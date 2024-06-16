package com.chattingweb.backend.entities.conversation;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Setter
@Getter
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

}