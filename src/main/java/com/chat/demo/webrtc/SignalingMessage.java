package com.chat.demo.webrtc;

public class SignalingMessage {
    private String type; // 消息类型，如 "offer", "answer", "candidate"
    private String roomId; // 房间 ID
    private String sender; // 发送者 ID
    private Object payload; // 具体数据载荷

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "SignalingMessage{" +
                "type='" + type + '\'' +
                ", roomId='" + roomId + '\'' +
                ", sender='" + sender + '\'' +
                ", payload=" + payload +
                '}';
    }
}
