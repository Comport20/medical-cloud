package com.example.medicalcloud;

import com.example.medicalcloud.model.*;
import com.example.medicalcloud.model.Records;
import com.example.medicalcloud.repositories.DoctorRepository;
import com.example.medicalcloud.repositories.PatientRepository;
import com.example.medicalcloud.repositories.RecordRepository;
import com.example.medicalcloud.repositories.SpecializationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MedicalCloudApplicationTests {
    @Autowired
    public RecordRepository recordRepository;
    @Autowired
    public PatientRepository patientRepository;
    @Autowired
    public DoctorRepository doctorRepository;
    @Autowired
    public SpecializationRepository specializationRepository;
    @Test
    void contextLoads() {
        Specialization specialization = new Specialization("Терапевт");
        Patient patient = new Patient("Алексей",123,123,1111111111,"@");
        MedicalHistory medicalHistory = new MedicalHistory("больной");
        Disease disease = new Disease("disease");
        Doctor doctor = new Doctor("name","login","password");
        Records records = new Records(LocalDateTime.now(),100,RecordStatus.RECORDED);
        patient.getRecords().add(records);
        specialization.getDoctors().add(doctor);
        medicalHistory.getDiseases().add(disease);
        doctor.getRecords().add(records);
        recordRepository.save(records);
    }

}
