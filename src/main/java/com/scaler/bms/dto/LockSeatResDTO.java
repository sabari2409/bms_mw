package com.scaler.bms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LockSeatResDTO {

    private String lockId;
    private LocalDateTime lockCreatedDate;
    private Long totalPrice;
}
