package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.TicketReqDTO;
import com.scaler.bms.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    Ticket updateTicketDetails(TicketReqDTO ticketReqDTO);

    Ticket findTicketDetailsByNo(Double ticketNo);

}
