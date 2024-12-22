package com.chat.demo.controller;

import com.chat.demo.entity.DTO.FriendshipDto;
import com.chat.demo.entity.Friendship;
import com.chat.demo.entity.User;
import com.chat.demo.response.Response;
import com.chat.demo.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

//    @PostMapping("/friends")
//    public Response<Friendship> addFriend(@RequestBody FriendshipDto friendshipDTO) {
//        User user = new User();
//        user.setId(friendshipDTO.getUser1Id());
//        User friend = new User();
//        friend.setId(friendshipDTO.getUser2Id());
//        Friendship newFriendship = friendshipService.addFriend(user, friend);
//        return Response.success(newFriendship);
//    }

    @GetMapping("/users/{userId}")
    public Response<List<FriendshipDto>> getFriends(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        List<FriendshipDto> friends = friendshipService.getFriends(user);
        return Response.success(friends);
    }

    @DeleteMapping("/user/{userId}/friend/{friendId}")
    public Response<Void> removeFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        friendshipService.removeFriend(userId, friendId);
        return Response.success(null);
    }
}