package com.chattingweb.backend.controller;

import com.chattingweb.backend.entities.admin.BannedUser;
import com.chattingweb.backend.entities.admin.DeletedPost;
import com.chattingweb.backend.entities.admin.Report;

import com.chattingweb.backend.repository.admin.ReportRepository;
import com.chattingweb.backend.services.admin.AdminService;
import com.chattingweb.backend.services.admin.ApproveReport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final ReportRepository reportRepository;

    public AdminController( AdminService adminService, ReportRepository reportRepository) {
        this.adminService=adminService;
        this.reportRepository=reportRepository;
    }


    @PostMapping("/approveReport/{reportID}")
    public ResponseEntity<BannedUser> banUser (@PathVariable Long reportId, @RequestBody ApproveReport approveReport ){
        Optional<Report> report = reportRepository.findById(reportId);
        if(report.isPresent()){
            adminService.approveReport(report.get(),approveReport);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/declineReport/{reportID}")
    public ResponseEntity<DeletedPost> banUser (@PathVariable Long reportId){
        Optional<Report> report = reportRepository.findById(reportId);
        if(report.isPresent()){
            adminService.declineReport(report.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
