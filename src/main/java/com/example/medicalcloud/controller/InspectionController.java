package com.example.medicalcloud.controller;

import com.example.medicalcloud.model.Records;
import com.example.medicalcloud.repositories.RecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inspection")
public class InspectionController {
    final RecordRepository recordRepository;

    public InspectionController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping
    public String getInspection(Model model){
        return "inspection";
    }
    @ModelAttribute(name="recordsList")
    public List<Records> recordsList(){
        return recordRepository.findAll();
    }
}
