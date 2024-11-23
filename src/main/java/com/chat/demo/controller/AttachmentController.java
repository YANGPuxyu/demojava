package com.chat.demo.controller;

import com.chat.demo.entity.Attachment;
import com.chat.demo.entity.DTO.AttachmentDto;
import com.chat.demo.entity.DTO.LoginRequestDto;
import com.chat.demo.entity.DTO.LoginResponseDto;
import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.entity.User;
import com.chat.demo.service.AttachmentService;
import com.chat.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping
    public AttachmentDto uploadAttachment(@RequestBody AttachmentDto attachment) {
        return attachmentService.uploadAttachment(attachment);
    }

    @GetMapping("/message/{messageId}")
    public List<AttachmentDto> getAttachmentsByMessage(@PathVariable Long messageId) {
        return attachmentService.getAttachmentsByMessage(messageId);
    }

    @DeleteMapping("/{id}")
    public void deleteAttachment(@PathVariable Long id) {
        attachmentService.deleteAttachment(id);
    }
}
