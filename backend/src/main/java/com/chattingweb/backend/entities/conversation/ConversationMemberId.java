package com.chattingweb.backend.entities.conversation;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class ConversationMemberId implements Serializable {
    private static final long serialVersionUID = -1330969740317256650L;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull
    @Column(name = "conversation_id", nullable = false)
    private UUID conversationId;

    

	public ConversationMemberId(@NotNull UUID userId, @NotNull UUID conversationId) {
		super();
		this.userId = userId;
		this.conversationId = conversationId;
	}

	public ConversationMemberId() {
		// TODO Auto-generated constructor stub
	}

	public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public void setConversationId(UUID conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ConversationMemberId entity = (ConversationMemberId) o;
        return Objects.equals(this.conversationId, entity.conversationId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId, userId);
    }

}