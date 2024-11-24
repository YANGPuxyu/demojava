以下是你的 **Attachment API** 的文档说明，基于 RESTful 风格和统一返回格式：

---

## **Attachment API 文档**

### **基本信息**
- **Base URL**: `/attachments`
- **Response 格式**: 
    - 成功：`{"code": 200, "message": "Success", "data": <实际数据>}`
    - 失败：`{"code": 500, "message": "错误描述", "data": null}`

---

### **1. 上传附件**

- **URL**: `/attachments`
- **请求方法**: `POST`
- **描述**: 上传一个新的附件。
- **请求头**:
  - `Content-Type: application/json`
- **请求体**:

```json
{
  "messageId": 100,
  "fileName": "example.png",
  "fileType": "image/png",
  "fileUrl": "http://example.com/uploads/example.png"
}
```

- **响应**:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "id": 1,
    "messageId": 100,
    "fileName": "example.png",
    "fileType": "image/png",
    "fileUrl": "http://example.com/uploads/example.png",
    "uploadedAt": "2024-11-24T15:30:00"
  }
}
```

**失败**:
```json
{
  "code": 500,
  "message": "Failed to upload attachment",
  "data": null
}
```

---

### **2. 根据消息 ID 获取附件列表**

- **URL**: `/attachments/message/{messageId}`
- **请求方法**: `GET`
- **描述**: 根据消息 ID 获取关联的所有附件。
- **路径参数**:
  - `messageId` (Long): 消息的唯一标识。
- **响应**:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    {
      "id": 1,
      "messageId": 100,
      "fileName": "example1.png",
      "fileType": "image/png",
      "fileUrl": "http://example.com/uploads/example1.png",
      "uploadedAt": "2024-11-24T15:30:00"
    },
    {
      "id": 2,
      "messageId": 100,
      "fileName": "example2.pdf",
      "fileType": "application/pdf",
      "fileUrl": "http://example.com/uploads/example2.pdf",
      "uploadedAt": "2024-11-24T16:00:00"
    }
  ]
}
```

**失败**:
```json
{
  "code": 500,
  "message": "No attachments found for the message",
  "data": null
}
```

---

### **3. 删除附件**

- **URL**: `/attachments/{id}`
- **请求方法**: `DELETE`
- **描述**: 删除指定 ID 的附件。
- **路径参数**:
  - `id` (Long): 附件的唯一标识。
- **响应**:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": null
}
```

**失败**:
```json
{
  "code": 500,
  "message": "Failed to delete attachment with id: 2",
  "data": null
}
```

---

### **错误状态码**

| 状态码 | 描述                  |
|--------|-----------------------|
| 200    | 请求成功              |
| 500    | 请求失败，包含错误信息 |

---

### **示例请求和响应**

#### **1. 上传附件示例**

**请求**:
```http
POST /attachments HTTP/1.1
Content-Type: application/json

{
  "messageId": 100,
  "fileName": "example.png",
  "fileType": "image/png",
  "fileUrl": "http://example.com/uploads/example.png"
}
```

**响应**:
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "id": 1,
    "messageId": 100,
    "fileName": "example.png",
    "fileType": "image/png",
    "fileUrl": "http://example.com/uploads/example.png",
    "uploadedAt": "2024-11-24T15:30:00"
  }
}
```

#### **2. 获取附件列表示例**

**请求**:
```http
GET /attachments/message/100 HTTP/1.1
```

**响应**:
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    {
      "id": 1,
      "messageId": 100,
      "fileName": "example1.png",
      "fileType": "image/png",
      "fileUrl": "http://example.com/uploads/example1.png",
      "uploadedAt": "2024-11-24T15:30:00"
    },
    {
      "id": 2,
      "messageId": 100,
      "fileName": "example2.pdf",
      "fileType": "application/pdf",
      "fileUrl": "http://example.com/uploads/example2.pdf",
      "uploadedAt": "2024-11-24T16:00:00"
    }
  ]
}
```

#### **3. 删除附件示例**

**请求**:
```http
DELETE /attachments/1 HTTP/1.1
```

**响应**:
```json
{
  "code": 200,
  "message": "Success",
  "data": null
}
``` 

--- 
