package com.scaler.bms.exception;

public class InvalidMovieException extends RuntimeException {

    public InvalidMovieException() {
        super();
    }

    public InvalidMovieException(String ex) {
        super(ex);
    }
}
