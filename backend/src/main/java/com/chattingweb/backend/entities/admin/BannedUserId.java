package com.chattingweb.backend.entities.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class BannedUserId implements Serializable {
    private static final long serialVersionUID = 3505288091394310832L;
    @Column(name = "userid", nullable = false)
    private Integer userID;

    @Column(name = "bandate", nullable = false)
    private Instant banDate;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Instant getBanDate() {
        return banDate;
    }

    public void setBanDate(Instant banDate) {
        this.banDate = banDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BannedUserId entity = (BannedUserId) o;
        return Objects.equals(this.banDate, entity.banDate) &&
                Objects.equals(this.userID, entity.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banDate, userID);
    }

}