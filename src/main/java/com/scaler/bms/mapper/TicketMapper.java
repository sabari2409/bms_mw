package com.scaler.bms.mapper;

import com.scaler.bms.dto.TicketReqDTO;
import com.scaler.bms.entity.Ticket;

public class TicketMapper implements ContextMapper<TicketReqDTO, Ticket> {
    @Override
    public Ticket toEntity(TicketReqDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setShows(dto.getShows());
        ticket.setTicketStatus(dto.getTicketStatus());
        ticket.setPrice(dto.getPrice());
        ticket.setUsers(dto.getUsers());
        ticket.setCreatedAt(dto.getCreatedAt());
        ticket.setUpdatedAt(dto.getUpdatedAt());
        ticket.setTicketNo(dto.getTicketNo());
        return ticket;
    }

    @Override
    public TicketReqDTO toDTO(Ticket entity) {
        return null;
    }
}
