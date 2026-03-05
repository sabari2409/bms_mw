package com.scaler.bms.services.impl;

import com.scaler.bms.dto.TransactionReqDTO;
import com.scaler.bms.entity.Transaction;
import com.scaler.bms.exception.InvalidPaymentException;
import com.scaler.bms.mapper.TransactionMapper;
import com.scaler.bms.repository.TransactionRepository;
import com.scaler.bms.services.interfaces.TransactionService;
import jakarta.transaction.InvalidTransactionException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository tr) {
        this.transactionRepository = tr;
    }

    @Override
    public void create(TransactionReqDTO reqDTO) {
        TransactionMapper transactionMapper = new TransactionMapper();
        Transaction transaction = transactionMapper.toEntity(reqDTO);
        this.transactionRepository.save(transaction);
    }

    @Override
    public Optional<List<Transaction>> findByPaymentId(Integer paymentID) {
        if (paymentID == null) {
            throw new InvalidPaymentException("Invalid payment details. Transaction failed");
        }
        return this.transactionRepository.findAllById(paymentID);
    }

}
