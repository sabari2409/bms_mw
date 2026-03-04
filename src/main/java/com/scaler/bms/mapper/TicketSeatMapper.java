package com.scaler.bms.mapper;

import com.scaler.bms.dto.TicketReqDTO;
import com.scaler.bms.dto.TicketSeatReqDTO;
import com.scaler.bms.entity.ShowSeat;
import com.scaler.bms.entity.TicketSeat;

import java.time.LocalDateTime;

public class TicketSeatMapper implements ContextMapper<TicketSeatReqDTO, TicketSeat> {
    @Override
    public TicketSeat toEntity(TicketSeatReqDTO dto) {

        return null;
    }

    @Override
    public TicketSeatReqDTO toDTO(TicketSeat entity) {
        return null;
    }

    // TODO: Implement multiple parameter ShowContext option
    public TicketSeat toEntity(TicketSeatReqDTO ticketSeatDTO, ShowSeat showSeat) {
        TicketSeat ticketSeat = new TicketSeat();
        ticketSeat.setTicket(ticketSeatDTO.getTicket());
        ticketSeat.setShowSeat(showSeat);
        ticketSeat.setCreatedAt(LocalDateTime.now());
        ticketSeat.setUpdatedAt(LocalDateTime.now());
        return ticketSeat;
    }

}
