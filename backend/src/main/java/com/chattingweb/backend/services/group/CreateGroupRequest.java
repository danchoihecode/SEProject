package com.chattingweb.backend.services.group;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateGroupRequest {
	
	private UUID conversationId;
	@NotBlank
	@Size(max = 100,message = "Length of group name must be below 100")	
	private String groupName;
	
	@NotBlank
	private UUID ownerId;
	
	@NotBlank
	private List<UUID> selectedFriendIds;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public List<UUID> getSelectedFriendIds() {
		return selectedFriendIds;
	}

	public void setSelectedFriendIds(List<UUID> selectedFriendIds) {
		this.selectedFriendIds = selectedFriendIds;
	}
}
