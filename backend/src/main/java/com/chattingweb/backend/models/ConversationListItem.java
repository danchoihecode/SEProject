package com.chattingweb.backend.models;

import lombok.Getter;

import java.util.UUID;

public class ConversationListItem {
    @Getter
    private byte[] avatar;
    @Getter
    private String conversationName;
    @Getter
    private UUID conversationId;
    private boolean isGroup;
    private boolean isRead;
    @Getter
    private String latestMessage;

    public ConversationListItem(byte[] avatar, String conversationName,boolean isGroup ,UUID conversationId, boolean isRead) {
        this.avatar = avatar;
        this.conversationName = conversationName;
        this.isGroup = isGroup;
        this.conversationId = conversationId;
        this.isRead = isRead;
    }

    public boolean getIsGroup() {
        return isGroup;
    }

    public ConversationListItem setIsGroup(boolean group) {
        isGroup = group;
        return this;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public ConversationListItem setAvatar(byte[] avatar) {
        this.avatar = avatar;
        return this;
    }

    public ConversationListItem setConversationId(UUID conversationId) {
        this.conversationId = conversationId;
        return this;
    }

    public ConversationListItem setConversationName(String conversationName) {
        this.conversationName = conversationName;
        return this;
    }

    public ConversationListItem setIsRead(boolean read) {
        isRead = read;
        return this;
    }

    public ConversationListItem setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
        return this;
    }
}
