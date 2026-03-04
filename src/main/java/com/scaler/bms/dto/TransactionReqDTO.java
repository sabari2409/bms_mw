package com.scaler.bms.dto;

import com.scaler.bms.constants.TransactionStatus;
import com.scaler.bms.entity.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TransactionReqDTO {

    private Payment payment;
    private TransactionStatus transactionStatus;
    private String referenceNo;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
