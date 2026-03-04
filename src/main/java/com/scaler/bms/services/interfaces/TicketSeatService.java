package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.TicketSeatReqDTO;
import com.scaler.bms.entity.TicketSeat;

import java.util.List;

public interface TicketSeatService {

    List<TicketSeat> save(TicketSeatReqDTO ticketSeatDTO);
}
