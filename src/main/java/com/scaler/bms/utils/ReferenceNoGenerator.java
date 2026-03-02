package com.scaler.bms.utils;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReferenceNoGenerator {

    private static final String PREFIX = "TXN";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int RANDOM_LENGTH = 6;
    private static final SecureRandom secureRandom = new SecureRandom();

    private ReferenceNoGenerator() {
        // Prevent instantiation
    }

    public static String generateReference() {

        // Date part (YYYYMMDD)
        String datePart = LocalDate.now()
                .format(DateTimeFormatter.BASIC_ISO_DATE);

        // Random part
        StringBuilder randomPart = new StringBuilder();
        for (int i = 0; i < RANDOM_LENGTH; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            randomPart.append(CHARACTERS.charAt(index));
        }

        return PREFIX + datePart + randomPart;
    }
}