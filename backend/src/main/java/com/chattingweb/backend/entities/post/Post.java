package com.chattingweb.backend.entities.post;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "postid", nullable = false)
    private Integer id;

    @Column(name = "postimage")
    private byte[] postImage;

    @Column(name = "posttext", length = Integer.MAX_VALUE)
    private String postText;

    @ColumnDefault("now()")
    @Column(name = "postdate", nullable = false)
    private Instant postDate;

    @ColumnDefault("0")
    @Column(name = "nolike")
    private Integer noLike;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private User userID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPostImage() {
        return postImage;
    }

    public void setPostImage(byte[] postImage) {
        this.postImage = postImage;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Instant getPostDate() {
        return postDate;
    }

    public void setPostDate(Instant postDate) {
        this.postDate = postDate;
    }

    public Integer getNoLike() {
        return noLike;
    }

    public void setNoLike(Integer noLike) {
        this.noLike = noLike;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

}