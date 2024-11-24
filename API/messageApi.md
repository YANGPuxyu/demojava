以下是 `MessageController` 的 API 文档

---

### API 文档

#### **1. 发送消息**
- **URL**: `/messages`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
      "chatRoomId": 1,
      "userId": 10,
      "content": "Hello, World!",
      "messageType": "text"
  }
  ```
- **Response**:
  - **成功**:
    ```json
    {
        "code": 200,
        "message": "Success",
        "data": {
            "id": 101,
            "chatRoomId": 1,
            "userId": 10,
            "content": "Hello, World!",
            "messageType": "text",
            "createdAt": "2024-11-24T12:00:00"
        }
    }
    ```
  - **失败**（例如消息内容为空）:
    ```json
    {
        "code": 500,
        "message": "Content cannot be empty",
        "data": null
    }
    ```

#### **2. 获取聊天室的所有消息**
- **URL**: `/messages/chat-room/{chatRoomId}`
- **Method**: `GET`
- **Response**:
  ```json
  {
      "code": 200,
      "message": "Success",
      "data": [
          {
              "id": 101,
              "chatRoomId": 1,
              "userId": 10,
              "content": "Hello, World!",
              "messageType": "text",
              "createdAt": "2024-11-24T12:00:00"
          },
          {
              "id": 102,
              "chatRoomId": 1,
              "userId": 11,
              "content": "Hi there!",
              "messageType": "text",
              "createdAt": "2024-11-24T12:01:00"
          }
      ]
  }
  ```

#### **3. 删除消息**
- **URL**: `/messages/{id}`
- **Method**: `DELETE`
- **Response**:
  - **成功**:
    ```json
    {
        "code": 200,
        "message": "Success",
        "data": "消息删除成功"
    }
    ```
  - **失败**:
    ```json
    {
        "code": 500,
        "message": "消息删除失败，消息不存在",
        "data": null
    }
    ```

---


