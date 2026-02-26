package com.scaler.bms.entity;

import com.scaler.bms.constants.SeatStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Shows shows;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Enumerated(EnumType.ORDINAL)
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lock_id")
    private ShowSeatLock showSeatLock;


}
