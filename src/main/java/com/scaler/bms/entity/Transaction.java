package com.scaler.bms.entity;

import com.scaler.bms.constants.PaymentStatus;
import com.scaler.bms.constants.PaymentType;
import com.scaler.bms.constants.TransactionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction extends BaseModel {

    private Long referenceNo;

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.ORDINAL)
    private TransactionStatus transactionStatus;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public Long getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(Long referenceNo) {
        this.referenceNo = referenceNo;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
