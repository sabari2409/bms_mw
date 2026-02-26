package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.AuditoriumReqDTO;
import com.scaler.bms.dto.AuditoriumResDTO;
import com.scaler.bms.entity.Auditorium;

import java.util.List;

public interface AuditoriumService {

    AuditoriumResDTO addNewAuditorium(AuditoriumReqDTO req);

    List<AuditoriumResDTO> getAllAuditoriums(Integer theatreId);

}
