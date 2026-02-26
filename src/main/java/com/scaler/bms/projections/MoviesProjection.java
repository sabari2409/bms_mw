package com.scaler.bms.projections;

import java.time.LocalDateTime;

public interface MoviesProjection {
    Integer getId();
    String getName();
    LocalDateTime getCreatedAt();
}
