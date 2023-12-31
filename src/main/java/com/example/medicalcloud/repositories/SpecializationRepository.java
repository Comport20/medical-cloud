package com.example.medicalcloud.repositories;

import com.example.medicalcloud.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<Specialization,Long> {
}
