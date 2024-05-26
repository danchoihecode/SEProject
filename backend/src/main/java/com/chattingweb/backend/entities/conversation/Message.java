package com.chattingweb.backend.entities.conversation;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @ColumnDefault("nextval('message_message_id_seq'::regclass)")
    @Column(name = "message_id", nullable = false)
    private Long id;

    @Column(name = "message_file")
    private byte[] messageFile;

    @Column(name = "message_content", length = Integer.MAX_VALUE)
    private String messageContent;

    @Column(name = "message_image")
    private byte[] messageImage;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "message_date", nullable = false)
    private Instant messageDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

}