package com.scaler.bms.controller;

import com.scaler.bms.dto.BasePaymentReqDTO;
import com.scaler.bms.dto.PaymentValidateResDTO;
import com.scaler.bms.dto.UPIPaymentReqDTO;
import com.scaler.bms.exception.InvalidPaymentException;
import com.scaler.bms.exception.InvalidShowSeatLockException;
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
    public PaymentValidateResDTO validateLockExpiry(@RequestBody BasePaymentReqDTO req) {
        // We are getting the object based on payment type
        IPayment<BasePaymentReqDTO> payment = this.paymentFactory.getPaymentStrategy(req.getPaymentType());
        // Step 1: Check seat lock expired or not
        Boolean validate = this.paymentService.validateSeatExpiry(req, payment);
        // Step 2: Validate payment interface like upi id format, bank name existence etc
        if (validate) {
            return payment.validatePaymentDetails(req);
        } else {
            //TODO: Either throw exception or return response with error message
            throw new InvalidShowSeatLockException("Seats unavailable to book");
        }
    }

    @PostMapping("/pay")
    public void doPayment(@RequestBody UPIPaymentReqDTO request) {
        // We are getting the object based on payment type
        IPayment<BasePaymentReqDTO> payment = this.paymentFactory.getPaymentStrategy(request.getPaymentType());
        payment.doPayment(request);
    }
}
