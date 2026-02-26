package com.scaler.bms.repository;

import com.scaler.bms.dto.TheatreReqDTO;
import com.scaler.bms.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

    Theatre save(TheatreReqDTO request);
}
