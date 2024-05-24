package com.chattingweb.backend.entities.post;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostReactionId implements Serializable {
    private static final long serialVersionUID = 6341251750546409230L;
    @Column(name = "userid", nullable = false)
    private Integer userID;

    @Column(name = "postid", nullable = false)
    private Integer postID;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostReactionId entity = (PostReactionId) o;
        return Objects.equals(this.postID, entity.postID) &&
                Objects.equals(this.userID, entity.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postID, userID);
    }

}