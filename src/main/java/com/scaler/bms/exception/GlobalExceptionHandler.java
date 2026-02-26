package com.scaler.bms.exception;

import com.scaler.bms.dto.ErrorResponseDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuditoriumException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuditoriumException(AuditoriumException ex) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setName(ex.getMessage());
        errorResponseDTO.setStatus(HttpStatus.BAD_REQUEST);
        errorResponseDTO.setDateTime(LocalDateTime.now());
        return ResponseEntity.badRequest().body(errorResponseDTO);
    }

    @ExceptionHandler(InvalidShowsException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidShowException(InvalidShowsException ex) {
        ErrorResponseDTO errorResponseDTO = GlobalExceptionHandler.getErrorResponseObj();
        return ResponseEntity.badRequest().body(errorResponseDTO);
    }

    @ExceptionHandler(InvalidMovieException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidMovieException(InvalidMovieException ex) {
        ErrorResponseDTO errorResponseDTO = GlobalExceptionHandler.getErrorResponseObj();
        String errMsg = ex.getMessage();
        errorResponseDTO.setName(errMsg);
        errorResponseDTO.setStatus(HttpStatus.NOT_FOUND);
        errorResponseDTO.setDateTime(LocalDateTime.now());
        return ResponseEntity.badRequest().body(errorResponseDTO);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponseDTO> handleDatabaseError(DataAccessException ex) {
        ErrorResponseDTO errorResponse = GlobalExceptionHandler.getErrorResponseObj();
        errorResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setName("Database temporarily unavailable. Please try again. " + ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserError(InvalidUserException ex) {
        ErrorResponseDTO errorResponse = GlobalExceptionHandler.getErrorResponseObj();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setName("Invalid user! User not found . " + ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<ErrorResponseDTO> handlePaymentError(InvalidPaymentException ex) {
        ErrorResponseDTO errorResponse = GlobalExceptionHandler.getErrorResponseObj();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setName(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(InvalidShowSeatLockException.class)
    public ResponseEntity<ErrorResponseDTO> handleShowSeatLockError(InvalidShowSeatLockException ex) {
        ErrorResponseDTO errorResponse = GlobalExceptionHandler.getErrorResponseObj();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setName(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }


    private static ErrorResponseDTO getErrorResponseObj() {
        return new ErrorResponseDTO();
    }

}
