package com.scaler.bms.mapper;

import com.scaler.bms.dto.AuditoriumReqDTO;
import com.scaler.bms.dto.AuditoriumResDTO;
import com.scaler.bms.dto.TheatreResDTO;
import com.scaler.bms.entity.Auditorium;
import com.scaler.bms.entity.Theatre;

import java.time.LocalDateTime;

public class AuditoriumMapper {

    /**
     * convertToAuditoriumEntity
     *
     * @param req
     * @param theatre
     * @return
     */
    public static Auditorium convertToAuditoriumEntity(AuditoriumReqDTO req, Theatre theatre) {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(req.getName());
        auditorium.setCreatedAt(LocalDateTime.now());
        auditorium.setUpdatedAt(LocalDateTime.now());
        auditorium.setTheatre(theatre);
        return auditorium;
    }

    public static AuditoriumResDTO convertToAuditoriumDTO(Auditorium auditorium) {
        AuditoriumResDTO auditoriumResDTO = new AuditoriumResDTO();
        TheatreResDTO theatreResDTO = new TheatreResDTO();

        //set theatre details:
        Theatre theatre = auditorium.getTheatre();
        theatreResDTO.setId(theatre.getId());
        theatreResDTO.setName(theatre.getName());
        theatreResDTO.setAddress(theatre.getAddress());

        // set auditorium details
        auditoriumResDTO.setAuditoriumId(auditorium.getId());
        auditoriumResDTO.setName(auditorium.getName());
        auditoriumResDTO.setSeats(auditorium.getSeats());
        auditoriumResDTO.setTheatreResDTO(theatreResDTO);
        auditoriumResDTO.setCreatedAt(auditorium.getCreatedAt());
        auditoriumResDTO.setUpdatedAt(auditorium.getUpdatedAt());
        return auditoriumResDTO;
    }

}
