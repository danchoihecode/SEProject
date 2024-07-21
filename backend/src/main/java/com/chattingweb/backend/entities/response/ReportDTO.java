package com.chattingweb.backend.entities.response;

public class ReportDTO {
    private Long reportID;
    private String reason;
    private String userName;
    private String postText;

    public ReportDTO(Long reportID, String reason, String userName, String postText) {
        this.reportID = reportID;
        this.reason=reason;
        this.userName=userName;
        this.postText=postText;
    }

	public Long getReportID() {
		return reportID;
	}

	public void setReportID(Long reportID) {
		this.reportID = reportID;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}
    
    


}
