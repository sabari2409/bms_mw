package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.TicketSeatReqDTO;
import com.scaler.bms.entity.TicketSeat;

public interface TicketSeatService {

    TicketSeat save(TicketSeatReqDTO ticketSeatDTO);
}
