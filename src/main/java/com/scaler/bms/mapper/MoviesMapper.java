package com.scaler.bms.mapper;

import com.scaler.bms.dto.MoviesResDTO;
import com.scaler.bms.entity.Movies;

public class MoviesMapper implements ContextMapper<MoviesResDTO, Movies> {


    @Override
    public Movies toEntity(MoviesResDTO dto) {
        return null;
    }

    @Override
    public MoviesResDTO toDTO(Movies entity) {
        MoviesResDTO moviesResDTO = new MoviesResDTO();
        moviesResDTO.setMovieId(entity.getId());
        moviesResDTO.setName(entity.getName());
        moviesResDTO.setLanguage(entity.getLanguage());
        moviesResDTO.setCreatedAt(entity.getCreatedAt());
        return moviesResDTO;
    }
}
