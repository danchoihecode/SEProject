package com.chattingweb.backend.entities.conversation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    @Column(name = "conversationid", nullable = false)
    private Integer id;

    @Column(name = "isgroup", nullable = false)
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