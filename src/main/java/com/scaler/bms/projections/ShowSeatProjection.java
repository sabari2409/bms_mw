package com.scaler.bms.projections;

import com.scaler.bms.constants.SeatStatus;

public interface ShowSeatProjection {
    Integer getId();

    Integer getSeatId();

    Integer getShowId();

    SeatStatus getSeatStatus();
}
