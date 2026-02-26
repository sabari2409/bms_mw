package com.scaler.bms.services.impl;

import com.scaler.bms.dto.BasePaymentReqDTO;
import com.scaler.bms.dto.PaymentResDTO;
import com.scaler.bms.entity.Payment;
import com.scaler.bms.services.interfaces.IPayment;
import org.springframework.stereotype.Service;

@Service("NBPS")
public class NetBankingPaymentServiceImpl implements IPayment<BasePaymentReqDTO> {


    @Override
    public PaymentResDTO doPayment(BasePaymentReqDTO reqDTO) {
        return null;
    }

    @Override
    public void initiateTransaction(BasePaymentReqDTO dto) {

    }
}
