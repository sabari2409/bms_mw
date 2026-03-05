package com.scaler.bms.repository;

import com.scaler.bms.entity.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UPIPaymentRepository extends PaymentRepository {

    @Query(value = """
            select p from Payment p where p.ticketId=:ticketID
            """, nativeQuery = true)
    Optional<Payment> findPaymentsByTicket(@Param("ticketID") Integer ticketID);
}
