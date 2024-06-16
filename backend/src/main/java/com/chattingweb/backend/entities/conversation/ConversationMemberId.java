package com.chattingweb.backend.entities.conversation;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class ConversationMemberId implements Serializable {
    private static final long serialVersionUID = -1330969740317256650L;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull
    @Column(name = "conversation_id", nullable = false)
    private UUID conversationId;

    public ConversationMemberId(UUID userId, UUID conversationId) {
        this.userId = userId;
        this.conversationId = conversationId;
    }

    public ConversationMemberId() {

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