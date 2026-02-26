package com.scaler.bms.services.interfaces;

import com.scaler.bms.dto.ShowSeatResDTO;
import com.scaler.bms.entity.ShowSeat;
import com.scaler.bms.projections.ShowSeatProjection;

import java.util.List;

public interface ShowSeatService {

    List<ShowSeat> findAllByIdInForUpdate(List<Integer> showSeatIds);

    ShowSeatResDTO updateShowSeat(ShowSeat showSeat);
}
