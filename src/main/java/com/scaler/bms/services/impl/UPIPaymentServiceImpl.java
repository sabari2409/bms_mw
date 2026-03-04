package com.scaler.bms.services.impl;

import com.scaler.bms.constants.*;
import com.scaler.bms.dto.*;
import com.scaler.bms.entity.Payment;
import com.scaler.bms.entity.Ticket;
import com.scaler.bms.entity.Transaction;
import com.scaler.bms.exception.InvalidPaymentException;
import com.scaler.bms.repository.UPIPaymentRepository;
import com.scaler.bms.services.interfaces.IPayment;
import com.scaler.bms.services.interfaces.TicketService;
import com.scaler.bms.services.interfaces.TransactionService;
import com.scaler.bms.utils.ReferenceNoGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("UPIPS")
public class UPIPaymentServiceImpl implements IPayment<UPIPaymentReqDTO> {

    private final UPIPaymentRepository upiPaymentRepository;
    private final TicketService ticketService;
    private final TransactionService transactionService;

    public UPIPaymentServiceImpl(UPIPaymentRepository upiPaymentRepository, TicketService ts, TransactionService transactionService) {
        this.upiPaymentRepository = upiPaymentRepository;
        this.ticketService = ts;
        this.transactionService = transactionService;
    }

    @Override
    public PaymentResDTO doPayment(UPIPaymentReqDTO dto) throws InterruptedException {
        // TODO: Third party api integration- We need to use Adapter Design Pattern

        // TODO STEP 1: VALIDATE OTHER TRANSACTION STATUS ARE FINE
        Ticket ticket = this.ticketService.findTicketDetailsByNo(dto.getTicketNo());

        //TODO: Below payment object, Create a DTO and convert to Entity instead of directly doing
        Payment payment = new Payment();
        payment.setPaymentType(PaymentType.UPI);
        payment.setPaymentStatus(PaymentStatus.IN_PROGRESS);
        payment.setTicket(ticket);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
        Payment paymentDetails = this.upiPaymentRepository.save(payment);

        // TODO STEP 2: ADD DETAILS IN TRANSACTION TABLE BASED ON ABOVE OPS
        //TODO: Below transaction object, Create a DTO and convert to Entity instead of directly doing

        // TODO: Very important step - Validate ticket status. If already booked or inprogress or any state other than available don't execute below code snippets
        TransactionReqDTO transaction = new TransactionReqDTO();
        String referenceNo = ReferenceNoGenerator.generateReference();
        transaction.setPayment(paymentDetails);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setReferenceNo(referenceNo);
        transaction.setTransactionStatus(TransactionStatus.AUTH_STARTED);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());
        this.transactionService.create(transaction);

        Thread.sleep(5000);

        TransactionReqDTO transaction1 = new TransactionReqDTO();
        String referenceNo5 = ReferenceNoGenerator.generateReference();
        transaction1.setPayment(paymentDetails);
        transaction1.setTransactionDate(LocalDateTime.now());
        transaction1.setReferenceNo(referenceNo5);
        transaction1.setTransactionStatus(TransactionStatus.AUTH_IN_PROGRESS);
        transaction1.setCreatedAt(LocalDateTime.now());
        transaction1.setUpdatedAt(LocalDateTime.now());
        this.transactionService.create(transaction1);

        Thread.sleep(5000);

        TransactionReqDTO transaction2 = new TransactionReqDTO();
        String referenceNo1 = ReferenceNoGenerator.generateReference();
        transaction2.setPayment(paymentDetails);
        transaction2.setTransactionDate(LocalDateTime.now());
        transaction2.setReferenceNo(referenceNo1);
        transaction2.setTransactionStatus(TransactionStatus.AUTH_COMPLETED);
        transaction2.setCreatedAt(LocalDateTime.now());
        transaction2.setUpdatedAt(LocalDateTime.now());
        this.transactionService.create(transaction2);

        Thread.sleep(5000);

        TransactionReqDTO transaction3 = new TransactionReqDTO();
        String referenceNo2 = ReferenceNoGenerator.generateReference();
        transaction3.setPayment(paymentDetails);
        transaction3.setTransactionDate(LocalDateTime.now());
        transaction3.setReferenceNo(referenceNo2);
        transaction3.setTransactionStatus(TransactionStatus.MINIMUM_BALANCE_VALIDATION_SUCCESS);
        transaction3.setCreatedAt(LocalDateTime.now());
        transaction3.setUpdatedAt(LocalDateTime.now());
        this.transactionService.create(transaction3);

        Thread.sleep(2000);

        TransactionReqDTO transaction4 = new TransactionReqDTO();
        String referenceNo3 = ReferenceNoGenerator.generateReference();
        transaction4.setPayment(paymentDetails);
        transaction4.setTransactionDate(LocalDateTime.now());
        transaction4.setReferenceNo(referenceNo3);
        transaction4.setTransactionStatus(TransactionStatus.UPI_TRANSACTION_SUCCESS);
        transaction4.setCreatedAt(LocalDateTime.now());
        transaction4.setUpdatedAt(LocalDateTime.now());
        this.transactionService.create(transaction4);


        // TODO need to set the ticket status as BOOKED
        ticket.setTicketStatus(TicketStatus.BOOKED);
        // TODO: Updated the payment table; Need to set POJO to entity mapper
        Payment payment1 = new Payment();
        payment1.setPaymentType(PaymentType.UPI);
        payment1.setPaymentStatus(PaymentStatus.COMPLETED);
        payment1.setTicket(ticket);
        payment1.setCreatedAt(LocalDateTime.now());
        payment1.setUpdatedAt(LocalDateTime.now());
        Payment paymentDetails1 = this.upiPaymentRepository.save(payment1);

        // TODO: Need to prepare the response pojo
        PaymentResDTO paymentResDTO = new PaymentResDTO();
        paymentResDTO.setStatus(true);
        // TODO: do return the response
        return paymentResDTO;
    }

    @Override
    @Transactional
    public PaymentValidateResDTO validatePaymentDetails(UPIPaymentReqDTO payment) {
        //Todo: step 1: verify UPI format is correct
        Boolean isValidUPIFormat = this.checkUPIFormat(payment);
        if (!isValidUPIFormat) {
            throw new InvalidPaymentException("Invalid UPI");
        }

        // TODO STEP 2: VERIFY UPI ID OR MOBILE IS VALID BY CHECKING 3RD PARTY TRANSACTION
        // User entered upi id instead of mobile
        if (payment.getMobileNo().toString().isEmpty()) {
            // spilit by @ symbol
            String[] upiList = payment.getUpiNo().split("@");
            List<String> upiBanks = BankTypes.UPIBanks;
            if (!upiBanks.contains(upiList[0])) {
                throw new InvalidPaymentException("Invalid UPI Bank");
            }
        }
        PaymentValidateResDTO paymentValidateResDTO = new PaymentValidateResDTO();
        paymentValidateResDTO.setStatus(true);
        return paymentValidateResDTO;
    }

    private Boolean checkUPIFormat(UPIPaymentReqDTO payment) {
        // TODO:  IMPLEMENTATION LOGIC TO BE HANDLED HERE
        return true;
    }
}