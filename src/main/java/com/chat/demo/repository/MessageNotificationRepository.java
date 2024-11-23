package com.chat.demo.repository;

import com.chat.demo.entity.MessageNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageNotificationRepository extends JpaRepository<MessageNotification, Long> {
    List<MessageNotification> findByUserId(Long userId); // 根据用户 ID 查询通知

    List<MessageNotification> findByUserIdAndIsRead(Long userId, Boolean isRead); // 根据用户 ID 和已读状态查询
}
