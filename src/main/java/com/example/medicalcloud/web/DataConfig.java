package com.example.medicalcloud.web;

import com.example.medicalcloud.model.*;
import com.example.medicalcloud.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Configuration
public class DataConfig {
    @Autowired
    public DiseaseRepository diseaseRepository;
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

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            int symbolPatient = 65;
            int office = 100;
            startData(symbolPatient, office);
        };
    }

    private void startData(int symbolPatient, int office) throws InterruptedException {
        Specialization specialization = new Specialization("Direction");
        Patient patient = generatePatient(symbolPatient++);
        MedicalHistory medicalHistory = new MedicalHistory("ill");
        Disease disease = new Disease("disease");
        Doctor doctor = new Doctor("name", "login", "password");
        saveData(specialization, doctor, medicalHistory, disease, patient);
        generateRecord(patient, doctor, office);
        loadRecordData(doctor, disease, office, symbolPatient);
    }

    private void saveData(Specialization specialization, Doctor doctor,
                          MedicalHistory medicalHistory, Disease disease,
                          Patient patient) {
        specialization.addDoctor(doctor);
        doctor.setSpecialization(specialization);
        specializationRepository.save(specialization);

        medicalHistory.addDisease(disease);
        disease.addMedicalHistory(medicalHistory);
        diseaseRepository.save(disease);
        medicalHistoryRepository.save(medicalHistory);

        patient.setMedicalHistory(medicalHistory);
        patientRepository.save(patient);
    }

    private void generateRecord(Patient patient, Doctor doctor, int office) {
        Records records = new Records(LocalDateTime.of(
                LocalDate.now(), LocalTime.of(
                        LocalTime.now().getHour() - 1,
                        LocalTime.now().getMinute(),
                        LocalTime.now().getSecond(),
                        LocalTime.now().getNano())), office, RecordStatus.RECORDED);
        records.setPatient(patient);
        records.setDoctor(doctor);
        recordRepository.save(records);
    }

    private void loadRecordData(Doctor doctor, Disease disease, int office, int symbolPatient) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread.sleep(1000);
            MedicalHistory medicalHistory1 = generateMedicalHistory(disease);
            Patient patient = generatePatient(symbolPatient++);
            medicalHistoryRepository.save(medicalHistory1);
            patientRepository.save(setMedicalHistoryPatient(medicalHistory1, patient));
            generateRecord(patient, doctor, office);
        }
    }

    private Patient generatePatient(int symbol) {
        return new Patient(String.valueOf((char) symbol),
                symbol + 10000, symbol + 10_00_0000,
                8_999_000_00_00L + symbol, symbol + "@");
    }

    private Patient setMedicalHistoryPatient(MedicalHistory medicalHistory, Patient patient) {
        patient.setMedicalHistory(medicalHistory);
        return patient;
    }
    private MedicalHistory generateMedicalHistory(Disease disease) {
        MedicalHistory medicalHistory = new MedicalHistory(disease.getTitle());
        medicalHistory.addDisease(disease);
        return medicalHistory;
    }
}


