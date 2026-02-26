package com.scaler.bms.services.impl;

import com.scaler.bms.dto.ShowSeatResDTO;
import com.scaler.bms.entity.ShowSeat;
import com.scaler.bms.exception.InvalidShowSeatException;
import com.scaler.bms.projections.ShowSeatProjection;
import com.scaler.bms.repository.ShowSeatRepository;
import com.scaler.bms.services.interfaces.ShowSeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {


    private final ShowSeatRepository showSeatRepository;

    public ShowSeatServiceImpl(ShowSeatRepository ssr) {
        this.showSeatRepository = ssr;
    }

    @Override
    public List<ShowSeat> findAllByIdInForUpdate(List<Integer> showSeatIds) {
        return this.showSeatRepository.findShowSeatsByIds(showSeatIds);
    }

    @Override
    public ShowSeatResDTO updateShowSeat(ShowSeat showSeat) {
//        ShowSeat repoResponse = this.showSeatRepository.(showSeat);
//        ShowSeatResDTO showSeatResDTO = new ShowSeatResDTO();
        return null;
    }
}
