package com.scaler.bms.services.impl;


import com.scaler.bms.dto.BasePaymentReqDTO;
import com.scaler.bms.exception.InvalidPaymentException;
import com.scaler.bms.services.interfaces.IPayment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentFactory {

    private final Map<String, IPayment<?>> paymentStrategy;

    public PaymentFactory(Map<String, IPayment<?>> st) {
        this.paymentStrategy = st;
    }


    public IPayment<?> getPaymentStrategy(String paymentType) {
        IPayment<?> payment = this.paymentStrategy.get(paymentType);
        if (payment == null) {
            throw new InvalidPaymentException("Invalid payment Type");
        }
        return payment;
    }

}
