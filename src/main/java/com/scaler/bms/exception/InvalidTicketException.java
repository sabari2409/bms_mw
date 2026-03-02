package com.scaler.bms.exception;

public class InvalidTicketException extends RuntimeException {

    public InvalidTicketException() {
        super();
    }

    public InvalidTicketException(String msg) {
        super(msg);
    }
}
