package com.scaler.bms.projections;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@Getter
//@Setter
//@NoArgsConstructor
//public class MovieShowResponseProjection {
//
//    private Integer theatreId;
//    private String theatreName;
//    private Integer auditoriumId;
//    private String auditoriumName;
//    private LocalDateTime showDate;
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//}

public interface MovieShowResponseProjection {
    Integer getAuditoriumId();

    String getAuditoriumName();

    Integer getTheatreId();

    String getTheatreName();

    LocalDate getShowDate();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

}
