package com.chattingweb.backend.entities.conversation;

import org.hibernate.annotations.ColumnDefault;

import com.chattingweb.backend.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "conversation_members")
public class ConversationMember {
    @EmbeddedId
    private ConversationMemberId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("conversationId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "has_read", nullable = false)
    private Boolean hasRead = false;

    // Constructors, getters and setters
    public ConversationMember() {}

    public ConversationMember(ConversationMemberId id, User user, Conversation conversation, @NotNull Boolean hasRead) {
        this.id = id;
        this.user = user;
        this.conversation = conversation;
        this.hasRead = hasRead;
    }

    public ConversationMemberId getId() {
        return id;
    }

    public void setId(ConversationMemberId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }
}
