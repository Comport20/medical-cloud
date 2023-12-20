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
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String fullName;

    private int insurancePolicy;

    private int passport;

    @Column(length = 11)
    private int phone;

    private String email;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "patient"
    )
    private List<Record> records = new ArrayList<>();

    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false,
            cascade = CascadeType.ALL
    )
    @JoinColumn(unique = true)
    private MedicalHistory medicalHistory;
}
