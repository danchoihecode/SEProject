package com.chattingweb.backend.models;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageData {
    private MessageType messageType;
    private String messageContent;
    private UUID senderUserId;
    private UUID conversationId;
    private String senderName;
}
