package com.example.medicalcloud.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medicalcloud.model.Records;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
public interface RecordRepository extends JpaRepository<Records, Long> {
    @Modifying
    @Transactional
    @Query(value = "update record set date_record = date_record + interval '1 hour' where DATE(dateRecord) = DATE(?1)", nativeQuery = true)
    void updateByLocalDateTime(LocalDateTime localDateTime);
}
