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
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String fullName;

    private String login;

    private String password;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false
    )
    private Specialization specialization;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "doctor"
    )
    private List<Record> records = new ArrayList<>();
}
