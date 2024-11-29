package com.chat.demo.service;

import com.chat.demo.entity.Friendship;
import com.chat.demo.entity.User;
import com.chat.demo.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    public List<Friendship> getFriends(User user) {
        return friendshipRepository.findByUser(user);
    }

    public Friendship addFriend(User user, User friend) {
        Friendship friendship = new Friendship();
        friendship.setUser(user);
        friendship.setFriend(friend);
        return friendshipRepository.save(friendship);
    }

    public void removeFriend(User user, User friend) {
        Friendship friendship = friendshipRepository.findByUserAndFriend(user, friend);
        if (friendship != null) {
            friendshipRepository.delete(friendship);
        }
    }
}