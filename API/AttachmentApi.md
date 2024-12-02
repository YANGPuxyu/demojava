

```markdown
# 附件 API 文档

本文档提供了管理系统中附件的 API 详情。API 允许上传、检索和删除与聊天室关联的附件。

## 基础 URL
```
/attachments
```

---

## 接口

### 1. **上传附件**
- **URL**: `/attachments`
- **方法**: `POST`
- **描述**: 上传附件到指定聊天室，并将文件存储到 MinIO。
- **请求参数**:
  - `chatRoomId` (必需): 要将附件关联的聊天室 ID。
  - `file` (必需): 要上传的文件。

#### 请求示例：
```
```bash
curl -X POST "http://localhost:8080/attachments" \
     -F "chatRoomId=123" \
     -F "file=@path/to/your/file.jpg"
```

#### 请求体：
- Form-data:
  - `chatRoomId` (Long): 聊天室的 ID（必需）。
  - `file` (MultipartFile): 要上传的文件（必需）。

#### 响应：
- **状态码**: `200`
- **消息**: `"成功"`
- **数据**: 附件的详细信息，包括文件名、文件类型、文件 URL 和关联的聊天室 ID。

#### 响应示例：
```json
{
  "code": 200,
  "message": "成功",
  "data": {
    "id": 1,
    "fileName": "file.jpg",
    "fileType": "image/jpeg",
    "fileUrl": "https://minio-url.com/attachments/file.jpg",
    "uploadedAt": "2024-12-01T12:34:56",
    "chatRoomId": 123
  }
}
```

- **错误响应**:
  - **状态码**: `500`
  - **消息**: `"上传附件失败: {错误信息}"`

---

### 2. **根据聊天室 ID 获取附件**
- **URL**: `/attachments/{roomId}`
- **方法**: `GET`
- **描述**: 获取指定聊天室的所有附件。
- **路径参数**:
  - `roomId` (必需): 聊天室的 ID。

#### 请求示例：
```bash
curl -X GET "http://localhost:8080/attachments/123"
```

#### 响应：
- **状态码**: `200`
- **消息**: `"成功"`
- **数据**: 附件列表。

#### 响应示例：
```json
{
  "code": 200,
  "message": "成功",
  "data": [
    {
      "id": 1,
      "fileName": "file1.jpg",
      "fileType": "image/jpeg",
      "fileUrl": "https://minio-url.com/attachments/file1.jpg",
      "uploadedAt": "2024-12-01T12:34:56",
      "chatRoomId": 123
    },
    {
      "id": 2,
      "fileName": "file2.pdf",
      "fileType": "application/pdf",
      "fileUrl": "https://minio-url.com/attachments/file2.pdf",
      "uploadedAt": "2024-12-01T12:35:01",
      "chatRoomId": 123
    }
  ]
}
```

- **错误响应**:
  - **状态码**: `500`
  - **消息**: `"未找到此聊天室的附件"`

---

### 3. **删除附件**
- **URL**: `/attachments/{id}`
- **方法**: `DELETE`
- **描述**: 删除指定的附件。
- **路径参数**:
  - `id` (必需): 附件的 ID。

#### 请求示例：
```bash
curl -X DELETE "http://localhost:8080/attachments/1"
```

#### 响应：
- **状态码**: `200`
- **消息**: `"成功"`
- **数据**: 无

#### 响应示例：
```json
{
  "code": 200,
  "message": "成功",
  "data": null
}
```

- **错误响应**:
  - **状态码**: `500`
  - **消息**: `"删除附件失败: {错误信息}"`

---

## 附件 DTO 说明

`AttachmentDto` 是附件的 Data Transfer Object (DTO)，它用于封装附件的相关信息。以下是 `AttachmentDto` 的字段说明：

| 字段名      | 类型        | 描述             | 约束                      |
| ----------- | ----------- | ---------------- | ------------------------- |
| `id`        | Long        | 附件 ID          | 无                        |
| `fileName`  | String      | 文件名           | 必填，不能为空             |
| `fileType`  | String      | 文件类型         | 必填，不能为空             |
| `fileUrl`   | String      | 文件 URL         | 必填，不能为空             |
| `uploadedAt`| String      | 上传时间         | 格式化的时间字符串         |
| `chatRoomId`| Long        | 聊天室 ID        | 必填，不能为空             |

---

## 错误代码说明

- **500**: 服务器错误，通常由于上传失败、数据库操作失败等原因。
- **404**: 资源未找到，例如附件未找到或指定的聊天室不存在。

---

### 示例代码：
- **上传附件接口**:
```java
@PostMapping
public Response<AttachmentDto> uploadAttachment(@RequestParam("chatRoomId") Long chatRoomId,
                                                  @RequestParam("file") MultipartFile file) {
    try {
        // 上传附件到 MinIO，返回文件的 URL
        AttachmentDto attachment = attachmentService.uploadAttachment(file, chatRoomId);

        return Response.success(attachment);
    } catch (Exception e) {
        e.printStackTrace();
        return Response.error("上传附件失败: " + e.getMessage());
    }
}
```
- **根据聊天室 ID 获取附件接口**:
```java
@GetMapping("/{roomId}")
public Response<List<AttachmentDto>> getAttachmentsByChatRoomId(@PathVariable Long roomId) {
    List<AttachmentDto> attachments = attachmentService.getAttachmentsByChatRoomId(roomId);
    return attachments.isEmpty() ? 
           Response.error("未找到该聊天室的附件") :
           Response.success(attachments);
}
```
- **删除附件接口**:
```java
@DeleteMapping("/{id}")
public Response<Void> deleteAttachment(@PathVariable Long id) {
    boolean success = attachmentService.deleteAttachment(id);
    return success ? Response.success(null) : Response.error("删除附件失败");
}
```

---

## 常见问题

1. **上传附件失败怎么办？**
  - 请检查文件大小是否超过限制，MinIO 服务是否正常运行，或者聊天室 ID 是否有效。

2. **如何获取附件 URL？**
  - 上传附件后，API 会返回包含附件 URL 的响应，URL 指向 MinIO 中存储的文件。

---

**免责声明**: 本文档基于当前代码实现，未来可能会有变更，请随时查看更新版本。
```

这份 API 文档包含了上传附件、获取附件以及删除附件的接口描述，采用了 Markdown 格式，适用于开发和团队协作时作为接口说明文档。