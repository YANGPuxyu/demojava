package com.chat.demo.controller;

import com.chat.demo.entity.DTO.AttachmentDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    // 上传附件
    @PostMapping
    public Response<AttachmentDto> uploadAttachment(@RequestParam("chatRoomId") Long chatRoomId,
                                                    @RequestParam("file") MultipartFile file) {
        try {
            // 上传附件到 MinIO，返回文件的 URL
          AttachmentDto attachment = attachmentService.uploadAttachment(file, chatRoomId);

            return Response.success(attachment);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("Failed to upload attachment: " + e.getMessage());
        }
    }

    @GetMapping("/{roomId}")
    public Response<List<AttachmentDto>> getAttachmentsByChatRoomId(@PathVariable Long roomId) {
        List<AttachmentDto> attachments = attachmentService.getAttachmentsByChatRoomId(roomId);
        return attachments.isEmpty() ?
                Response.error("No attachments found for the room id") :
                Response.success(attachments);
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteAttachment(@PathVariable Long id) {
        boolean success = attachmentService.deleteAttachment(id);
        return success ? Response.success(null) : Response.error("Failed to delete attachment with id: " + id);
    }
    // 下载附件
    // 下载附件（根据 URL 查询）
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadAttachment(@RequestParam("fileUrl") String attachmentUrl) {
        try {
            InputStream fileStream = attachmentService.downloadAttachment(attachmentUrl);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"attachment\"");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(fileStream));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
