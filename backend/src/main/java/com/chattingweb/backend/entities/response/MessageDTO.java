package com.chattingweb.backend.entities.response;

import java.time.Instant;
import java.util.UUID;

public class MessageDTO {
	private Long id;
	private String messageContent;
	private Instant messageDate;
	private UUID senderId;

	public MessageDTO(Long id, String messageContent, Instant messageDate, UUID senderId) {
		this.id = id;
		this.messageContent = messageContent;
		this.messageDate = messageDate;
		this.senderId = senderId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Instant getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Instant messageDate) {
		this.messageDate = messageDate;
	}

	public UUID getSenderId() {
		return senderId;
	}

	public void setSenderId(UUID senderId) {
		this.senderId = senderId;
	}
}
