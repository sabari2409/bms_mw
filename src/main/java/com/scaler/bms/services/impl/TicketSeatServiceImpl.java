package com.scaler.bms.services.impl;

import com.scaler.bms.dto.TicketSeatReqDTO;
import com.scaler.bms.entity.ShowSeat;
import com.scaler.bms.entity.TicketSeat;
import com.scaler.bms.exception.InvalidTicketException;
import com.scaler.bms.mapper.TicketSeatMapper;
import com.scaler.bms.repository.TicketSeatRepository;
import com.scaler.bms.services.interfaces.TicketSeatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketSeatServiceImpl implements TicketSeatService {

    private final TicketSeatRepository ticketSeatRepository;

    public TicketSeatServiceImpl(TicketSeatRepository tr) {
        this.ticketSeatRepository = tr;
    }


    @Override
    public List<TicketSeat> save(TicketSeatReqDTO ticketSeatDTO) {
        try {
            List<TicketSeat> ticketSeatList = new ArrayList<>();
            TicketSeatMapper ticketSeatMapper = new TicketSeatMapper();
            List<ShowSeat> ticketSeats = ticketSeatDTO.getShowSeatList();
            for (ShowSeat showSeat : ticketSeats) {
                TicketSeat ticketSeatEntity = ticketSeatMapper.toEntity(ticketSeatDTO, showSeat);
                ticketSeatList.add(this.ticketSeatRepository.save(ticketSeatEntity));
            }
            return ticketSeatList;
        } catch (Exception ex) {
            throw new InvalidTicketException("Exception in ticket creation" + ex.getMessage());
        }
    }
}
