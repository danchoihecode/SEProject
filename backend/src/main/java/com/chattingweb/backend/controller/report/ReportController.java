package com.chattingweb.backend.controller.report;

import com.chattingweb.backend.entities.response.ReportDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chattingweb.backend.entities.admin.Report;
import com.chattingweb.backend.services.report.CreateReportRequest;
import com.chattingweb.backend.services.report.ReportService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

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
}