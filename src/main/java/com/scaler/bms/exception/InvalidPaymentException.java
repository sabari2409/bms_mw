package com.scaler.bms.exception;

public class InvalidPaymentException extends RuntimeException {

    public InvalidPaymentException() {
        super();
    }

    public InvalidPaymentException(String msg) {
        super(msg);
    }
}
