package com.chattingweb.backend.entities.conversation;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
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

}