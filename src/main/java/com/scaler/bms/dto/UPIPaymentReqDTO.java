package com.scaler.bms.dto;

import lombok.Getter;

@Getter
public class UPIPaymentReqDTO extends BasePaymentReqDTO {

    private Double ticketNo;
    private Long mobileNo;
    private String upiNo;
}
