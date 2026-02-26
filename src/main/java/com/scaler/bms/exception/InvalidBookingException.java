package com.scaler.bms.exception;

public class InvalidBookingException extends RuntimeException {

    public InvalidBookingException() {
        super();
    }

    public InvalidBookingException(String msg) {
        super(msg);
    }
}
