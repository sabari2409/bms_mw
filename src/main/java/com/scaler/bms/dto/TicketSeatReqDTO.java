package com.scaler.bms.dto;

import com.scaler.bms.entity.ShowSeat;
import com.scaler.bms.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketSeatReqDTO {

    private Ticket ticket;
    private List<ShowSeat> showSeatList;
}
