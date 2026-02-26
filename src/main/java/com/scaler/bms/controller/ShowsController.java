package com.scaler.bms.controller;

import com.scaler.bms.projections.MovieShowResponseProjection;
import com.scaler.bms.dto.MoviesResDTO;
import com.scaler.bms.services.interfaces.ShowsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowsController {

    private final ShowsService showsService;


    public ShowsController(ShowsService showsService) {

        System.out.println("Inside shows controller");
        this.showsService = showsService;
    }


    @GetMapping("/{movieId}")
    public ResponseEntity<List<MovieShowResponseProjection>> findAllShowsByMovieId(@PathVariable Integer movieId) {
        // Check if movie id is valid or not.
        MoviesResDTO movies = this.showsService.findMovieById(movieId);
        System.out.println("path variable id -->"+ movieId);
        System.out.println("DB Movie Id -->" + movies.getMovieId());
        List<MovieShowResponseProjection> response =  this.showsService.findAllShowsByMovieId(movieId);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
