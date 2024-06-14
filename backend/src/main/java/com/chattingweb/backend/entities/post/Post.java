package com.chattingweb.backend.entities.post;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @ColumnDefault("nextval('post_post_id_seq'::regclass)")
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(name = "post_image")
    private byte[] postImage;

    @Column(name = "post_text", length = Integer.MAX_VALUE)
    private String postText;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "post_date", nullable = false)
    private Instant postDate;

    @ColumnDefault("0")
    @Column(name = "no_like")
    private Integer noLike;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}