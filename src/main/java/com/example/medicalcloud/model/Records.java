package com.example.medicalcloud.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.example.medicalcloud.model.RecordStatus.RECEPTION_IS_OVER;
@Component
@Entity
@Data
@NoArgsConstructor
@Slf4j
@Table(name="record")
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime dateRecord;
    private int office;
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    private Patient patient;
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false)
    private Doctor doctor;
    @Enumerated
    private RecordStatus recordStatus;

    public Records(LocalDateTime dateRecord, int office, RecordStatus recordStatus) {
        this.dateRecord = dateRecord;
        this.office = office;
        this.recordStatus = recordStatus;
    }

    @Column(columnDefinition = "integer default 0")
    private int indicateRecord;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;

    @PreUpdate
    public void postUpdateCalculateTime() {
        if (recordStatus == RECEPTION_IS_OVER) {
            log.info("Info:{},{}", dateRecord.getHour(), lastUpdatedOn.getHour());
            if (lastUpdatedOn.getHour() - dateRecord.getHour() >= 1) {
                indicateRecord = 1;
            }
        }
    }
}
