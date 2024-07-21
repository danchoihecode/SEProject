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


}
