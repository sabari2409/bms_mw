package com.scaler.bms.services.interfaces;

import com.scaler.bms.entity.ShowSeatLock;
import com.scaler.bms.projections.ShowSeatLockProjection;

import java.util.Optional;

public interface ShowSeatLockService {

    ShowSeatLockProjection findByUUID(String UUID);
}
