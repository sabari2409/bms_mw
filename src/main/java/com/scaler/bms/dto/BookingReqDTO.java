package com.scaler.bms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BookingReqDTO {

    private Integer userId;
    private List<Integer> showSeatIds;
}
