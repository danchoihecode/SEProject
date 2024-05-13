package com.chattingweb.backend.entities.post;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @Column(name = "PostID", nullable = false)
    private Integer id;

    @Column(name = "PostImage")
    private byte[] postImage;

    @Column(name = "PostText", length = Integer.MAX_VALUE)
    private String postText;

    @ColumnDefault("now()")
    @Column(name = "PostDate", nullable = false)
    private Instant postDate;

    @ColumnDefault("0")
    @Column(name = "Like")
    private Integer like;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
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

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

}