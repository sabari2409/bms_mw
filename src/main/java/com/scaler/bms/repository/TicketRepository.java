package com.scaler.bms.repository;

import com.scaler.bms.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Optional<Ticket> findByTicketNo(Double ticketNo);
}
