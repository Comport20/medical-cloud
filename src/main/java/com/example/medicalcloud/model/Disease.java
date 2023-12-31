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
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "diseases")
    private List<MedicalHistory> medicalHistories = new ArrayList<>();
    public void addMedicalHistory(MedicalHistory medicalHistory){
        this.medicalHistories.add(medicalHistory);
    }
    public Disease(String title) {
        this.title = title;
    }
}
