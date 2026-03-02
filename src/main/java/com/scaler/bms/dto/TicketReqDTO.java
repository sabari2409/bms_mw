package com.scaler.bms.dto;

import com.scaler.bms.constants.TicketStatus;
import com.scaler.bms.entity.Shows;
import com.scaler.bms.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TicketReqDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Shows shows; // TODO: we need to update as DTO and not entity directly - NEED TO BE CHANGED
    private Users users; // TODO: we need to update as DTO and not entity directly - NEED TO BE CHANGED
    private TicketStatus ticketStatus;
    private Long price;
    private Double ticketNo;
}
