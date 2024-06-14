package com.chattingweb.backend.modules;

import java.util.UUID;

public class FriendData {
    private UUID uuid;
    private String nickName;
    private byte[] avatar;

    public UUID getUuid() {
        return uuid;
    }

    public FriendData setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public FriendData setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public FriendData setAvatar(byte[] avatar) {
        this.avatar = avatar;
        return this;
    }

}
