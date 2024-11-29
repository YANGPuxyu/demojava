package com.chat.demo.controller;

import com.chat.demo.entity.DTO.FriendshipDTO;
import com.chat.demo.entity.Friendship;
import com.chat.demo.entity.User;
import com.chat.demo.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @PostMapping("/friends")
    public ResponseEntity<Friendship> addFriend(@RequestBody FriendshipDTO friendshipDTO) {
        User user = new User();
        user.setId(friendshipDTO.getUserId());
        User friend = new User();
        friend.setId(friendshipDTO.getFriendId());
        Friendship newFriendship = friendshipService.addFriend(user, friend);
        return ResponseEntity.status(201).body(newFriendship);
    }

    @GetMapping("/users/{userId}/friends")
    public ResponseEntity<List<Friendship>> getFriends(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        List<Friendship> friends = friendshipService.getFriends(user);
        return ResponseEntity.ok(friends);
    }

    @DeleteMapping("/users/{userId}/friends/{friendId}")
    public ResponseEntity<Void> removeFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        User user = new User();
        user.setId(userId);
        User friend = new User();
        friend.setId(friendId);
        friendshipService.removeFriend(user, friend);
        return ResponseEntity.noContent().build();
    }
}