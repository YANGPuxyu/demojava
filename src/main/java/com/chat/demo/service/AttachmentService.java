package com.chat.demo.service;
import com.chat.demo.entity.Attachment;
import com.chat.demo.entity.DTO.AttachmentDto;
import com.chat.demo.entity.DTO.LoginRequestDto;
import com.chat.demo.entity.DTO.LoginResponseDto;
import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.entity.User;
import com.chat.demo.repository.AttachmentRepository;
import com.chat.demo.repository.UserRepository;
import com.chat.demo.utility.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    // 获取消息的所有附件
    public List<AttachmentDto> getAttachmentsByMessage(Long messageId) {
        return attachmentRepository.findByMessageId(messageId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 上传附件
    public AttachmentDto uploadAttachment(AttachmentDto attachmentDto) {
        Attachment attachment = convertToEntity(attachmentDto);

        // 设置上传时间为当前时间
        attachment.setUploadedAt(LocalDateTime.now());

        Attachment savedAttachment = attachmentRepository.save(attachment);
        return convertToDto(savedAttachment);
    }

    // 删除附件
    public void deleteAttachment(Long id) {
        attachmentRepository.deleteById(id);
    }

    // 将实体转换为 DTO
    private AttachmentDto convertToDto(Attachment attachment) {
        AttachmentDto attachmentDto = new AttachmentDto();
        BeanUtils.copyProperties(attachment, attachmentDto);
        return attachmentDto;
    }

    // 将 DTO 转换为实体
    private Attachment convertToEntity(AttachmentDto attachmentDto) {
        Attachment attachment = new Attachment();
        BeanUtils.copyProperties(attachmentDto, attachment);
        return attachment;
    }
}
