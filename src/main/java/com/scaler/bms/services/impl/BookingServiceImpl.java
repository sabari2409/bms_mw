package com.scaler.bms.services.impl;

import com.scaler.bms.constants.LockStatus;
import com.scaler.bms.constants.SeatStatus;
import com.scaler.bms.dto.BookingReqDTO;
import com.scaler.bms.dto.LockSeatResDTO;
import com.scaler.bms.dto.TicketReqDTO;
import com.scaler.bms.dto.TicketSaveDTO;
import com.scaler.bms.entity.ShowSeat;
import com.scaler.bms.entity.ShowSeatLock;
import com.scaler.bms.entity.Users;
import com.scaler.bms.exception.InvalidBookingException;
import com.scaler.bms.exception.InvalidUserException;
import com.scaler.bms.projections.BookingProjection;
import com.scaler.bms.services.interfaces.BookingService;
import com.scaler.bms.services.interfaces.ShowSeatService;
import com.scaler.bms.services.interfaces.TicketService;
import com.scaler.bms.services.interfaces.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Service
public class BookingServiceImpl implements BookingService {

    //    private final TicketService ticketService;
    private final ShowSeatService showSeatService;
    private final UserService userService;

    public BookingServiceImpl(
//            TicketService tr,
            ShowSeatService ss,
            UserService us) {
//        this.ticketService = tr;
        this.showSeatService = ss;
        this.userService = us;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LockSeatResDTO bookTickets(BookingReqDTO request) {

        List<Integer> selectedShowSeatIds = request.getShowSeatIds();

        // Step1: validate duplicate seats
        HashSet<Integer> hashSetShowSeatIds = new HashSet<Integer>(request.getShowSeatIds());
        if (hashSetShowSeatIds.size() != request.getShowSeatIds().size()) {
            throw new InvalidBookingException("Duplicate seats not allowed");
        }

        //Step 2: first put the lock for the seats before doing availability status
        List<ShowSeat> showSeatProjectionList = this.showSeatService.findAllByIdInForUpdate(selectedShowSeatIds);

        // Step 3: validate the request seatIds are valid
        if (showSeatProjectionList.isEmpty() || showSeatProjectionList.size() != selectedShowSeatIds.size()) {
            throw new InvalidBookingException("Selected Seats Invalid");
        }

        // Step 4: validate seats available or not
        List<ShowSeat> seatStatusList = showSeatProjectionList
                .stream()
                .filter((s) -> s.getSeatStatus().equals(SeatStatus.AVAILABLE))
                .toList();

        // Step 5: If the selected seatid and available seat id's match then proceed for payment
        if (seatStatusList.size() != selectedShowSeatIds.size()) {
            throw new InvalidBookingException("Seats are not available for booking");
        }

        // Step 6: validate All seats belong to the same show
        Integer showId = seatStatusList.get(0).getShows().getId();
        List<ShowSeat> validShowsList = showSeatProjectionList
                .stream()
                .filter((s) -> s.getShows().getId().equals(showId))
                .toList();

        if (validShowsList.size() != selectedShowSeatIds.size()) {
            throw new InvalidBookingException("Invalid seat selection");
        }

        // Step 7: validate user
        Users userDetails = this.userService.getUser(request.getUserId());
        if (userDetails == null) {
            throw new InvalidUserException("Not a valid user. Please try booking with valid user");
        }

        // Step 8: Set status as lock in table
        String lockId = UUID.randomUUID().toString();
        ShowSeatLock showSeatLock = new ShowSeatLock();
        showSeatLock.setUUID(lockId);
        showSeatLock.setLockStatus(LockStatus.LOCKED);
        showSeatLock.setUsers(userDetails);
        showSeatLock.setLockCreatedAt(LocalDateTime.now());
        showSeatLock.setUpdatedAt(LocalDateTime.now());


        for (ShowSeat seat : showSeatProjectionList) {
            seat.setSeatStatus(SeatStatus.LOCKED);
            seat.setUpdatedAt(LocalDateTime.now());
            seat.setUsers(userDetails);
            seat.setShowSeatLock(showSeatLock);

        }

        // step 8 calculate the total_price of the ticket
        Long totalPrice = 0L;
        for (ShowSeat seat : showSeatProjectionList) {
            totalPrice += seat.getSeat().getPrice();
        }

        System.out.println("Total price = " + totalPrice);
        System.out.println("LockId -->" + lockId);

        // Step 9: update the records in ShowSeatLockEntity
        LockSeatResDTO lockSeatResDTO = new LockSeatResDTO();
        lockSeatResDTO.setLockId(lockId);
        lockSeatResDTO.setLockCreatedDate(LocalDateTime.now());
        lockSeatResDTO.setTotalPrice(totalPrice);


        // Step 10: Add details in Ticket entity
        TicketSaveDTO ticketSaveDTO = new TicketSaveDTO();
        

        return lockSeatResDTO;
    }


}
