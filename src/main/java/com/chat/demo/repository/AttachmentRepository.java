package com.chat.demo.repository;

import com.chat.demo.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    // 根据文件类型获取附件
    List<Attachment> findByFileType(String fileType);

    List<Attachment> findByChatRoom_Id(Long chatRoomId);
}
