package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.BookingReqDTO;
import com.scaler.bms.dto.LockSeatResDTO;
import com.scaler.bms.projections.BookingProjection;

public interface BookingService {

    LockSeatResDTO bookTickets(BookingReqDTO request);
}
