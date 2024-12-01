package com.chat.demo.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages") // 指定数据库表名为 messages
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    @Column(name = "chat_room_id", nullable = false)
    private Long chatRoomId; // 聊天室 ID

    @Column(name = "user_id", nullable = false)
    private Long userId; // 用户 ID

    @Column(nullable = false)
    private String content; // 消息内容

    @Column(name = "message_type", nullable = false)
    private String messageType; // 消息类型（文本、图片、文件等）

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt; // 消息发送时间

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
