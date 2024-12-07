package com.chat.demo.entity.DTO;

import java.time.LocalDateTime;

public class FriendshipDto {
    private Long id;
    private Long userId;
    private Long friendId;
    private LocalDateTime createdAt;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser1Id() {
        return userId;
    }

    public void setUser1Id(Long userId) {
        this.userId = userId;
    }

    public Long getUser2Id() {
        return friendId;
    }

    public void setUser2Id(Long friendId) {
        this.friendId = friendId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}