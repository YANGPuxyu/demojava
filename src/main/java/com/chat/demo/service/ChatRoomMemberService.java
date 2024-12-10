package com.chat.demo.service;
import com.chat.demo.entity.ChatRoom;
import com.chat.demo.entity.ChatRoomMember;
import com.chat.demo.entity.DTO.ChatRoomDto;
import com.chat.demo.entity.DTO.ChatRoomMemberDto;
import com.chat.demo.entity.User;
import com.chat.demo.repository.ChatRoomMemberRepository;
import com.chat.demo.repository.ChatRoomRepository;
import com.chat.demo.repository.UserRepository;
import com.chat.demo.response.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class ChatRoomMemberService {

    @Autowired
    private ChatRoomMemberRepository chatRoomMemberRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserRepository userRepository;

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

    // 根据用户 ID 获取聊天室
    public List<Long> getChatRoomsByUserId(Long userId) {
        return chatRoomMemberRepository.findByUserId(userId)
                .stream()
                .map(ChatRoomMember::getChatRoomId)
                .collect(Collectors.toList());
    }

    // 根据用户 ID 获取聊天室 DTO
    public List<ChatRoomDto> getChatRoomDtosByUserId(Long userId) {
        List<Long> chatRoomIds = chatRoomMemberRepository.findChatRoomIdsByUserId(userId);
        return chatRoomIds.stream()
                .map(chatRoomService::getChatRoomById)
                .collect(Collectors.toList());
    }

    public List<ChatRoomDto> getPublicChatRoomDtosByUserId(Long userId) {
        List<Long> chatRoomIds = chatRoomMemberRepository.findChatRoomIdsByUserId(userId);
        List<ChatRoom> chatRooms = chatRoomRepository.findByIdInAndIsPrivate(chatRoomIds, false);
        return chatRooms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ChatRoomDto> getPrivateChatRoomDtosByUserId(Long userId) {
        List<Long> chatRoomIds = chatRoomMemberRepository.findChatRoomIdsByUserId(userId);
        List<ChatRoom> chatRooms = chatRoomRepository.findByIdInAndIsPrivate(chatRoomIds, true);
        for (ChatRoom chatRoom :  chatRooms) {
            String otherUserName = getOtherUserName(chatRoom.getId(), userId);
            chatRoom.setName(otherUserName);
        }
        return chatRooms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private String getOtherUserName(Long chatRoomId, Long userId) {
        List<Long> userIds = chatRoomMemberRepository.findUserIdsByChatRoomId(chatRoomId);
        userIds.remove(userId);
        if (userIds.isEmpty()) {
            return "Unknown";
        } else {
            Long otherUserId = userIds.get(0);
            Optional<User> otherUserOptional = userRepository.findById(otherUserId);
            if (otherUserOptional.isPresent()) {
                User otherUser = otherUserOptional.get();
                return "Private Chat with " + otherUser.getUsername();
            } else {
                return "Unknown";
            }
        }
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

    private ChatRoomDto convertToDto(ChatRoom chatRoom) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        BeanUtils.copyProperties(chatRoom, chatRoomDto);
        return chatRoomDto;
    }
}

