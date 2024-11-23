package com.chat.demo.repository;

import com.chat.demo.entity.ChatRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {
    // 根据聊天室 ID 查找所有成员
    List<ChatRoomMember> findByChatRoomId(Long chatRoomId);

    // 根据用户 ID 查找用户所在的所有聊天室
    List<ChatRoomMember> findByUserId(Long userId);

    // 检查用户是否在某个聊天室中
    boolean existsByChatRoomIdAndUserId(Long chatRoomId, Long userId);
}

