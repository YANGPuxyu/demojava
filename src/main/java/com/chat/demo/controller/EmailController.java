package com.chat.demo.controller;

import com.chat.demo.response.Response;
import com.chat.demo.service.EmailService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public Response<String> sendEmail(@RequestBody String requestBody) {
        try {
            JSONObject json = new JSONObject(requestBody);
            String to = json.getString("to");
            String subject = json.getString("subject");
            String body = json.getString("body");

            // 默认发件人邮箱
            String from = "1852611363@qq.com";

            // 调用邮件发送服务
            boolean success = emailService.sendEmail(from, to, subject, body);

            if (success) {
                return Response.success("Email sent successfully");
            } else {
                return Response.error("Email sending failed");
            }

        } catch (Exception e) {
            return Response.error("Error processing the request");
        }
    }
}
