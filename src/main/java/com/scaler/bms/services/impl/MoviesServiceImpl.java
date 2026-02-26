package com.scaler.bms.services.impl;

import com.scaler.bms.dto.MoviesResDTO;
import com.scaler.bms.entity.Movies;
import com.scaler.bms.exception.InvalidMovieException;
import com.scaler.bms.mapper.MoviesMapper;
import com.scaler.bms.projections.MoviesProjection;
import com.scaler.bms.repository.MoviesRepository;
import com.scaler.bms.services.interfaces.MoviesService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesServiceImpl implements MoviesService {

    private final MoviesRepository moviesRepository;

    public MoviesServiceImpl(MoviesRepository mr) {
        this.moviesRepository = mr;
    }


    @Override
    public MoviesResDTO findMovieById(Integer movieId) {
        MoviesMapper mapper = new MoviesMapper();
        return this.moviesRepository.findById(movieId)
                .map(mapper::toDTO)
                .orElseThrow(() -> new InvalidMovieException("Invalid Movie"));
    }


    @Override
    public List<MoviesProjection> findAllMoviesByLanguage(String lang) {
        if (lang == null) {
            throw new InvalidMovieException("Not a valid movie language");
        }
        Optional<List<MoviesProjection>> moviesProjection = this.moviesRepository.findAllMoviesByLanguage(lang);
        return moviesProjection.orElse(null);
    }

}
