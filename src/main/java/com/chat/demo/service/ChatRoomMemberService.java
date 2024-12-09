package com.chat.demo.service;
import com.chat.demo.entity.ChatRoomMember;
import com.chat.demo.entity.DTO.ChatRoomMemberDto;
import com.chat.demo.repository.ChatRoomMemberRepository;
import com.chat.demo.response.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class ChatRoomMemberService {

    @Autowired
    private ChatRoomMemberRepository chatRoomMemberRepository;

    // 获取聊天室的所有成员
    public List<ChatRoomMemberDto> getMembersByChatRoom(Long chatRoomId) {
        return chatRoomMemberRepository.findByChatRoomId(chatRoomId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 添加成员到聊天室
    public ChatRoomMemberDto addMemberToChatRoom(ChatRoomMemberDto memberDto) {
        ChatRoomMember member = convertToEntity(memberDto);

        // 设置加入时间为当前时间
        member.setJoinedAt(LocalDateTime.now());

        ChatRoomMember savedMember = chatRoomMemberRepository.save(member);
        return convertToDto(savedMember);
    }

    // 从聊天室移除成员
    public void removeMemberFromChatRoom(Long id) {
        chatRoomMemberRepository.deleteById(id);
    }

    // 获取用户所加入的所有聊天室
    public List<ChatRoomMemberDto> getChatRoomsByUserId(Long userId) {
        return chatRoomMemberRepository.findChatRoomsByUserId(userId);
    }

    // 将实体转换为 DTO
    private ChatRoomMemberDto convertToDto(ChatRoomMember member) {
        ChatRoomMemberDto memberDto = new ChatRoomMemberDto();
        BeanUtils.copyProperties(member, memberDto);
        return memberDto;
    }

    // 将 DTO 转换为实体
    private ChatRoomMember convertToEntity(ChatRoomMemberDto memberDto) {
        ChatRoomMember member = new ChatRoomMember();
        BeanUtils.copyProperties(memberDto, member);
        return member;
    }
}

