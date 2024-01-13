package com.example.medicalcloud.controller;

import com.example.medicalcloud.model.Records;
import com.example.medicalcloud.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inspection")
public class InspectionController {
    @Autowired
    RecordRepository recordRepository;
    @GetMapping
    public String getInspection(){
        return "inspection";
    }
    @ModelAttribute
    public List<Records> recordsList(){
        return recordRepository.findAll();
    }
//    @PostMapping
//    public void postData(@RequestBody ){
//        
//    }
}
