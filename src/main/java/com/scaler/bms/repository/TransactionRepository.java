package com.scaler.bms.repository;

import com.scaler.bms.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(
            value = """
                    select t from Transaction t where t.paymentId="paymentId ORDER BY transactionDate DESC
                    """
    )
    Optional<List<Transaction>> findAllById(@Param("paymentId") Integer paymentID);
}
