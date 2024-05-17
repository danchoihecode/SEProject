package com.chattingweb.backend.entities.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "adminid", nullable = false)
    private Integer id;

    @Column(name = "adminpassword", nullable = false, length = Integer.MAX_VALUE)
    private String adminPassword;

    @Column(name = "adminemail", nullable = false, length = Integer.MAX_VALUE)
    private String adminEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

}