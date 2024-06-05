package com.chattingweb.backend.entities.conversation;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
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

}