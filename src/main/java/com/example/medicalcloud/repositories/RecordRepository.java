package com.example.medicalcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medicalcloud.model.Record;
public interface RecordRepository extends JpaRepository<Record, Long> {

}
