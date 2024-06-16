package com.chattingweb.backend.entities.conversation;

import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;

import com.chattingweb.backend.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	// getters and setters
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
