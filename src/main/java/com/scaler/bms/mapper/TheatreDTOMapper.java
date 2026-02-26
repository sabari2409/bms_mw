package com.scaler.bms.mapper;

import com.scaler.bms.dto.TheatreReqDTO;
import com.scaler.bms.dto.TheatreResDTO;
import com.scaler.bms.entity.City;
import com.scaler.bms.entity.Theatre;

import java.time.LocalDateTime;
import java.util.Date;

public class TheatreDTOMapper {

    public static Theatre toTheatreEntity(TheatreReqDTO req, City c) {
        Theatre t = new Theatre();
        t.setAddress(req.getAddress());
        t.setName(req.getName());
        t.setCity(c);
        t.setCreatedAt(LocalDateTime.now());
        t.setUpdatedAt(LocalDateTime.now());
        return t;
    }

    public static TheatreResDTO toTheatreDTO(Theatre resp) {
        TheatreResDTO tr = new TheatreResDTO();
        tr.setAddress(resp.getAddress());
        tr.setId(resp.getId());
        tr.setName(resp.getName());
        tr.setAuditoriums(resp.getAuditoriums());
        return tr;
    }
}
