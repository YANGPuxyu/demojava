package com.chat.demo.controller;

import com.chat.demo.entity.DTO.AttachmentDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping
    public Response<AttachmentDto> uploadAttachment(@RequestBody AttachmentDto attachment) {
        AttachmentDto result = attachmentService.uploadAttachment(attachment);
        if (result.getId() == null) {
            return Response.error("Failed to upload attachment");
        }
        return Response.success(result);
    }

    @GetMapping("/message/{messageId}")
    public Response<List<AttachmentDto>> getAttachmentsByMessage(@PathVariable Long messageId) {
        List<AttachmentDto> attachments = attachmentService.getAttachmentsByMessage(messageId);
        if (attachments.isEmpty()) {
            return Response.error("No attachments found for the message");
        }
        return Response.success(attachments);
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteAttachment(@PathVariable Long id) {
        boolean success = attachmentService.deleteAttachment(id);
        if (success) {
            return Response.success(null);
        }
        return Response.error("Failed to delete attachment with id: " + id);
    }
}
