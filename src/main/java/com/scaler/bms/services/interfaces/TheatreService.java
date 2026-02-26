package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.TheatreReqDTO;
import com.scaler.bms.dto.TheatreResDTO;
import com.scaler.bms.entity.Theatre;

public interface TheatreService {
    TheatreResDTO addNewTheatre(TheatreReqDTO req);

    Theatre findByTheatreId(Integer theatreId);
}
