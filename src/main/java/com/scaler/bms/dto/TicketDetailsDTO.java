package com.scaler.bms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TicketDetailsDTO {

    private Double ticketNo;
    private Long price;
    private LocalDateTime createdDate;
    private List<String> setSeatDetails;
}
