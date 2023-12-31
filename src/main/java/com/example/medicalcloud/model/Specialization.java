package com.example.medicalcloud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nameSpecialization;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "specialization"
    )
    private List<Doctor> doctors = new ArrayList<>();
    public void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }
    public Specialization(String nameSpecialization) {
        this.nameSpecialization = nameSpecialization;
    }
}
