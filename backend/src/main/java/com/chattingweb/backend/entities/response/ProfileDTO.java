package com.chattingweb.backend.entities.response;

import java.util.List;
import java.util.UUID;

import com.chattingweb.backend.entities.post.Post;

public class ProfileDTO {

	private UUID uuid;
	private String nickName;
	private String fullName;
	private byte[] avatar;
	private List<Post> postList;
	
	
	public ProfileDTO(UUID uuid, String nickName, String fullName, byte[] avatar, List<Post> postList) {
		super();
		this.uuid = uuid;
		this.nickName = nickName;
		this.fullName = fullName;
		this.avatar = avatar;
		this.postList = postList;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	public List<Post> getPostList() {
		return postList;
	}
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
	
	


}
