package com.chattingweb.backend.entities.conversation;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "ConversationMembers")
public class ConversationMember {
    @EmbeddedId
    private ConversationMemberId id;

    @MapsId("userID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @MapsId("conversationID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ConversationID", nullable = false)
    private Conversation conversationID;

    @ColumnDefault("false")
    @Column(name = "HasRead", nullable = false)
    private Boolean hasRead = false;

    public ConversationMemberId getId() {
        return id;
    }

    public void setId(ConversationMemberId id) {
        this.id = id;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Conversation getConversationID() {
        return conversationID;
    }

    public void setConversationID(Conversation conversationID) {
        this.conversationID = conversationID;
    }

    public Boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }

}