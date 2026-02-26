package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.BasePaymentReqDTO;
import com.scaler.bms.dto.PaymentResDTO;

public interface IPayment<T extends BasePaymentReqDTO> {

    PaymentResDTO doPayment(T dto);

    void initiateTransaction(T dto);
}
