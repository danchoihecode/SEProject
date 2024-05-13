package com.chattingweb.backend.entities.user;

import jakarta.persistence.*;

@Entity
@Table(name = "Friends")
public class Friend {
    @EmbeddedId
    private FriendId id;

    @MapsId("userID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @MapsId("friendID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FriendID", nullable = false)
    private User friendID;

    public FriendId getId() {
        return id;
    }

    public void setId(FriendId id) {
        this.id = id;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public User getFriendID() {
        return friendID;
    }

    public void setFriendID(User friendID) {
        this.friendID = friendID;
    }

}