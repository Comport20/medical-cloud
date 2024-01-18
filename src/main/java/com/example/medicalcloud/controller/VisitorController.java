package com.example.medicalcloud.controller;

import com.example.medicalcloud.model.RecordStatus;
import com.example.medicalcloud.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitor")
public class VisitorController {
    @Autowired
    RecordRepository recordRepository;
    @GetMapping()
    public String getVisitor(Model model){
        model.addAttribute("listRecordStatus", recordRepository.findByRecordStatus(RecordStatus.RECEPTION_IS_OVER));
        return "visitor";
    }
}
