package com.example.medicalcloud.repositories;

import com.example.medicalcloud.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
