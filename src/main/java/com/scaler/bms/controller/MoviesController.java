package com.scaler.bms.controller;

import com.scaler.bms.projections.MoviesProjection;
import com.scaler.bms.services.interfaces.MoviesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService ms) {
        this.moviesService = ms;
    }

    @GetMapping("/{lang}")
    public ResponseEntity<List<MoviesProjection>> getMoviesByLanguage(@PathVariable String lang) {
        List<MoviesProjection> response = this.moviesService.findAllMoviesByLanguage(lang);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
