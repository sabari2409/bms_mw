package com.scaler.bms.services.interfaces;

import com.scaler.bms.projections.MovieShowResponseProjection;
import com.scaler.bms.dto.MoviesResDTO;

import java.util.List;

public interface ShowsService {

    List<MovieShowResponseProjection> findAllShowsByMovieId(Integer movieId);

    MoviesResDTO findMovieById(Integer movieId);
}
