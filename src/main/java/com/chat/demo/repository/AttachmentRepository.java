package com.chat.demo.repository;

import com.chat.demo.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    // 获取某条消息的所有附件
    List<Attachment> findByMessageId(Long messageId);

    // 根据文件类型获取附件
    List<Attachment> findByFileType(String fileType);
}
