package com.scaler.bms.repository;

import com.scaler.bms.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Ticket, Integer> {
}
