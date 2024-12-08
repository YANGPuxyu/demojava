package com.chat.demo.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "attachments") // 指定数据库表名为 attachments
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    @Column(name = "file_name", nullable = false)
    private String fileName; // 文件名

    @Column(name = "file_type", nullable = false)
    private String fileType; // 文件类型（图片、文档、视频等）

    @Column(name = "file_url", nullable = false)
    private String fileUrl; // 文件存储的 URL 或路径

    @CreatedDate
    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt; // 上传时间

    // Many-to-One 关系，多个附件属于同一个聊天室
    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false) // 外键字段
    private ChatRoom chatRoom;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
