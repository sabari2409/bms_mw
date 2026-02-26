package com.scaler.bms.dto;

import lombok.Getter;

@Getter
public class BasePaymentReqDTO {

    private String uuid;
    private String paymentType;
    private Long price;
}
