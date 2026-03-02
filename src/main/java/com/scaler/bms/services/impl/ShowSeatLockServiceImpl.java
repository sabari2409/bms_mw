package com.scaler.bms.services.impl;

import com.scaler.bms.entity.ShowSeatLock;
import com.scaler.bms.exception.InvalidShowSeatLockException;
import com.scaler.bms.projections.ShowSeatLockProjection;
import com.scaler.bms.repository.ShowSeatLockRepository;
import com.scaler.bms.services.interfaces.ShowSeatLockService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowSeatLockServiceImpl implements ShowSeatLockService {

    private final ShowSeatLockRepository showSeatLockRepository;

    public ShowSeatLockServiceImpl(ShowSeatLockRepository slr) {
        this.showSeatLockRepository = slr;
    }


    @Override
    public ShowSeatLockProjection findByUUID(String UUID) {
        Optional<ShowSeatLockProjection> lockDetails = this.showSeatLockRepository.findLockDetailsByUUID(UUID);
        return lockDetails.orElseThrow(() -> new InvalidShowSeatLockException("Invalid booking details"));
    }
}
