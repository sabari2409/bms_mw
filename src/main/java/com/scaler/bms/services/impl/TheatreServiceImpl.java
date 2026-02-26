package com.scaler.bms.services.impl;

import com.scaler.bms.dto.TheatreReqDTO;
import com.scaler.bms.dto.TheatreResDTO;
import com.scaler.bms.entity.City;
import com.scaler.bms.entity.Theatre;
import com.scaler.bms.mapper.TheatreDTOMapper;
import com.scaler.bms.repository.TheatreRepository;
import com.scaler.bms.services.interfaces.CityService;
import com.scaler.bms.services.interfaces.TheatreService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheatreServiceImpl implements TheatreService {


    private final TheatreRepository theatreRepository;
    private final CityService cityService;

    public TheatreServiceImpl(TheatreRepository theatreRepository, CityService cityService) {
        this.theatreRepository = theatreRepository;
        this.cityService = cityService;
    }

    /**
     * addNewTheatre
     *
     * @param req
     * @return
     */
    @Override
    public TheatreResDTO addNewTheatre(TheatreReqDTO req) {
        City c = cityService.findById(req.getCityId());
        Theatre tEntity = TheatreDTOMapper.toTheatreEntity(req, c);
        Theatre tResponse = theatreRepository.save(tEntity);
        return TheatreDTOMapper.toTheatreDTO(tResponse);
    }

    /**
     * findByTheatreId
     *
     * @param theatreId
     * @return
     */
    public Theatre findByTheatreId(Integer theatreId) {
        Optional<Theatre> t = theatreRepository.findById(theatreId);
        return t.orElse(null);
    }

}
