package com.scaler.bms.entity;

import com.scaler.bms.constants.LockStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ShowSeatLock extends BaseModel {

    private String UUID;
    private LocalDateTime lockCreatedAt;

    @Enumerated(EnumType.ORDINAL)
    private LockStatus lockStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "showSeatLock")
    private List<ShowSeat> showSeatList;

}
