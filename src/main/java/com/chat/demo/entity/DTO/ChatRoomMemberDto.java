package com.chat.demo.entity.DTO;

import jakarta.validation.constraints.NotNull;

public class ChatRoomMemberDto {
    private Long id; // 成员 ID

    @NotNull(message = "Chat room ID cannot be null")
    private Long chatRoomId; // 聊天室 ID

    @NotNull(message = "User ID cannot be null")
    private Long userId; // 用户 ID

    private String joinedAt; // 加入时间（返回格式化后的时间）

    private Boolean isActive; // 是否活跃

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
