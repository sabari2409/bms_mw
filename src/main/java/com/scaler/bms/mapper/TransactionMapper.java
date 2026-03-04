package com.scaler.bms.mapper;

import com.scaler.bms.dto.TransactionReqDTO;
import com.scaler.bms.entity.Transaction;
import org.springframework.stereotype.Component;


public class TransactionMapper implements ContextMapper<TransactionReqDTO, Transaction> {
    @Override
    public Transaction toEntity(TransactionReqDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(dto.getTransactionStatus());
        transaction.setPayment(dto.getPayment());
        transaction.setCreatedAt(dto.getCreatedAt());
        transaction.setUpdatedAt(dto.getUpdatedAt());
        transaction.setReferenceNo(dto.getReferenceNo());
        transaction.setTransactionDate(dto.getTransactionDate());
        return transaction;
    }

    @Override
    public TransactionReqDTO toDTO(Transaction entity) {
        return null;
    }
}
