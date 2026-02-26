package com.scaler.bms.repository;

import com.scaler.bms.projections.MoviesProjection;
import com.scaler.bms.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {

    Optional<List<MoviesProjection>> findAllMoviesByLanguage(String language);
}
