package com.chat.demo.entity.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AttachmentDto {
    private Long id; // 附件 ID

    @NotNull(message = "Message ID cannot be null")
    private Long messageId; // 消息 ID

    @NotBlank(message = "File name cannot be empty")
    private String fileName; // 文件名

    @NotBlank(message = "File type cannot be empty")
    private String fileType; // 文件类型

    @NotBlank(message = "File URL cannot be empty")
    private String fileUrl; // 文件存储的 URL 或路径

    private String uploadedAt; // 上传时间（格式化返回）

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
