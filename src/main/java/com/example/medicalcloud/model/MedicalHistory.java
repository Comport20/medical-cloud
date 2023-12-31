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
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String description;

    public MedicalHistory(String description) {
        this.description = description;
    }

    @OneToOne(mappedBy = "medicalHistory")
    private Patient patient;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Medical_History_Disease",
            joinColumns = @JoinColumn(name = "MedicalHistory_ID"),
            inverseJoinColumns = @JoinColumn(name = "Disease_ID")
    )
    private List<Disease> diseases = new ArrayList<>();

    public void addDisease(Disease disease){
        diseases.add(disease);
    }
}
