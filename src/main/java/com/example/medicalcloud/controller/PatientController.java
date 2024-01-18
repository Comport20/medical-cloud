package com.example.medicalcloud.controller;

import com.example.medicalcloud.model.RecordStatus;
import com.example.medicalcloud.model.Records;
import com.example.medicalcloud.repositories.PatientRepository;
import com.example.medicalcloud.repositories.RecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private final PatientRepository patientRepository;
    private final RecordRepository recordRepository;
    private Long idR;

    public PatientController(PatientRepository patientRepository, RecordRepository recordRepository) {
        this.patientRepository = patientRepository;
        this.recordRepository = recordRepository;
    }
    @GetMapping()
    public String getPatient(@RequestParam("idP") Long idP,@RequestParam("idR") Long idR,Model model){
        this.idR = idR;
        Records records = recordRepository.findById(idR).get();
        records.setRecordStatus(RecordStatus.RECEPTION);
        recordRepository.save(records);
        model.addAttribute("patientObj",patientRepository.findById(idP).get());
        return "patient";
    }
    @PostMapping()
    public String postUpdateAndCheck(){
        Records records = recordRepository.findById(idR).get();
        records.setRecordStatus(RecordStatus.RECEPTION_IS_OVER);
        recordRepository.save(records);
        records = recordRepository.findById(idR).get();
        if(records.getIndicateRecord() == 1){
            recordRepository.updateByLocalDateTime(records.getDateRecord().toLocalDate());
        }
        return "redirect:/inspection";
    }
}
