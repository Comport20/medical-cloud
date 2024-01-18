package com.example.medicalcloud.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String fullName;

    private int insurancePolicy;

    private int passport;

    @Column(length = 11)
    private long phone;

    private String email;

    public Patient(String fullName, int insurancePolicy, int passport, long phone, String email) {
        this.fullName = fullName;
        this.insurancePolicy = insurancePolicy;
        this.passport = passport;
        this.phone = phone;
        this.email = email;
    }

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "patient"
    )
    private List<Records> records = new ArrayList<>();
    public void addRecord(Records records){
        this.records.add(records);
    }
    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(unique = true)
    private MedicalHistory medicalHistory;
}
