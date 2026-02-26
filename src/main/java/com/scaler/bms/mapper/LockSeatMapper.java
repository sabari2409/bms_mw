package com.scaler.bms.mapper;

import com.scaler.bms.dto.LockSeatResDTO;
import com.scaler.bms.entity.ShowSeatLock;

public class LockSeatMapper implements ContextMapper<LockSeatResDTO, ShowSeatLock> {
    @Override
    public ShowSeatLock toEntity(LockSeatResDTO dto) {
        return null;
    }

    @Override
    public LockSeatResDTO toDTO(ShowSeatLock entity) {
        return null;
    }
}
