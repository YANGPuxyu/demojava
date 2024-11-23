以下是完整的 API 文档，包括用户管理、聊天室管理、消息管理、消息通知、聊天室成员、附件管理的所有接口，按照模块划分：

---

# **API 文档**

## **用户管理**

### **1. 用户登录**
- **URL**: `/users/login`
- **方法**: `POST`
- **请求体**:
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **响应体**:
  ```json
  {
    "token": "string",
    "userId": "long"
  }
  ```
- **HTTP 状态码**:
  - `200 OK`: 登录成功
  - `401 Unauthorized`: 用户名或密码错误

---

### **2. 获取所有用户**
- **URL**: `/users`
- **方法**: `GET`
- **响应体**:
  ```json
  [
    {
      "id": "long",
      "username": "string",
      "email": "string",
      "createdAt": "datetime"
    }
  ]
  ```
- **HTTP 状态码**:
  - `200 OK`: 成功返回用户列表

---

### **3. 根据 ID 获取用户**
- **URL**: `/users/{id}`
- **方法**: `GET`
- **响应体**:
  ```json
  {
    "id": "long",
    "username": "string",
    "email": "string",
    "createdAt": "datetime"
  }
  ```
- **HTTP 状态码**:
  - `200 OK`: 成功返回用户信息
  - `404 Not Found`: 用户不存在

---

### **4. 用户注册**
- **URL**: `/users/register`
- **方法**: `POST`
- **请求体**:
  ```json
  {
    "username": "string",
    "email": "string",
    "password": "string"
  }
  ```
- **响应体**:
  ```json
  {
    "id": "long",
    "username": "string",
    "email": "string",
    "createdAt": "datetime"
  }
  ```
- **HTTP 状态码**:
  - `201 Created`: 用户注册成功
  - `400 Bad Request`: 请求参数有误

---

### **5. 删除用户**
- **URL**: `/users/{id}`
- **方法**: `DELETE`
- **HTTP 状态码**:
  - `204 No Content`: 删除成功
  - `404 Not Found`: 用户不存在

---

## **聊天室管理**

### **1. 创建聊天室**
- **URL**: `/chat-rooms`
- **方法**: `POST`
- **请求体**:
  ```json
  {
    "name": "string",
    "description": "string"
  }
  ```
- **响应体**:
  ```json
  {
    "id": "long",
    "name": "string",
    "description": "string",
    "createdAt": "datetime"
  }
  ```
- **HTTP 状态码**:
  - `201 Created`: 创建成功
  - `400 Bad Request`: 参数有误

---

### **2. 获取所有聊天室**
- **URL**: `/chat-rooms`
- **方法**: `GET`
- **响应体**:
  ```json
  [
    {
      "id": "long",
      "name": "string",
      "description": "string",
      "createdAt": "datetime"
    }
  ]
  ```
- **HTTP 状态码**:
  - `200 OK`: 成功返回聊天室列表

---

### **3. 根据 ID 获取聊天室**
- **URL**: `/chat-rooms/{id}`
- **方法**: `GET`
- **响应体**:
  ```json
  {
    "id": "long",
    "name": "string",
    "description": "string",
    "createdAt": "datetime"
  }
  ```
- **HTTP 状态码**:
  - `200 OK`: 返回指定聊天室
  - `404 Not Found`: 聊天室不存在

---

### **4. 更新聊天室信息**
- **URL**: `/chat-rooms/{id}`
- **方法**: `PUT`
- **请求体**:
  ```json
  {
    "name": "string",
    "description": "string"
  }
  ```
- **响应体**:
  ```json
  {
    "id": "long",
    "name": "string",
    "description": "string",
    "updatedAt": "datetime"
  }
  ```
- **HTTP 状态码**:
  - `200 OK`: 更新成功
  - `404 Not Found`: 聊天室不存在

---

### **5. 删除聊天室**
- **URL**: `/chat-rooms/{id}`
- **方法**: `DELETE`
- **HTTP 状态码**:
  - `204 No Content`: 删除成功
  - `404 Not Found`: 聊天室不存在

---

## **消息管理**

### **1. 发送消息**
- **URL**: `/messages`
- **方法**: `POST`
- **请求体**:
  ```json
  {
    "chatRoomId": "long",
    "senderId": "long",
    "content": "string"
  }
  ```
- **响应体**:
  ```json
  {
    "id": "long",
    "chatRoomId": "long",
    "senderId": "long",
    "content": "string",
    "createdAt": "datetime"
  }
  ```
- **HTTP 状态码**:
  - `201 Created`: 消息发送成功

---

### **2. 获取聊天室的消息列表**
- **URL**: `/messages/chat-room/{chatRoomId}`
- **方法**: `GET`
- **响应体**:
  ```json
  [
    {
      "id": "long",
      "chatRoomId": "long",
      "senderId": "long",
      "content": "string",
      "createdAt": "datetime"
    }
  ]
  ```
- **HTTP 状态码**:
  - `200 OK`: 返回消息列表

---

### **3. 删除消息**
- **URL**: `/messages/{id}`
- **方法**: `DELETE`
- **HTTP 状态码**:
  - `204 No Content`: 删除成功
  - `404 Not Found`: 消息不存在

---

## **消息通知**

### **1. 创建消息通知**
- **URL**: `/message-notifications`
- **方法**: `POST`
- **请求体**:
  ```json
  {
    "userId": "long",
    "message": "string"
  }
  ```
- **响应体**:
  ```json
  {
    "id": "long",
    "userId": "long",
    "message": "string",
    "createdAt": "datetime"
  }
  ```

---

### **2. 获取用户的消息通知**
- **URL**: `/message-notifications/user/{userId}`
- **方法**: `GET`
- **响应体**:
  ```json
  [
    {
      "id": "long",
      "userId": "long",
      "message": "string",
      "createdAt": "datetime",
      "read": "boolean"
    }
  ]
  ```

---

### **3. 标记通知为已读**
- **URL**: `/message-notifications/{id}/read`
- **方法**: `PUT`
- **响应体**:
  ```json
  {
    "id": "long",
    "userId": "long",
    "message": "string",
    "read": "boolean",
    "updatedAt": "datetime"
  }
  ```

---
## **聊天室成员管理**

### **1. 添加成员到聊天室**
- **URL**: `/chat-room-members`
- **方法**: `POST`
- **请求体**:
  ```json
  {
    "chatRoomId": "long",
    "userId": "long",
    "role": "string" 
  }
  ```
- **响应体**:
  ```json
  {
    "id": "long",
    "chatRoomId": "long",
    "userId": "long",
    "role": "string",
    "addedAt": "datetime"
  }
  ```
- **HTTP 状态码**:
  - `201 Created`: 成员添加成功
  - `400 Bad Request`: 参数有误
  - `404 Not Found`: 聊天室或用户不存在

---

### **2. 获取聊天室的所有成员**
- **URL**: `/chat-room-members/chat-room/{chatRoomId}`
- **方法**: `GET`
- **响应体**:
  ```json
  [
    {
      "id": "long",
      "chatRoomId": "long",
      "userId": "long",
      "role": "string",
      "addedAt": "datetime"
    }
  ]
  ```
- **HTTP 状态码**:
  - `200 OK`: 返回成员列表
  - `404 Not Found`: 聊天室不存在

---

### **3. 从聊天室移除成员**
- **URL**: `/chat-room-members/{id}`
- **方法**: `DELETE`
- **HTTP 状态码**:
  - `204 No Content`: 成员移除成功
  - `404 Not Found`: 成员不存在

---

## **附件管理**

### **1. 上传附件**
- **URL**: `/attachments`
- **方法**: `POST`
- **请求体**:
  ```json
  {
    "messageId": "long",
    "fileName": "string",
    "fileType": "string",
    "fileUrl": "string"
  }
  ```
- **响应体**:
  ```json
  {
    "id": "long",
    "messageId": "long",
    "fileName": "string",
    "fileType": "string",
    "fileUrl": "string",
    "uploadedAt": "datetime"
  }
  ```
- **HTTP 状态码**:
  - `201 Created`: 附件上传成功
  - `400 Bad Request`: 参数有误
  - `404 Not Found`: 消息不存在

---

### **2. 获取某条消息的附件**
- **URL**: `/attachments/message/{messageId}`
- **方法**: `GET`
- **响应体**:
  ```json
  [
    {
      "id": "long",
      "messageId": "long",
      "fileName": "string",
      "fileType": "string",
      "fileUrl": "string",
      "uploadedAt": "datetime"
    }
  ]
  ```
- **HTTP 状态码**:
  - `200 OK`: 返回附件列表
  - `404 Not Found`: 消息不存在

---

### **3. 删除附件**
- **URL**: `/attachments/{id}`
- **方法**: `DELETE`
- **HTTP 状态码**:
  - `204 No Content`: 附件删除成功
  - `404 Not Found`: 附件不存在

---

### **补充说明**
1. 所有时间字段（如 `createdAt`、`updatedAt` 等）格式统一为 ISO 8601，例如 `2024-11-23T10:15:30Z`。
2. 返回错误响应时，应包括详细错误信息，示例：
   ```json
   {
     "timestamp": "2024-11-23T10:15:30Z",
     "status": 404,
     "error": "Not Found",
     "message": "The requested resource was not found",
     "path": "/users/123"
   }
   ```
---