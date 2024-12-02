

## **接口说明**

### **POST /api/send**

- **描述**：该接口用于发送电子邮件。用户需要提供收件人邮箱、邮件主题和邮件正文。

- **请求示例**：

请求体示例：

```json
{
    "to": "recipient@example.com",
    "subject": "Test Email",
    "body": "This is a test email."
}
```

- **响应示例**：

成功响应：

```json
{
    "code": 200,
    "message": "Email sent successfully",
    "data": null
}
```

失败响应：

```json
{
    "code": 500,
    "message": "Email sending failed",
    "data": null
}
```

---

### **错误处理**

- **500**：邮件发送失败，或者请求格式错误。检查日志获取更详细的信息。
- **400**：请求格式不正确，或者缺少必要字段。确保请求体包含 `to`、`subject` 和 `body` 字段。

---


