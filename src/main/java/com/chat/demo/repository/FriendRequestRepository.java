package com.chat.demo.repository;

import com.chat.demo.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findBySenderUserId(Long senderUserId);
    List<FriendRequest> findByReceiverUserId(Long receiverUserId);
}