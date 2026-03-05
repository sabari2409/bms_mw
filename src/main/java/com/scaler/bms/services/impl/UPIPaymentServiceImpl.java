package com.scaler.bms.services.impl;

import com.scaler.bms.constants.*;
import com.scaler.bms.dto.*;
import com.scaler.bms.entity.Payment;
import com.scaler.bms.entity.Ticket;
import com.scaler.bms.entity.Transaction;
import com.scaler.bms.exception.InvalidPaymentException;
import com.scaler.bms.exception.InvalidTicketException;
import com.scaler.bms.repository.UPIPaymentRepository;
import com.scaler.bms.services.interfaces.IPayment;
import com.scaler.bms.services.interfaces.TicketService;
import com.scaler.bms.services.interfaces.TransactionService;
import com.scaler.bms.utils.ReferenceNoGenerator;
import jakarta.transaction.InvalidTransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public PaymentResDTO doPayment(UPIPaymentReqDTO dto) throws InterruptedException, InvalidTransactionException {
        // TODO: Third party api integration- We need to use Adapter Design Pattern


        //TODO: Below payment object, Create a DTO and convert to Entity instead of directly doing

        // Validate payment already exist for ticket
        Ticket ticket = this.validateTicketStatus(dto);
        Integer ticketID = ticket.getId();
        Optional<Payment> paymentDetailsByTicket = this.upiPaymentRepository.findPaymentsByTicket(ticketID);
        Payment paymentDetails = null;
        if (paymentDetailsByTicket.isEmpty()) {
            Payment payment = new Payment();
            payment.setPaymentType(PaymentType.UPI);
            payment.setPaymentStatus(PaymentStatus.IN_PROGRESS);
            payment.setTicket(ticket);
            payment.setCreatedAt(LocalDateTime.now());
            payment.setUpdatedAt(LocalDateTime.now());
            paymentDetails = this.upiPaymentRepository.save(payment);
        } else {
            paymentDetails = paymentDetailsByTicket.get();
        }

        // TODO: Validate transaction status based on payment Id
        Integer paymentId = paymentDetails.getId();
        this.validateTransactionStatus(paymentId);
        TransactionStatus postTxnStatus = this.processTransaction(paymentDetails);

        if (postTxnStatus == null) {
            throw new InvalidTransactionException("Transaction failed due to null");
        }
        PaymentResDTO paymentResDTO = new PaymentResDTO();
        if (postTxnStatus.equals(TransactionStatus.UPI_TRANSACTION_SUCCESS)) {
            // TODO need to set the ticket status as BOOKED
            ticket.setTicketStatus(TicketStatus.BOOKED);
            ticket.setUpdatedAt(LocalDateTime.now());
            // TODO: Updated the payment table; Need to set POJO to entity mapper
            Payment payment1 = new Payment();
            payment1.setPaymentType(PaymentType.UPI);
            payment1.setPaymentStatus(PaymentStatus.COMPLETED);
            payment1.setTicket(ticket);
            payment1.setCreatedAt(LocalDateTime.now());
            payment1.setUpdatedAt(LocalDateTime.now());
            this.upiPaymentRepository.save(payment1);

            // TODO: Need to prepare the response pojo
            paymentResDTO.setStatus(true);
            // TODO: do return the response
        } else {
            paymentResDTO.setStatus(false);
        }
        return paymentResDTO;
    }

    @Override
    @Transactional
    public PaymentValidateResDTO validatePaymentDetails(UPIPaymentReqDTO payment) {
        this.validateUPIFormat(payment);
        this.validateUPIBankList(payment);
        PaymentValidateResDTO paymentValidateResDTO = new PaymentValidateResDTO();
        paymentValidateResDTO.setStatus(true);
        return paymentValidateResDTO;
    }

    /**
     * validateUPIFormat
     *
     * @param payment
     */
    private void validateUPIFormat(UPIPaymentReqDTO payment) {
        //Todo: step 1: verify UPI format is correct
        boolean isValidUPIFormat = true; // TODO:  IMPLEMENTATION LOGIC TO BE HANDLED HERE
        if (!isValidUPIFormat) {
            throw new InvalidPaymentException("Invalid UPI");
        }
    }

    /**
     * validateUPIBankList
     *
     * @param payment
     */
    private void validateUPIBankList(UPIPaymentReqDTO payment) {
        // TODO STEP 2: VERIFY UPI ID OR MOBILE IS VALID BY CHECKING 3RD PARTY TRANSACTION
        // User entered upi id instead of mobile
        if (payment.getMobileNo().toString().isEmpty()) {
            // split by @ symbol
            String[] upiList = payment.getUpiNo().split("@");
            List<String> upiBanks = BankTypes.UPIBanks;
            if (!upiBanks.contains(upiList[0])) {
                throw new InvalidPaymentException("Invalid UPI Bank");
            }
        }
    }

    /**
     * This method is used check ticket statys is not booked to proceed for payment
     * validateTicketStatus
     *
     * @param dto
     * @return
     */
    private Ticket validateTicketStatus(UPIPaymentReqDTO dto) {
        Ticket ticket = this.ticketService.findTicketDetailsByNo(dto.getTicketNo());
        // TODO: Very important step - Validate ticket status. If already booked or inprogress or any state other than available don't execute below code snippets
        TicketStatus ticketStatus = ticket.getTicketStatus();
        if (ticketStatus.equals(TicketStatus.BOOKED)) {
            throw new InvalidTicketException("Ticket already booked.");
        }
        if (ticketStatus.equals(TicketStatus.IN_PROGRESS)) {
            throw new InvalidTicketException("Ticket booking In-Progress by other user.");
        }
        // step 3: validate Ticket is still exist or expired
        LocalDateTime ticketUpdatedAt = ticket.getUpdatedAt();
        LocalDateTime currentDateTime = LocalDateTime.now();

        boolean sameDate = ticketUpdatedAt.toLocalDate().equals(currentDateTime.toLocalDate());
        boolean isExpired = currentDateTime.isAfter(ticketUpdatedAt.plusMinutes(10));

        if (!sameDate || isExpired) {
            throw new InvalidTicketException(Message.BMS_PAYMENT_SESSION_EXPIRED);
        }
        return ticket;
    }

    /**
     * validateTransactionStatus
     *
     * @param paymentId
     * @throws InvalidTransactionException
     */
    private void validateTransactionStatus(Integer paymentId) throws InvalidTransactionException {
        TransactionStatus txnStatus = this.getTransactionStatusList(paymentId);
        if (txnStatus == null) {
            throw new InvalidTransactionException(Message.BMS_TXN_NULL);
        }
        if (txnStatus.equals(TransactionStatus.UPI_TRANSACTION_SUCCESS)) {
            throw new InvalidTransactionException(Message.BMS_INVALID_TXN_PAYMENT);
        }
    }

    /**
     * getTransactionStatusList
     *
     * @param paymentId
     * @return
     */
    private TransactionStatus getTransactionStatusList(Integer paymentId) {
        Optional<List<Transaction>> transactionListByPayment = this.transactionService.findByPaymentId(paymentId);

        // Get the last transaction status
        return transactionListByPayment.map(transactions -> transactions.get(0).getTransactionStatus()).orElse(null);
    }


    private TransactionStatus processTransaction(Payment paymentDetails) throws InterruptedException {
        //TODO: Below transaction object, Create a DTO and convert to Entity instead of directly doing

        List<TransactionStatus> transactionStatusList = new ArrayList<>();
        transactionStatusList.add(TransactionStatus.AUTH_STARTED);
        transactionStatusList.add(TransactionStatus.AUTH_IN_PROGRESS);
        transactionStatusList.add(TransactionStatus.AUTH_COMPLETED);
        transactionStatusList.add(TransactionStatus.MINIMUM_BALANCE_VALIDATION_SUCCESS);
        transactionStatusList.add(TransactionStatus.UPI_TRANSACTION_SUCCESS);

        for (TransactionStatus status : transactionStatusList) {
            String referenceNo = ReferenceNoGenerator.generateReference();
            TransactionReqDTO transaction_referenceNo = new TransactionReqDTO();
            transaction_referenceNo.setPayment(paymentDetails);
            transaction_referenceNo.setTransactionDate(LocalDateTime.now());
            transaction_referenceNo.setReferenceNo(referenceNo);
            transaction_referenceNo.setTransactionStatus(status);
            transaction_referenceNo.setCreatedAt(LocalDateTime.now());
            transaction_referenceNo.setUpdatedAt(LocalDateTime.now());
            this.transactionService.create(transaction_referenceNo);
            Thread.sleep(2000);
        }
        TransactionStatus txnStatus = this.getTransactionStatusList(paymentDetails.getId());
        return txnStatus;
    }
}