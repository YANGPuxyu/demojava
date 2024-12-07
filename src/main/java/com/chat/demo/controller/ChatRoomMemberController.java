package com.chat.demo.controller;

import com.chat.demo.entity.DTO.ChatRoomMemberDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.ChatRoomMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat-room-members")
public class ChatRoomMemberController {

    @Autowired
    private ChatRoomMemberService chatRoomMemberService;

    @PostMapping
    public Response<ChatRoomMemberDto> addMemberToChatRoom(@RequestBody ChatRoomMemberDto chatRoomMember) {
        ChatRoomMemberDto savedMember = chatRoomMemberService.addMemberToChatRoom(chatRoomMember);
        return Response.success(savedMember);
    }

    @GetMapping("/chat-room/{chatRoomId}")
    public Response<List<ChatRoomMemberDto>> getMembersByChatRoom(@PathVariable Long chatRoomId) {
        List<ChatRoomMemberDto> members = chatRoomMemberService.getMembersByChatRoom(chatRoomId);
        return Response.success(members);
    }

    @GetMapping("/my-chat-rooms")
    public Response<List<Long>> getMyChatRooms(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        List<Long> chatRoomIds = chatRoomMemberService.getChatRoomsByUser(userId);
        return Response.success(chatRoomIds);
    }

    @DeleteMapping("/{id}")
    public Response<Void> removeMemberFromChatRoom(@PathVariable Long id) {
        chatRoomMemberService.removeMemberFromChatRoom(id);
        return Response.success(null);
    }
}
