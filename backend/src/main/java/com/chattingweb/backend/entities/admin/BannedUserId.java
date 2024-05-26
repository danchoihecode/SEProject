package com.chattingweb.backend.entities.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class BannedUserId implements Serializable {
    private static final long serialVersionUID = 3196421244974325034L;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull
    @Column(name = "band_date", nullable = false)
    private Instant bandDate;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Instant getBandDate() {
        return bandDate;
    }

    public void setBandDate(Instant bandDate) {
        this.bandDate = bandDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BannedUserId entity = (BannedUserId) o;
        return Objects.equals(this.bandDate, entity.bandDate) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bandDate, userId);
    }

}