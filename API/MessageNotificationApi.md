以下是基于当前实现的 API 文档和测试文件。

---

## **API 文档**

### **1. 创建通知**
- **URL**: `/message-notifications`
- **方法**: `POST`
- **请求体**:
  ```json
  {
      "userId": 1,
      "messageId": 10
  }
  ```
- **响应**:
  - **成功**:
    ```json
    {
        "code": 200,
        "message": "Success",
        "data": {
            "id": 101,
            "userId": 1,
            "messageId": 10,
            "isRead": false,
            "notifiedAt": "2024-11-24T10:00:00"
        }
    }
    ```
  - **失败**: 无特殊失败逻辑，默认响应为成功。

---

### **2. 获取用户通知列表**
- **URL**: `/message-notifications/user/{userId}`
- **方法**: `GET`
- **参数**:
  - **路径参数**:
    - `userId` (Long): 用户 ID。
- **响应**:
  - **成功**:
    ```json
    {
        "code": 200,
        "message": "Success",
        "data": [
            {
                "id": 101,
                "userId": 1,
                "messageId": 10,
                "isRead": false,
                "notifiedAt": "2024-11-24T10:00:00"
            },
            {
                "id": 102,
                "userId": 1,
                "messageId": 15,
                "isRead": true,
                "notifiedAt": "2024-11-24T09:00:00"
            }
        ]
    }
    ```

---

### **3. 标记通知为已读**
- **URL**: `/message-notifications/{id}/read`
- **方法**: `PUT`
- **参数**:
  - **路径参数**:
    - `id` (Long): 通知的 ID。
- **响应**:
  - **成功**:
    ```json
    {
        "code": 200,
        "message": "Success",
        "data": {
            "id": 101,
            "userId": 1,
            "messageId": 10,
            "isRead": true,
            "notifiedAt": "2024-11-24T10:00:00"
        }
    }
    ```
  - **失败**:
    ```json
    {
        "code": 500,
        "message": "通知不存在或操作失败",
        "data": null
    }
    ```

---
。