package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.BasePaymentReqDTO;

public interface PaymentService {

    Boolean validate(BasePaymentReqDTO req, IPayment payment);
}
