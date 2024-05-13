package com.chattingweb.backend.entities.conversation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Conversation")
public class Conversation {
    @Id
    @Column(name = "ConversationID", nullable = false)
    private Integer id;

    @Column(name = "IsGroup", nullable = false)
    private Boolean isGroup = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

}