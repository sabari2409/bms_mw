package com.scaler.bms.services.impl;

import com.scaler.bms.entity.Shows;
import com.scaler.bms.projections.MovieShowResponseProjection;
import com.scaler.bms.dto.MoviesResDTO;
import com.scaler.bms.exception.InvalidMovieException;
import com.scaler.bms.exception.InvalidShowsException;
import com.scaler.bms.repository.ShowsRepository;
import com.scaler.bms.services.interfaces.MoviesService;
import com.scaler.bms.services.interfaces.ShowsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowsServiceImpl implements ShowsService {

    private final ShowsRepository showsRepository;
    private final MoviesService moviesService;

    public ShowsServiceImpl(ShowsRepository sr, MoviesService ms) {
        this.showsRepository = sr;
        this.moviesService = ms;
    }


    /**
     * findAllShowsByMovieId
     *
     * @param movieId
     * @return
     */
    @Override
    public List<MovieShowResponseProjection> findAllShowsByMovieId(Integer movieId) {
        System.out.println("Service impl movieId -->" + movieId);
        if (movieId == null) {
            throw new InvalidShowsException("Invalid movie");
        } else {
            return this.showsRepository.findAllShowsByMovieId(movieId);
        }
    }

    /**
     * findMovieById
     *
     * @param movieId
     * @return
     */
    @Override
    public MoviesResDTO findMovieById(Integer movieId) {
        if (movieId == null) {
            throw new InvalidShowsException("Invalid movie");
        }
        MoviesResDTO movie = this.moviesService.findMovieById(movieId);
        if (movie == null) {
            throw new InvalidMovieException("Movie not exist");
        }
        return movie;
    }


    /**
     * findShowsById
     * @param showId
     * @return
     */
    @Override
    public Shows findShowsById(Integer showId) {
        if (showId == null) {
            throw new InvalidShowsException("Invalid Show");
        }
        return this.showsRepository.findById(showId).orElseThrow(() -> new InvalidShowsException("Selected Show not available"));
    }
}
