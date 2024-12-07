package com.chat.demo.entity.DTO;

public class SignalMessage {

    private String type;  // "offer", "answer", "candidate"
    private String sdp;   // SDP 信息（用于 offer 和 answer）
    private String candidate;  // ICE candidate 信息

    private Long chatRoomId; // 聊天室 ID，用于区分不同的会话

    // Getters 和 Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSdp() {
        return sdp;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
}
