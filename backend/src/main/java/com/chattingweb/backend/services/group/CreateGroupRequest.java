package com.chattingweb.backend.services.group;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateGroupRequest {
	
	private MultipartFile groupAvatar;
	private UUID conversationId;
	@NotBlank
	@Size(max = 100,message = "Length of group name must be below 100")	
	private String groupName;
	
	@NotBlank
	private UUID ownerId;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public MultipartFile getGroupAvatar() {
		return groupAvatar;
	}

	public void setGroupAvatar(MultipartFile groupAvatar) {
		this.groupAvatar = groupAvatar;
	}

	public UUID getConversationId() {
		return conversationId;
	}

	public void setConversationId(UUID conversationId) {
		this.conversationId = conversationId;
	}

	public UUID getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(UUID ownerId) {
		this.ownerId = ownerId;
	}
}
