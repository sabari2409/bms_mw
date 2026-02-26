package com.scaler.bms.controller;

import com.scaler.bms.dto.BasePaymentReqDTO;
import com.scaler.bms.services.impl.PaymentFactory;
import com.scaler.bms.services.interfaces.IPayment;
import com.scaler.bms.services.interfaces.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentFactory paymentFactory;
    private final PaymentService paymentService;

    public PaymentController(PaymentFactory pf, PaymentService ps) {
        this.paymentFactory = pf;
        this.paymentService = ps;
    }


    @PostMapping
    public void validateLockExpiry(@RequestBody BasePaymentReqDTO req) {
        IPayment payment = this.paymentFactory.getPaymentStrategy(req.getPaymentType());
        Boolean validate = this.paymentService.validate(req, payment);
        if (validate) {
            payment.initiateTransaction(req);
        } else {
            //TODO: Either throw exception or return response with error message
        }

    }
}
