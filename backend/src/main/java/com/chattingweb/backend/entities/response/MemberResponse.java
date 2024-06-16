package com.chattingweb.backend.entities.response;

import java.util.UUID;

public class MemberResponse {
	private UUID id;
	private String fullName;
	private String nickName;
	private byte[] avatar;
	private String email;

	public MemberResponse(UUID id, String fullName, String nickName, byte[] avatar, String email) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.nickName = nickName;
		this.avatar = avatar;
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
