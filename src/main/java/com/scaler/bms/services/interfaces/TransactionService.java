package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.TransactionReqDTO;

public interface TransactionService {
    void create(TransactionReqDTO reqDTO);
}
