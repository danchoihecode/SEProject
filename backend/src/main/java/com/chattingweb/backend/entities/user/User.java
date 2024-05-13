package com.chattingweb.backend.entities.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "UserID", nullable = false)
    private Integer id;

    @Column(name = "UserFullName", nullable = false, length = Integer.MAX_VALUE)
    private String userFullName;

    @Column(name = "UserNickName", nullable = false, length = Integer.MAX_VALUE)
    private String userNickName;

    @Column(name = "UserAvatar")
    private byte[] userAvatar;

    @Column(name = "UserEmail", nullable = false, length = Integer.MAX_VALUE)
    private String userEmail;

    @Column(name = "UserPassword", nullable = false, length = Integer.MAX_VALUE)
    private String userPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}