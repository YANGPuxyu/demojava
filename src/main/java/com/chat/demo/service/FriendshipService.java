package com.chat.demo.service;

import com.chat.demo.entity.DTO.FriendshipDto;
import com.chat.demo.entity.Friendship;
import com.chat.demo.entity.User;
import com.chat.demo.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    public Friendship addFriend(User user, User friend) {
        Friendship friendship = new Friendship();
        friendship.setUser1Id(user.getId());
        friendship.setUser2Id(friend.getId());
        friendship.setCreatedAt(LocalDateTime.now());
        return friendshipRepository.save(friendship);
    }

    public List<FriendshipDto> getFriends(User user) {
        List<Friendship> friendships = friendshipRepository.findByUser1IdOrUser2Id(user.getId(), user.getId());
        return friendships.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void removeFriend(Long userId, Long friendId) {
        Friendship friendship = friendshipRepository.findByUser1IdAndUser2Id(userId, friendId);
        if (friendship == null) {
            friendship = friendshipRepository.findByUser1IdAndUser2Id(friendId, userId);
        }
        if (friendship != null) {
            friendshipRepository.delete(friendship);
        }
    }

    private FriendshipDto toDTO(Friendship friendship) {
        FriendshipDto dto = new FriendshipDto();
        dto.setId(friendship.getId());
        dto.setUser1Id(friendship.getUser1Id());
        dto.setUser2Id(friendship.getUser2Id());
        dto.setCreatedAt(friendship.getCreatedAt());
        return dto;
    }
}