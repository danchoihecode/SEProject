package com.chattingweb.backend.entities.admin;

import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.entities.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @ColumnDefault("nextval('report_post_id_seq'::regclass)")
    @Column(name = "report_id", nullable = false)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "report_reason", nullable = false, length = 200)
    private String reportReason;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}