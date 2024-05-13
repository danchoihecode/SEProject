package com.chattingweb.backend.entities.admin;

import com.chattingweb.backend.entities.post.Post;
import jakarta.persistence.*;

@Entity
@Table(name = "DeletedPost")
public class DeletedPost {
    @Id
    @Column(name = "PostID", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PostID", nullable = false)
    private Post post;

    @Column(name = "DeleteReason", nullable = false, length = Integer.MAX_VALUE)
    private String deleteReason;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AdminID", nullable = false)
    private Admin adminID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public Admin getAdminID() {
        return adminID;
    }

    public void setAdminID(Admin adminID) {
        this.adminID = adminID;
    }

}