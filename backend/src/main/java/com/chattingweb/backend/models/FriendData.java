package com.chattingweb.backend.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class FriendData {
    private UUID uuid;
    private String nickName;
    private byte[] avatar;

    public FriendData setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public FriendData setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public FriendData setAvatar(byte[] avatar) {
        this.avatar = avatar;
        return this;
    }

}
