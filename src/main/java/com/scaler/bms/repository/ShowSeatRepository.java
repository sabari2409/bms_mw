package com.scaler.bms.repository;

import com.scaler.bms.entity.ShowSeat;
import com.scaler.bms.projections.ShowSeatProjection;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from ShowSeat s where s.id IN :ids")
    List<ShowSeat> findShowSeatsByIds(@Param("ids") List<Integer> ids);

}
