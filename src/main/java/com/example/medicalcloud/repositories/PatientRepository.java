package com.example.medicalcloud.repositories;

import com.example.medicalcloud.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
