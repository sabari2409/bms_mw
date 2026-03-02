package com.scaler.bms.mapper;

import com.scaler.bms.dto.TicketReqDTO;
import com.scaler.bms.dto.TicketSeatReqDTO;
import com.scaler.bms.entity.TicketSeat;

public class TicketSeatMapper implements ContextMapper<TicketSeatReqDTO, TicketSeat> {
    @Override
    public TicketSeat toEntity(TicketSeatReqDTO dto) {
        return null;
    }

    @Override
    public TicketReqDTO toDTO(TicketSeat entity) {
        return null;
    }
}
