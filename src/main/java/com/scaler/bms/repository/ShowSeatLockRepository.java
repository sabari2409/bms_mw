package com.scaler.bms.repository;

import com.scaler.bms.entity.ShowSeatLock;
import com.scaler.bms.projections.ShowSeatLockProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShowSeatLockRepository extends JpaRepository<ShowSeatLock, Integer> {

    @Query(value = """
            SELECT s.id as lockId,
             a.uuid as uniqueId,
             a.lock_created_at as lockCreatedDate,
             a.lock_status as lockStatus
             FROM ShowSeatLock s where s.uuid = :uuid
            """, nativeQuery = true)
    Optional<ShowSeatLockProjection> findLockDetailsByUUID(@Param("UUID") String uuid);
}
