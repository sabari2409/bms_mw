package com.scaler.bms.projections;

import com.scaler.bms.constants.LockStatus;

import java.time.LocalDateTime;

public interface ShowSeatLockProjection {
    Integer getLockId();

    String getUniqueId();

    LocalDateTime getLockCreatedDate();

    LockStatus getLockStatus();
}
