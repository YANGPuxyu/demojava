package com.chat.demo.repository;

import com.chat.demo.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByUser1IdOrUser2Id(Long user1Id, Long user2Id);
    void deleteByUser1IdAndUser2Id(Long user1Id, Long user2Id);
}