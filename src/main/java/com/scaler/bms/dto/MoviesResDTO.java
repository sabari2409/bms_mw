package com.scaler.bms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoviesResDTO {

    private Integer movieId;
    private String name;
    private String language;
    private LocalDateTime createdAt;
}
