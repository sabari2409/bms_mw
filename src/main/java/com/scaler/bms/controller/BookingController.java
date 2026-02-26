package com.scaler.bms.controller;

import com.scaler.bms.dto.BookingReqDTO;
import com.scaler.bms.dto.ErrorResponseDTO;
import com.scaler.bms.dto.LockSeatResDTO;
import com.scaler.bms.exception.InvalidBookingException;
import com.scaler.bms.services.interfaces.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bs) {
        this.bookingService = bs;
    }

    @PostMapping
    public ResponseEntity<?> seatBooking(@RequestBody BookingReqDTO request) {
        if (request == null) {
            throw new InvalidBookingException("Invalid request. Request is null");
        }
        try {
            LockSeatResDTO lockId = this.bookingService.bookTickets(request);
            System.out.println("Controller lockId -->" + lockId);
            return ResponseEntity.ok(lockId);
        } catch (Exception ex) {
            System.out.println("Exception -->" + ex.getMessage());
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
            errorResponseDTO.setName(ex.getMessage());
            errorResponseDTO.setStatus(HttpStatus.BAD_REQUEST);
            errorResponseDTO.setDateTime(LocalDateTime.now());
            return ResponseEntity.badRequest().body(errorResponseDTO);
        }

    }
}
