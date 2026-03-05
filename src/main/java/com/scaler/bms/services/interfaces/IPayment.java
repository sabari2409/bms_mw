package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.BasePaymentReqDTO;
import com.scaler.bms.dto.PaymentResDTO;
import com.scaler.bms.dto.PaymentValidateResDTO;
import jakarta.transaction.InvalidTransactionException;

public interface IPayment<T extends BasePaymentReqDTO> {

    PaymentResDTO doPayment(T dto) throws InterruptedException, InvalidTransactionException;

    PaymentValidateResDTO validatePaymentDetails(T dto);
}
