package com.scaler.bms.constants;

import lombok.Getter;

@Getter
public enum SeatStatus {

    AVAILABLE(100, "Seats Available"),
    BOOKED(101, "Seats Booked"),

    LOCKED(102, "Seat Currently Locked");

    private final int code;
    private final String description;

    SeatStatus(int i, String name) {
        this.code = i;
        this.description = name;
    }
}