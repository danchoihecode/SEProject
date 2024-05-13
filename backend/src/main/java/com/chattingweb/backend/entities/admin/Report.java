package com.chattingweb.backend.entities.admin;

import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Report")
public class Report {
    @Id
    @Column(name = "ReportID", nullable = false)
    private Integer id;

    @Column(name = "ReportReason", nullable = false, length = Integer.MAX_VALUE)
    private String reportReason;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PostID", nullable = false)
    private Post postID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Post getPostID() {
        return postID;
    }

    public void setPostID(Post postID) {
        this.postID = postID;
    }

}