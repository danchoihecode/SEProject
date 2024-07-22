package com.chattingweb.backend.controller.report;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chattingweb.backend.entities.admin.Report;
import com.chattingweb.backend.entities.response.ReportDTO;
import com.chattingweb.backend.entities.response.UserDTO;
import com.chattingweb.backend.services.report.CreateReportRequest;
import com.chattingweb.backend.services.report.ReportService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Authentication")
@RequestMapping("/")
public class ReportController {
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/reports")
    public ResponseEntity<Report> createReport(@RequestBody CreateReportRequest createReportRequest) {
        Report savedReport = reportService.createReport(createReportRequest);
        return ResponseEntity.ok(savedReport);
    }

    @GetMapping("/report_list")
    public ResponseEntity<List<ReportDTO>> reportList(){
        List<ReportDTO> reports= reportService.reportList();
        return ResponseEntity.ok(reports);
    }
    
    @GetMapping("/banned_list")
    public ResponseEntity<List<UserDTO>> bannedList(){
        List<UserDTO> reports= reportService.bannedList();
        return ResponseEntity.ok(reports);
    }
}