package com.chattingweb.backend.services.report;

import org.springframework.stereotype.Service;

import com.chattingweb.backend.entities.admin.Report;
import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.admin.ReportRepository;
import com.chattingweb.backend.repository.post.PostRepository;
import com.chattingweb.backend.repository.user.UserRepository;

@Service
public class ReportService {
	private final ReportRepository reportRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public ReportService(ReportRepository reportRepository, PostRepository postRepository,
			UserRepository userRepository) {
		this.reportRepository = reportRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

    public Report createReport(CreateReportRequest createReportRequest) {
    	Report report = new Report();
        report.setReportReason(createReportRequest.getReportReason());

        User user = userRepository.findById(createReportRequest.getUserId()).orElse(null);
        if (user == null) {
        	throw new IllegalArgumentException("User not found");
        }
        report.setUser(user);

        Post post = postRepository.findById(createReportRequest.getPostId()).orElse(null);
        if (post == null) {
        	throw new IllegalArgumentException("Post not found");
        }
        report.setPost(post);
        
        return reportRepository.save(report);
    }
}
