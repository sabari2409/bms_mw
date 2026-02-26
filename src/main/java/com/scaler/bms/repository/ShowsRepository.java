package com.scaler.bms.repository;

import com.scaler.bms.projections.MovieShowResponseProjection;
import com.scaler.bms.entity.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowsRepository extends JpaRepository<Shows, Integer> {

    @Query(value = """
            select
            	audi.id as auditoriumId,
            	audi.name as auditoriumName,
            	audi.theatre_id as theatreId,
            	th.name as theatreName,
            	sh.show_date as showDate,
            	sh.start_time as startTime,
            	sh.end_time as endTime
            	from auditorium audi, shows sh, theatre th
            	where
            	sh.auditorium_id = audi.id and
            	audi.theatre_id = th.id and sh.movie_id = :movieId
            """, nativeQuery = true)
    List<MovieShowResponseProjection> findAllShowsByMovieId(Integer movieId);
}
