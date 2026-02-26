package com.scaler.bms.services.impl;

import com.scaler.bms.dto.BasePaymentReqDTO;
import com.scaler.bms.exception.InvalidPaymentException;
import com.scaler.bms.exception.InvalidShowSeatLockException;
import com.scaler.bms.projections.ShowSeatLockProjection;
import com.scaler.bms.services.interfaces.IPayment;
import com.scaler.bms.services.interfaces.PaymentService;
import com.scaler.bms.services.interfaces.ShowSeatLockService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    public final ShowSeatLockService showSeatLockService;

    public PaymentServiceImpl(ShowSeatLockService ssls) {
        this.showSeatLockService = ssls;
    }


    @Override
    public Boolean validate(BasePaymentReqDTO req, IPayment payment) {

        // step 1: validate the payment status
        if (payment == null) {
            throw new InvalidPaymentException("Invalid Payment mode");
        }

        // step 2: validate lock is expired or not
        String UUID = req.getUuid();
        ShowSeatLockProjection lockDetails = this.showSeatLockService.findByUUID(UUID);

        // step 3: validate lock is still exist or expired
        LocalDateTime lockCreatedAt = lockDetails.getLockCreatedDate();
        LocalDateTime currentDateTime = LocalDateTime.now();

        boolean sameDate = lockCreatedAt.toLocalDate().equals(currentDateTime.toLocalDate());
        boolean isExpired = currentDateTime.isAfter(lockCreatedAt.plusMinutes(10));

        if (!sameDate || isExpired) {
            throw new InvalidShowSeatLockException("Seats Expired");
        }
        return true;
    }
}
