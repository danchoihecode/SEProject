package com.chattingweb.backend.services.post;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

public class CreatePostRequest {
	@NotBlank(message = "Text is required")
    private String postText;
	
	@NotBlank(message = "User id is required")
    private UUID userId;
	
	private Integer noLike;
	
    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

	public Integer getNoLike() {
		return noLike;
	}

	public void setNoLike(Integer noLike) {
		this.noLike = noLike;
	}
}