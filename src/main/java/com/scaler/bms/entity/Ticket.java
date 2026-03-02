package com.scaler.bms.entity;

import com.scaler.bms.constants.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Shows shows;

    //    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    private Double ticketNo;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;

    @OneToMany(mappedBy = "ticket")
    private List<Payment> paymentList;

    private Long price;
}
