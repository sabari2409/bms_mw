package com.scaler.bms.services.impl;

import com.scaler.bms.dto.TicketSeatReqDTO;
import com.scaler.bms.entity.TicketSeat;
import com.scaler.bms.exception.InvalidTicketException;
import com.scaler.bms.mapper.TicketSeatMapper;
import com.scaler.bms.repository.TicketSeatRepository;
import com.scaler.bms.services.interfaces.TicketSeatService;
import org.springframework.stereotype.Service;

@Service
public class TicketSeatServiceImpl implements TicketSeatService {

    private final TicketSeatRepository ticketSeatRepository;

    public TicketSeatServiceImpl(TicketSeatRepository tr) {
        this.ticketSeatRepository = tr;
    }


    @Override
    public TicketSeat save(TicketSeatReqDTO ticketSeatDTO) {
        try {
            TicketSeatMapper ticketSeatMapper = new TicketSeatMapper();
            TicketSeat ticketSeatEntity = ticketSeatMapper.toEntity(ticketSeatDTO);
            return this.ticketSeatRepository.save(ticketSeatEntity);
        } catch (Exception ex) {
            throw new InvalidTicketException("Exception in ticket creation" + ex.getMessage());
        }
    }
}
