package com.chat.demo.repository;

import com.chat.demo.entity.Friendship;
import com.chat.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByUser(User user);
    List<Friendship> findByFriend(User friend);
    Friendship findByUserAndFriend(User user, User friend);
}