package com.chattingweb.backend.entities.admin;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "BannedUser")
public class BannedUser {
    @EmbeddedId
    private BannedUserId id;

    @MapsId("userID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @Column(name = "BanReason", nullable = false, length = Integer.MAX_VALUE)
    private String banReason;

    @Column(name = "Duration", nullable = false)
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AdminID", nullable = false)
    private Admin adminID;

    public BannedUserId getId() {
        return id;
    }

    public void setId(BannedUserId id) {
        this.id = id;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public String getBanReason() {
        return banReason;
    }

    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Admin getAdminID() {
        return adminID;
    }

    public void setAdminID(Admin adminID) {
        this.adminID = adminID;
    }

}