package com.chattingweb.backend.entities.conversation;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "conversation_id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "is_group", nullable = false)
    private Boolean isGroup = false;

    @OneToOne(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Group group;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConversationMember> conversationMembers = new ArrayList<>();

    // getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<ConversationMember> getConversationMembers() {
        return conversationMembers;
    }

    public void setConversationMembers(List<ConversationMember> conversationMembers) {
        this.conversationMembers = conversationMembers;
    }
}
