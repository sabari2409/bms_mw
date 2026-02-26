package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.TicketReqDTO;
import com.scaler.bms.entity.Ticket;

import java.util.List;

public interface TicketService {

    Ticket createTicket(TicketReqDTO ticketReqDTO);

}
