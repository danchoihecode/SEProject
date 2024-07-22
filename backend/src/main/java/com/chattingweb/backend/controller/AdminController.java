package com.chattingweb.backend.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chattingweb.backend.entities.admin.BannedUser;
import com.chattingweb.backend.entities.admin.DeletedPost;
import com.chattingweb.backend.entities.admin.Report;
import com.chattingweb.backend.repository.admin.ReportRepository;
import com.chattingweb.backend.services.admin.AdminService;
import com.chattingweb.backend.services.admin.ApproveReport;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;
	private final ReportRepository reportRepository;

	public AdminController(AdminService adminService, ReportRepository reportRepository) {
		this.adminService = adminService;
		this.reportRepository = reportRepository;
	}

	@PostMapping("/approveReport/{reportID}")
	public ResponseEntity<String> banUser(@PathVariable Long reportID, @RequestBody ApproveReport approveReport) {
		Optional<Report> report = reportRepository.findById(reportID);
		if (report.isPresent()) {
			try {
				adminService.approveReport(report.get(), approveReport);
				return ResponseEntity.ok("Deleted successfully");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.notFound().build();
			}

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/declineReport/{reportID}")
	public ResponseEntity<Void> banUser(@PathVariable Long reportID) {
		Optional<Report> report = reportRepository.findById(reportID);
		if (report.isPresent()) {
			try {
				adminService.declineReport(report.get());
				return ResponseEntity.ok().build();
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}

	}
}
