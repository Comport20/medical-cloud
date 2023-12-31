package com.example.medicalcloud.model;

import com.example.medicalcloud.repositories.RecordRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

import static com.example.medicalcloud.model.RecordStatus.RECEPTION_IS_OVER;

@Entity
@Data
@NoArgsConstructor
@Table(name="record")
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime dateRecord;
    private int office;
    @ManyToOne(
            fetch = FetchType.LAZY,
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
    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;

    @PostUpdate
    public void postUpdateCalculateTime(){
        if(recordStatus == RECEPTION_IS_OVER){
            if(dateRecord.getHour() - lastUpdatedOn.getHour() > 1){
                updateByLocalDateTime(dateRecord);
            }
        }
    }
    @Modifying
    @Query(value = "update record set date_record = date_record + interval '1 hour' where DATE(dateRecord) = DATE(?1)",
            nativeQuery = true)
    public void updateByLocalDateTime(LocalDateTime localDateTime){
    }
}
