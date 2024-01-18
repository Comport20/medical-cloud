package com.example.medicalcloud.repositories;

import com.example.medicalcloud.model.RecordStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medicalcloud.model.Records;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RecordRepository extends JpaRepository<Records, Long> {
    @Modifying
    @Transactional
    @Query(value = "update record set date_record = date_record + interval '1 hour' where date(date_record) = ?1 and record_status = 0",
            nativeQuery = true)
    int updateByLocalDateTime(LocalDate localDate);
    List<Records> findByRecordStatus(RecordStatus recordStatus);
}
