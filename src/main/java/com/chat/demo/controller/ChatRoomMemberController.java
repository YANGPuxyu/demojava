package com.chat.demo.controller;
import com.chat.demo.entity.ChatRoomMember;
import com.chat.demo.entity.DTO.ChatRoomMemberDto;
import com.chat.demo.entity.DTO.LoginRequestDto;
import com.chat.demo.entity.DTO.LoginResponseDto;
import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.entity.User;
import com.chat.demo.service.ChatRoomMemberService;
import com.chat.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/chat-room-members")
public class ChatRoomMemberController {

    @Autowired
    private ChatRoomMemberService chatRoomMemberService;

    @PostMapping
    public ChatRoomMemberDto addMemberToChatRoom(@RequestBody ChatRoomMemberDto chatRoomMember) {
        return chatRoomMemberService.addMemberToChatRoom(chatRoomMember);
    }

    @GetMapping("/chat-room/{chatRoomId}")
    public List<ChatRoomMemberDto> getMembersByChatRoom(@PathVariable Long chatRoomId) {
        return chatRoomMemberService.getMembersByChatRoom(chatRoomId);
    }

    @DeleteMapping("/{id}")
    public void removeMemberFromChatRoom(@PathVariable Long id) {
        chatRoomMemberService.removeMemberFromChatRoom(id);
    }
}
