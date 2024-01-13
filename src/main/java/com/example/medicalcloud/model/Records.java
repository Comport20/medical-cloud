package com.example.medicalcloud.model;

import com.example.medicalcloud.repositories.RecordRepository;
import com.example.medicalcloud.trigger.RecordsTrigger;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
            log.info("Info:{},{}",dateRecord.getHour(),lastUpdatedOn.getHour());
            if(lastUpdatedOn.getHour() - dateRecord.getHour()  >= 1){

            }
        }
    }
//    @Modifying
//    @Query(value = "update record set date_record = date_record + interval '1 hour'" +
//            "where date(date_record) = ?1" +
//            "and record_status = 0;",
//            nativeQuery = true)
//    public void updateByLocalDateTime(String localDate){}
}
