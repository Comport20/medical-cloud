package com.example.medicalcloud.repositories;

import com.example.medicalcloud.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
