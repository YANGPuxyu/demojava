
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

## **消息通知管理**

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
    "read": "boolean"
  }
  ```
- **HTTP 状态码**:
  - `201 Created`: 创建成功

---

### **2. 获取用户的所有通知**
- **URL**: `/message-notifications/user/{userId}`  
- **方法**: `GET`  
- **响应体**:
  ```json
  [
    {
      "id": "long",
      "userId": "long",
      "message": "string",
      "read": "boolean"
    }
  ]
  ```
- **HTTP 状态码**:
  - `200 OK`: 返回通知列表

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
    "read": true
  }
  ```
- **HTTP 状态码**:
  - `200 OK`: 更新成功

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

### **2. 获取聊天室消息**
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

---

## **聊天室管理**

### **1. 创建聊天室**
- **URL**: `/chat-rooms`  
- **方法**: `POST`  
- **请求体**:
  ```json
  {
    "name": "string"
  }
  ```
- **响应体**:
  ```json
  {
    "id": "long",
    "name": "string",
    "createdAt": "datetime"
  }
  ```
- **HTTP 状态码**:
  - `201 Created`: 创建成功

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
      "createdAt": "datetime"
    }
  ]
  ```
- **HTTP 状态码**:
  - `200 OK`: 返回聊天室列表

---

## **附件管理**

### **1. 上传附件**
- **URL**: `/attachments`  
- **方法**: `POST`  
- **请求体**:
  ```json
  {
    "messageId": "long",
    "fileUrl": "string"
  }
  ```
- **响应体**:
  ```json
  {
    "id": "long",
    "messageId": "long",
    "fileUrl": "string",
    "uploadedAt": "datetime"
  }
  ```
- **HTTP 状态码**:
  - `201 Created`: 上传成功

---
