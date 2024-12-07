package com.chat.demo.service;

import com.chat.demo.entity.DTO.FriendRequestDto;
import com.chat.demo.entity.FriendRequest;
import com.chat.demo.entity.Friendship;
import com.chat.demo.entity.User;
import com.chat.demo.repository.FriendRequestRepository;
import com.chat.demo.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendRequestService {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    public FriendRequest createFriendRequest(Long senderUserId, Long receiverUserId, String announcement) {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSenderUserId(senderUserId);
        friendRequest.setReceiverUserId(receiverUserId);
        friendRequest.setAnnouncement(announcement);
        friendRequest.setCreatedAt(LocalDateTime.now());
        friendRequest.setStatus(FriendRequest.Status.PENDING);
        return friendRequestRepository.save(friendRequest);
    }

    public void approveFriendRequest(Long requestId) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId).orElseThrow();
        friendRequest.setStatus(FriendRequest.Status.APPROVED);
        friendRequestRepository.save(friendRequest);

        User user = new User();
        user.setId(friendRequest.getSenderUserId());
        User friend = new User();
        friend.setId(friendRequest.getReceiverUserId());
        Friendship friendship = new Friendship();
        friendship.setUser1Id(user.getId());
        friendship.setUser2Id(friend.getId());
        friendship.setCreatedAt(LocalDateTime.now());
        friendshipRepository.save(friendship);
    }

    public void rejectFriendRequest(Long requestId) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId).orElseThrow();
        friendRequest.setStatus(FriendRequest.Status.REJECTED);
        friendRequestRepository.save(friendRequest);
    }

    public List<FriendRequestDto> getSentRequests(Long senderUserId) {
        List<FriendRequest> requests = friendRequestRepository.findBySenderUserId(senderUserId);
        return requests.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<FriendRequestDto> getReceivedRequests(Long receiverUserId) {
        List<FriendRequest> requests = friendRequestRepository.findByReceiverUserId(receiverUserId);
        return requests.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private FriendRequestDto toDTO(FriendRequest friendRequest) {
        FriendRequestDto dto = new FriendRequestDto();
        dto.setId(friendRequest.getId());
        dto.setSenderUserId(friendRequest.getSenderUserId());
        dto.setReceiverUserId(friendRequest.getReceiverUserId());
        dto.setAnnouncement(friendRequest.getAnnouncement());
        dto.setCreatedAt(friendRequest.getCreatedAt());
        dto.setStatus(friendRequest.getStatus().name());
        return dto;
    }
}