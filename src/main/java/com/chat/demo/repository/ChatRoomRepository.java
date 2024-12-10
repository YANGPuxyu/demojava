package com.chat.demo.repository;

import com.chat.demo.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    // 通过聊天室名称查找聊天室
    ChatRoom findByName(String name);

    // 通过聊天室 ID 查找聊天室
    @Query("SELECT c FROM ChatRoom c WHERE c.id IN :ids AND c.isPrivate = :isPrivate")
    List<ChatRoom> findByIdInAndIsPrivate(@Param("ids") List<Long> ids, @Param("isPrivate") boolean isPrivate);
}
