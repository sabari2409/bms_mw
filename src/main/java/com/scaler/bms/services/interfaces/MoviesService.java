package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.MoviesResDTO;
import com.scaler.bms.entity.Movies;
import com.scaler.bms.projections.MoviesProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesService {

    MoviesResDTO findMovieById(Integer movieId);

    List<MoviesProjection> findAllMoviesByLanguage(String lang);
}
