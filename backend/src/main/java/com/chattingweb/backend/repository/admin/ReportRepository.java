package com.chattingweb.backend.repository.admin;

import com.chattingweb.backend.entities.admin.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}