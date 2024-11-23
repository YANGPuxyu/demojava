package com.chat.demo.entity.DTO;

import jakarta.validation.constraints.NotBlank;

public class ChatRoomDto {
    private Long id; // 聊天室 ID

    @NotBlank(message = "Chat room name cannot be empty")
    private String name; // 聊天室名称

    private Long courseId; // 课程 ID，可选

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
