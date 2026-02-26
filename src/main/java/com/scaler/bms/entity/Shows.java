package com.scaler.bms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shows extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "movie_id") // FK
    private Movies movies;

    @ManyToOne
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium; // We are keeping this also as a unidirectional

    @OneToMany(mappedBy = "shows")
    private List<ShowSeat> showSeatList;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate showDate;

}
