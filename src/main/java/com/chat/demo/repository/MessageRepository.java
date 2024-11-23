package com.chat.demo.repository;

import com.chat.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // 查找某个聊天室的所有消息
    List<Message> findByChatRoomId(Long chatRoomId);

    // 按用户 ID 查找其发送的所有消息
    List<Message> findByUserId(Long userId);
}

