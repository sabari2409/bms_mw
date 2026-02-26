package com.scaler.bms.dto;

import com.scaler.bms.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuditoriumResDTO {

    private Integer auditoriumId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private List<Seat> seats;
    private TheatreResDTO theatreResDTO;
}
