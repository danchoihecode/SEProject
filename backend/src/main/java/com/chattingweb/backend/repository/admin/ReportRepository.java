package com.chattingweb.backend.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.chattingweb.backend.entities.admin.Report;

import jakarta.transaction.Transactional;

public interface ReportRepository extends JpaRepository<Report, Long> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM report WHERE post_id = :postId", nativeQuery = true)
	void deleteAllByPostId(Long postId);
}