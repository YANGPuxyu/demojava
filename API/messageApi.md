
---

### API 文档

#### **1. 发送消息,但是不同步到所有的订阅客户端**
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

### **WebSocket API 文档**

#### **1. WebSocket 连接**

- **URL**: `/ws/chat`
- **方法**: `CONNECT`（WebSocket）
- **描述**:  
  客户端通过 WebSocket 协议与服务器建立连接。客户端可以在此连接下发送和接收消息。连接成功后，客户端可以订阅聊天室的消息。

#### **2. WebSocket 订阅消息**

- **URL**: `/topic/chat-room/{chatRoomId}`
- **方法**: `SUBSCRIBE`（WebSocket）
- **描述**:  
  客户端在 WebSocket 连接中订阅特定聊天室的消息。当聊天室有新消息发送时，订阅的客户端将会接收到该消息。

- **消息格式**：  
  接收到的消息与通过 HTTP `POST /messages` 发送的消息格式相同，结构如下：
  ```json
  {
    "id": 101,
    "chatRoomId": 1,
    "userId": 10,
    "content": "Hello, World!",
    "messageType": "text",
    "createdAt": "2024-11-24T12:00:00"
  }
  ```

#### **3. WebSocket 发送消息**

- **URL**: `/app/sendMessage`
- **方法**: `SEND`（WebSocket）
- **描述**:  
  客户端通过 WebSocket 向服务器发送消息，消息将被保存到数据库，并通过 WebSocket 推送到聊天室中的所有订阅者。

- **请求体**：
  发送的消息结构与 `POST /messages` API 的请求体相同。以下是 WebSocket 请求体的示例：
  ```json
  {
    "chatRoomId": 1,
    "userId": 10,
    "content": "Hello, World!",
    "messageType": "text"
  }
  ```

- **响应**：
  服务器会将消息推送到订阅了该聊天室的所有客户端。客户端接收到的消息与请求消息相同，附带 `createdAt`（消息发送时间）字段。
  ```json
  {
    "chatRoomId": 1,
    "userId": 10,
    "content": "Hello, World!",
    "messageType": "text",
    "createdAt": "2024-12-01T14:35:23"
  }
  ```

---

### **前端 WebSocket 使用示例**

以下是前端如何通过 WebSocket 与服务器交互的示例：

```javascript
// 建立 WebSocket 连接
let socket = new WebSocket('ws://127.0.0.1:8080/ws/chat');  // 连接 WebSocket 服务

// 连接成功后的回调
socket.onopen = function(event) {
    console.log("WebSocket连接成功：", event);

    // 订阅聊天室消息
    socket.onmessage = function(event) {
        let message = JSON.parse(event.data);
        console.log("收到消息：", message);
        // 处理新消息并显示在聊天界面
        displayMessage(message);
    };
};

// 发送消息到指定聊天室
function sendMessage(content) {
    let message = {
        chatRoomId: 1,
        userId: 10,
        content: content,
        messageType: 'text'
    };
    socket.send(JSON.stringify(message));
}

// 显示消息
function displayMessage(message) {
    // 这里可以更新聊天界面，显示接收到的消息
    console.log("Chat message: ", message);
}
```

---

### **总结**

- **WebSocket 连接**:  
  客户端连接到 `/ws/chat`，使用 `CONNECT` 方法建立 WebSocket 连接。

- **订阅消息**:  
  客户端通过 `SUBSCRIBE /topic/chat-room/{chatRoomId}` 订阅聊天室消息。

- **发送消息**:  
  客户端通过 `SEND /app/sendMessage` 向服务器发送消息。

- **消息格式**:  
  所有的消息格式与通过 HTTP 发送的消息一致，包括 `chatRoomId`、`userId`、`content` 和 `messageType`。

- **前端示例**:  
  使用原生 WebSocket API 直接与服务器通信，进行消息的发送和接收。

---

### **修改说明**
- 移除了与 `SockJS` 相关的配置和示例，直接使用原生 WebSocket。
- 更新了 WebSocket 连接的实现和前端示例代码，采用标准的 WebSocket API。

---
