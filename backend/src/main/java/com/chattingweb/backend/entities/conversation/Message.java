package com.chattingweb.backend.entities.conversation;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "messageid", nullable = false)
    private Integer id;

    @Column(name = "messagefile")
    private byte[] messageFile;

    @Column(name = "messagecontent", length = Integer.MAX_VALUE)
    private String messageContent;

    @Column(name = "messageimage")
    private byte[] messageImage;

    @ColumnDefault("now()")
    @Column(name = "messagedate", nullable = false)
    private Instant messageDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "senderid", nullable = false)
    private User senderID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversationid")
    private Conversation conversationID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getMessageFile() {
        return messageFile;
    }

    public void setMessageFile(byte[] messageFile) {
        this.messageFile = messageFile;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public byte[] getMessageImage() {
        return messageImage;
    }

    public void setMessageImage(byte[] messageImage) {
        this.messageImage = messageImage;
    }

    public Instant getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Instant messageDate) {
        this.messageDate = messageDate;
    }

    public User getSenderID() {
        return senderID;
    }

    public void setSenderID(User senderID) {
        this.senderID = senderID;
    }

    public Conversation getConversationID() {
        return conversationID;
    }

    public void setConversationID(Conversation conversationID) {
        this.conversationID = conversationID;
    }

}