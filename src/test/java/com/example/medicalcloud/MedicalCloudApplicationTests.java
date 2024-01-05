package com.example.medicalcloud;

import com.example.medicalcloud.model.*;
import com.example.medicalcloud.model.Records;
import com.example.medicalcloud.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    @Autowired
    public MedicalHistoryRepository medicalHistoryRepository;

    @Test
    void contextLoads() {

        Specialization specialization = new Specialization("Direction");
        Patient patient = new Patient("Алексей", 123345, 123, 1111111111, "patient@gmail.com");
        MedicalHistory medicalHistory = new MedicalHistory("больной");
        Disease disease = new Disease("disease");
        Doctor doctor = new Doctor("name", "login", "password");
        Records records = new Records(LocalDateTime.of(
                LocalDate.now(), LocalTime.of(
                        LocalTime.now().getHour() - 1,
                        LocalTime.now().getMinute(),
                        LocalTime.now().getSecond(),
                        LocalTime.now().getNano())),
                100, RecordStatus.RECORDED);

        specialization.addDoctor(doctor);
        doctor.setSpecialization(specialization);
        specializationRepository.save(specialization);

        medicalHistory.addDisease(disease);
        disease.addMedicalHistory(medicalHistory);
        medicalHistoryRepository.save(medicalHistory);

        patient.setMedicalHistory(medicalHistory);
        patientRepository.save(patient);

        records.setDoctor(doctor);
        records.setPatient(patient);

        recordRepository.save(records);
    }
}
