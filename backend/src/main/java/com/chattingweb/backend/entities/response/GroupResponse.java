package com.chattingweb.backend.entities.response;

public class GroupResponse {
String message;

public GroupResponse(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

}
