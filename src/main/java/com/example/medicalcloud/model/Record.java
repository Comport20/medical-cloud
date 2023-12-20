package com.example.medicalcloud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate dateRecord;
    private int office;
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false,
            cascade = CascadeType.ALL
    )
    private Patient patient;
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false,
            cascade = CascadeType.ALL
    )
    private Doctor doctor;
    @Enumerated
    private RecordStatus recordStatus;
    @UpdateTimestamp
    private LocalDate lastUpdatedOn;
}
