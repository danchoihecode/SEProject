package com.chattingweb.backend.entities.response;

public class UserDTO {

	private String userName;
	private String reason;
	private Integer duration;
	private String admin;

	public UserDTO(String userName, String reason, Integer duration, String admin) {
		super();
		this.userName = userName;
		this.reason = reason;
		this.duration = duration;
		this.admin = admin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	
	
	
	
	



}
