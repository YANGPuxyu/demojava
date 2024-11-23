package com.chat.demo.repository;

import com.chat.demo.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    // 通过聊天室名称查找聊天室
    ChatRoom findByName(String name);
}
