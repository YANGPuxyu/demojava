package com.chat.demo.entity.DTO;

import jakarta.validation.constraints.NotBlank;

public class MessageDto {
    private Long id; // 消息 ID

    private Long chatRoomId; // 聊天室 ID

    private Long userId; // 用户 ID

    @NotBlank(message = "Content cannot be empty")
    private String content; // 消息内容

    @NotBlank(message = "Message type cannot be empty")
    private String messageType; // 消息类型

    private String createdAt; // 消息发送时间（返回格式化后的时间）

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
