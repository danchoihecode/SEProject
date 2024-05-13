package com.chattingweb.backend.entities.post;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "PostReaction")
public class PostReaction {
    @EmbeddedId
    private PostReactionId id;

    @MapsId("userID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @MapsId("postID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PostID", nullable = false)
    private Post postID;

    public PostReactionId getId() {
        return id;
    }

    public void setId(PostReactionId id) {
        this.id = id;
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