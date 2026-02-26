package com.scaler.bms.services.impl;

import com.scaler.bms.dto.AuditoriumReqDTO;
import com.scaler.bms.dto.AuditoriumResDTO;
import com.scaler.bms.dto.TheatreResDTO;
import com.scaler.bms.entity.Auditorium;
import com.scaler.bms.entity.Theatre;
import com.scaler.bms.exception.AuditoriumException;
import com.scaler.bms.mapper.AuditoriumMapper;
import com.scaler.bms.repository.AuditoriumRepository;
import com.scaler.bms.services.interfaces.AuditoriumService;
import com.scaler.bms.services.interfaces.TheatreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    private final TheatreService theatreService;
    private final AuditoriumRepository auditoriumRepository;

    public AuditoriumServiceImpl(TheatreServiceImpl ts, AuditoriumRepository ar) {
        this.theatreService = ts;
        this.auditoriumRepository = ar;
    }


    @Override
    public AuditoriumResDTO addNewAuditorium(AuditoriumReqDTO req) {
        Theatre theatre = this.theatreService.findByTheatreId(req.getTheatreId());
        if (theatre == null) {
            throw new AuditoriumException("Invalid Theatre");
        }
        Auditorium auditorium = AuditoriumMapper.convertToAuditoriumEntity(req, theatre);
        Auditorium aResponse = this.auditoriumRepository.save(auditorium);
        return AuditoriumMapper.convertToAuditoriumDTO(aResponse);
    }

    @Override
    public List<AuditoriumResDTO> getAllAuditoriums(Integer theatreId) {
        return List.of();
    }
}
