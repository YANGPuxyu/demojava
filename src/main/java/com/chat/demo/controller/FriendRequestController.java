package com.chat.demo.controller;

import com.chat.demo.entity.DTO.FriendRequestDto;
import com.chat.demo.entity.FriendRequest;
import com.chat.demo.response.Response;
import com.chat.demo.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend-requests")
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping
    public Response<FriendRequest> createFriendRequest(@RequestBody FriendRequestDto friendRequestDto) {
        FriendRequest friendRequest = friendRequestService.createFriendRequest(
                friendRequestDto.getSenderUserId(),
                friendRequestDto.getReceiverUserId(),
                friendRequestDto.getAnnouncement()
        );
        return Response.success(friendRequest);
    }
    @PostMapping("/{requestId}/approve")
    public Response<Void> approveFriendRequest(@PathVariable Long requestId) {
        friendRequestService.approveFriendRequest(requestId);
        return Response.success(null);
    }

    @PostMapping("/{requestId}/reject")
    public Response<Void> rejectFriendRequest(@PathVariable Long requestId) {
        friendRequestService.rejectFriendRequest(requestId);
        return Response.success(null);
    }

    @GetMapping("/sent/{senderUserId}")
    public Response<List<FriendRequestDto>> getSentRequests(@PathVariable Long senderUserId) {
        List<FriendRequestDto> requests = friendRequestService.getSentRequests(senderUserId);
        return Response.success(requests);
    }

    @GetMapping("/received/{receiverUserId}")
    public Response<List<FriendRequestDto>> getReceivedRequests(@PathVariable Long receiverUserId) {
        List<FriendRequestDto> requests = friendRequestService.getReceivedRequests(receiverUserId);
        return Response.success(requests);
    }
}