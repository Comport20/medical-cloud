package com.example.medicalcloud.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Doctor {
    public Doctor(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String fullName;

    private String login;

    private String password;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private Specialization specialization;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "doctor"
    )
    private List<Records> records = new ArrayList<>();

    public void addRecords(Records records){
        this.records.add(records);
    }
}
