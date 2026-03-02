package com.scaler.bms.services.impl;

import com.scaler.bms.dto.TicketReqDTO;
import com.scaler.bms.entity.Ticket;
import com.scaler.bms.exception.InvalidTicketException;
import com.scaler.bms.mapper.TicketMapper;
import com.scaler.bms.repository.TicketRepository;
import com.scaler.bms.services.interfaces.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository tr) {
        this.ticketRepository = tr;
    }

    @Override
    public Ticket updateTicketDetails(TicketReqDTO ticketReqDTO) {
        // convert DTO to Entity
        TicketMapper ticketMapper = new TicketMapper();
        Ticket ticketEntity = ticketMapper.toEntity(ticketReqDTO);
        try {
            return this.ticketRepository.save(ticketEntity);
        } catch (Exception ex) {
            throw new InvalidTicketException(ex.getMessage());
        }
    }

    @Override
    public Ticket findTicketDetailsByNo(Double ticketNo) {
        Optional<Ticket> ticket = this.ticketRepository.findByTicketNo(ticketNo);
        return ticket.orElseThrow(() -> new InvalidTicketException("Invalid ticket number"));
    }

}
