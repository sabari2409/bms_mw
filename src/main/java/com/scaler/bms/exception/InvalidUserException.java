package com.scaler.bms.exception;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException() {
        super();
    }

    public InvalidUserException(String ex) {
        super(ex);
    }
}
