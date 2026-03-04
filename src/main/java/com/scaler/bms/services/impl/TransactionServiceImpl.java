package com.scaler.bms.services.impl;

import com.scaler.bms.dto.TransactionReqDTO;
import com.scaler.bms.entity.Transaction;
import com.scaler.bms.mapper.TransactionMapper;
import com.scaler.bms.repository.TransactionRepository;
import com.scaler.bms.services.interfaces.TransactionService;
import org.springframework.stereotype.Service;

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
}
