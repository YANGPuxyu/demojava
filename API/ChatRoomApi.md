
---

## **API 文档 - 聊天室管理**

### 基本信息
- **基础路径**: `/chat-rooms`
- **返回格式**: JSON

---

### 1. **创建聊天室**

- **URL**: `/chat-rooms`
- **方法**: `POST`
- **请求头**:
    - `Content-Type: application/json`
- **请求体**:

  ```json
  {
    "name": "技术分享会",
    "courseId": 101
  }
  ```

- **响应**:
    - **成功**:
      ```json
      {
        "code": 200,
        "message": "Success",
        "data": {
          "id": 1,
          "name": "技术分享会",
          "courseId": 101,
          "createdAt": "2024-11-27T12:00:00",
          "updatedAt": "2024-11-27T12:00:00"
        }
      }
      ```
    - **失败**:
      ```json
      {
        "code": 500,
        "message": "Failed to create chat room",
        "data": null
      }
      ```

---

### 2. **获取所有聊天室**

- **URL**: `/chat-rooms`
- **方法**: `GET`
- **响应**:
    - **成功**:
      ```json
      {
        "code": 200,
        "message": "Success",
        "data": [
          {
            "id": 1,
            "name": "技术分享会",
            "courseId": 101,
            "createdAt": "2024-11-27T12:00:00",
            "updatedAt": "2024-11-27T12:00:00"
          },
          {
            "id": 2,
            "name": "产品讨论组",
            "courseId": 102,
            "createdAt": "2024-11-27T12:30:00",
            "updatedAt": "2024-11-27T12:30:00"
          }
        ]
      }
      ```

---

### 3. **根据 ID 获取聊天室详情**

- **URL**: `/chat-rooms/{id}`
- **方法**: `GET`
- **路径参数**:
    - `id`: 聊天室的唯一标识，类型 `Long`。
- **响应**:
    - **成功**:
      ```json
      {
        "code": 200,
        "message": "Success",
        "data": {
          "id": 1,
          "name": "技术分享会",
          "courseId": 101,
          "createdAt": "2024-11-27T12:00:00",
          "updatedAt": "2024-11-27T12:00:00"
        }
      }
      ```
    - **失败**:
      ```json
      {
        "code": 500,
        "message": "Chat room not found",
        "data": null
      }
      ```

---

### 4. **删除聊天室**

- **URL**: `/chat-rooms/{id}`
- **方法**: `DELETE`
- **路径参数**:
    - `id`: 聊天室的唯一标识，类型 `Long`。
- **响应**:
    - **成功**:
      ```json
      {
        "code": 200,
        "message": "Success",
        "data": null
      }
      ```
    - **失败**:
      ```json
      {
        "code": 500,
        "message": "Chat room not found or could not be deleted",
        "data": null
      }
      ```

---

### 5. **更新聊天室**

- **URL**: `/chat-rooms/{id}`
- **方法**: `PUT`
- **请求头**:
    - `Content-Type: application/json`
- **路径参数**:
    - `id`: 聊天室的唯一标识，类型 `Long`。
- **请求体**:

  ```json
  {
    "name": "新技术分享会",
    "courseId": 105
  }
  ```

- **响应**:
    - **成功**:
      ```json
      {
        "code": 200,
        "message": "Success",
        "data": {
          "id": 1,
          "name": "新技术分享会",
          "courseId": 105,
          "createdAt": "2024-11-27T12:00:00",
          "updatedAt": "2024-11-27T13:00:00"
        }
      }
      ```
    - **失败**:
      ```json
      {
        "code": 500,
        "message": "Failed to update chat room",
        "data": null
      }
      ```

---

### 返回代码说明
- `200`: 请求成功，返回对应的数据。
- `500`: 请求失败，返回错误描述。

---

这份 API 文档简洁清晰，可以直接提供给前端开发人员或测试团队使用。