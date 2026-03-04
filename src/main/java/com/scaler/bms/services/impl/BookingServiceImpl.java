package com.scaler.bms.services.impl;

import com.scaler.bms.constants.LockStatus;
import com.scaler.bms.constants.SeatStatus;
import com.scaler.bms.constants.TicketStatus;
import com.scaler.bms.dto.*;
import com.scaler.bms.entity.*;
import com.scaler.bms.exception.InvalidBookingException;
import com.scaler.bms.exception.InvalidUserException;
import com.scaler.bms.projections.BookingProjection;
import com.scaler.bms.services.interfaces.*;
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
    private final TicketService ticketService;
    private final ShowsService showsService;
    private final TicketSeatService ticketSeatService;

    public BookingServiceImpl(
            ShowSeatService ss,
            UserService us,
            TicketService ts,
            ShowsService showsService,
            TicketSeatService tss) {
        this.showSeatService = ss;
        this.userService = us;
        this.ticketService = ts;
        this.showsService = showsService;
        this.ticketSeatService = tss;
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


        // Step 10: Add details in Ticket entity

        // Step 10.1 get the show by showId
        Shows show = this.showsService.findShowsById(showId);
        // Step 10.2 Add the ticket details in ticket DTO and do save
        TicketReqDTO ticketReqDTO = new TicketReqDTO();
        ticketReqDTO.setShows(show);
        ticketReqDTO.setTicketStatus(TicketStatus.INIT);
        ticketReqDTO.setUsers(userDetails);
        ticketReqDTO.setPrice(totalPrice);
        ticketReqDTO.setCreatedAt(LocalDateTime.now());
        ticketReqDTO.setUpdatedAt(LocalDateTime.now());
        ticketReqDTO.setTicketNo(Math.random());

        // Step 10.3 update the seat details for the ticket in TicketSeat Entity
        Ticket ticket = this.ticketService.updateTicketDetails(ticketReqDTO);

        TicketSeatReqDTO ticketSeatDTO = new TicketSeatReqDTO();
        ticketSeatDTO.setTicket(ticket);
        ticketSeatDTO.setShowSeatList(validShowsList);

        List<TicketSeat> ticketSeatList = this.ticketSeatService.save(ticketSeatDTO);
        System.out.println("TicketSeat Entry details -===>" + ticketSeatList);
        TicketDetailsDTO ticketDetailsDTO = new TicketDetailsDTO();
        ticketDetailsDTO.setTicketNo(ticket.getTicketNo());
        ticketDetailsDTO.setPrice(ticket.getPrice());
        ticketDetailsDTO.setCreatedDate(ticket.getCreatedAt());

        // Step 9: update the records in ShowSeatLockEntity
        LockSeatResDTO lockSeatResDTO = new LockSeatResDTO();
        lockSeatResDTO.setLockId(lockId);
        lockSeatResDTO.setLockCreatedDate(LocalDateTime.now());
        lockSeatResDTO.setTotalPrice(totalPrice);

        lockSeatResDTO.setTicketResDTO(ticketDetailsDTO);

        return lockSeatResDTO;
    }


}
