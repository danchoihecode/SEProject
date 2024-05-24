package com.chattingweb.backend.entities.conversation;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConversationMemberId implements Serializable {
    private static final long serialVersionUID = 5300915410688434403L;
    @Column(name = "userid", nullable = false)
    private Integer userID;

    @Column(name = "conversationid", nullable = false)
    private Integer conversationID;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getConversationID() {
        return conversationID;
    }

    public void setConversationID(Integer conversationID) {
        this.conversationID = conversationID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ConversationMemberId entity = (ConversationMemberId) o;
        return Objects.equals(this.conversationID, entity.conversationID) &&
                Objects.equals(this.userID, entity.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationID, userID);
    }

}