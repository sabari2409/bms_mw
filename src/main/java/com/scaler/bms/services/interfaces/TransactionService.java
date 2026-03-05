package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.TransactionReqDTO;
import com.scaler.bms.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    void create(TransactionReqDTO reqDTO);

    Optional<List<Transaction>> findByPaymentId(Integer paymentID);
}
