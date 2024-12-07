package com.chat.demo.service;

import com.chat.demo.entity.Attachment;
import com.chat.demo.entity.ChatRoom;
import com.chat.demo.entity.DTO.AttachmentDto;
import com.chat.demo.repository.AttachmentRepository;
import io.minio.errors.MinioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private FileUploadService fileUploadService; // 引入 FileUploadService

    private static final String BUCKET_NAME = "attachments"; // MinIO 存储桶名称
    // 获取聊天室的附件
    public List<AttachmentDto> getAttachmentsByChatRoomId(Long chatRoomId) {
        List<Attachment> attachments = attachmentRepository.findByChatRoom_Id(chatRoomId);
        return attachments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    // 上传附件
    public AttachmentDto uploadAttachment(MultipartFile file, Long chatRoomId) throws MinioException {
        try {
            // 使用 FileUploadService 上传到 MinIO，并返回文件的 URL
            String fileUrl = fileUploadService.uploadFile(file, BUCKET_NAME);

            // 保存附件信息到数据库
            AttachmentDto attachmentDto = new AttachmentDto();
            attachmentDto.setFileName(file.getOriginalFilename());
            attachmentDto.setFileType(file.getContentType());
            attachmentDto.setFileUrl(fileUrl);
            attachmentDto.setChatRoomId(chatRoomId);
            attachmentDto.setUploadedAt(LocalDateTime.now().toString());

            Attachment attachment = convertToEntity(attachmentDto);
            attachment.setUploadedAt(LocalDateTime.now());
            Attachment savedAttachment = attachmentRepository.save(attachment);

            return convertToDto(savedAttachment);
        } catch (Exception e) {
            throw new MinioException("Error uploading file to MinIO"+e.getMessage());
        }
    }

    // 删除附件
    public boolean deleteAttachment(Long id) {
        if (attachmentRepository.existsById(id)) {
            attachmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

// 将实体转换为 DTO
    private AttachmentDto convertToDto(Attachment attachment) {
        AttachmentDto attachmentDto = new AttachmentDto();
        BeanUtils.copyProperties(attachment, attachmentDto);
        // 手动设置 chatRoomId
        attachmentDto.setChatRoomId(attachment.getChatRoom().getId()); // 从 chatRoom 获取 ID 并设置到 DTO
        // 格式化 uploadedAt 时间（如果需要）
        attachmentDto.setUploadedAt(attachment.getUploadedAt().toString()); // 或者根据需要格式化为特定的字符串格式

        return attachmentDto;
    }
// 将 DTO 转换为实体
    private Attachment convertToEntity(AttachmentDto attachmentDto) {
        Attachment attachment = new Attachment();
        BeanUtils.copyProperties(attachmentDto, attachment);

        // 手动设置 chatRoomId
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setId(attachmentDto.getChatRoomId()); // 根据 DTO 中的 chatRoomId 设置 ChatRoom 对象
        attachment.setChatRoom(chatRoom); // 将 ChatRoom 设置到 Attachment 实体类中

        return attachment;
    }

}
