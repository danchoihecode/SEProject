package com.chattingweb.backend.services.report;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateReportRequest {
 	
 	@NotBlank(message = "Report reason is required")
    @Size(max = 200,message = "Length of report reason must be below 200")	
    private String reportReason;
 	
    @NotBlank(message = "User id is required")
    private UUID userId;
    
    @NotBlank(message = "Post id is required")
    private Long postId;

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
