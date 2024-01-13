package com.example.medicalcloud.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medicalcloud.model.Records;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
public interface RecordRepository extends JpaRepository<Records, Long> {
    @Modifying
    @Query(value = "update record set date_record = date_record + interval '1 hour'" +
            "where date(date_record) = ?1" +
            "and record_status = 0;",
            nativeQuery = true)
    int updateByLocalDateTime(LocalDate localDate);
}
