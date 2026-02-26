package com.scaler.bms.services.impl;

import com.scaler.bms.dto.BasePaymentReqDTO;
import com.scaler.bms.dto.PaymentResDTO;
import com.scaler.bms.dto.UPIPaymentReqDTO;
import com.scaler.bms.services.interfaces.IPayment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UPI")
public class UPIPaymentServiceImpl implements IPayment<UPIPaymentReqDTO> {

    @Override
    public PaymentResDTO doPayment(UPIPaymentReqDTO dto) {
        // TODO: Third party api integration- We need to use Adapter Design Pattern

        return null;
    }

    @Override
    @Transactional
    public void initiateTransaction(UPIPaymentReqDTO payment) {
        //Todo: step 1: verify UPI format is correct
        Boolean isValidUPIFormat = this.checkUPIFormat(payment);

        // TODO STEP 2: VERIFY UPI ID OR MOBILE IS VALID BY CHECKING 3RD PARTY TRANSACTION

        // TODO STEP 3: VALIDATE OTHER TRANSACTION STATUS ARE FINE

        // TODO STEP 4: ADD DETAILS IN TRANSACTION TABLE BASED ON ABOVE OPS

        // TODO STE 5: IF ANY TRANSACTION FAILED DO ROLLBACK

    }

    private Boolean checkUPIFormat(UPIPaymentReqDTO payment) {
        // TODO:  IMPLEMENTATION LOGIC TO BE HANDLED HERE
        return true;
    }
}