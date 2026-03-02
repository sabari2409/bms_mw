package com.scaler.bms.entity;

import com.scaler.bms.constants.PaymentStatus;
import com.scaler.bms.constants.PaymentType;
import com.scaler.bms.constants.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseModel {

    private String referenceNo;

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.ORDINAL)
    private TransactionStatus transactionStatus;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
