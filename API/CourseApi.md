
---

### **1. 创建课程**

- **请求路径**: `/courses`
- **请求方法**: `POST`
- **请求体** (JSON):

```json
{
    "name": "Java基础课程",
    "description": "学习Java基础知识",
    "price": 199.99,
    "credits": 3,
    "teacherName": "张三",
    "startDate": "2024-01-01T09:00:00",
    "endDate": "2024-06-01T09:00:00",
    "hasExam": true,
    "examDate": "2024-06-01T09:00:00",
    "location": "第一教室",
    "teachingSessions": 36
}
```

- **响应成功** (HTTP 200):

```json
{
    "code": 200,
    "message": "Success",
    "data": {
        "id": 1,
        "name": "Java基础课程",
        "description": "学习Java基础知识",
        "price": 199.99,
        "credits": 3,
        "teacherName": "张三",
        "startDate": "2024-01-01T09:00:00",
        "endDate": "2024-06-01T09:00:00",
        "hasExam": true,
        "examDate": "2024-06-01T09:00:00",
        "location": "第一教室",
        "teachingSessions": 36
    }
}
```

- **响应失败** (HTTP 500):

```json
{
    "code": 500,
    "message": "Failed to create course",
    "data": null
}
```

---

### **2. 获取所有课程**

- **请求路径**: `/courses`
- **请求方法**: `GET`
- **响应成功** (HTTP 200):

```json
{
    "code": 200,
    "message": "Success",
    "data": [
        {
            "id": 1,
            "name": "Java基础课程",
            "description": "学习Java基础知识",
            "price": 199.99,
            "credits": 3,
            "teacherName": "张三",
            "startDate": "2024-01-01T09:00:00",
            "endDate": "2024-06-01T09:00:00",
            "hasExam": true,
            "examDate": "2024-06-01T09:00:00",
            "location": "第一教室",
            "teachingSessions": 36
        }
    ]
}
```

---

### **3. 根据课程 ID 获取课程**

- **请求路径**: `/courses/{id}`
- **请求方法**: `GET`
- **请求参数**:
  - `id`: 课程的 ID
- **响应成功** (HTTP 200):

```json
{
    "code": 200,
    "message": "Success",
    "data": {
        "id": 1,
        "name": "Java基础课程",
        "description": "学习Java基础知识",
        "price": 199.99,
        "credits": 3,
        "teacherName": "张三",
        "startDate": "2024-01-01T09:00:00",
        "endDate": "2024-06-01T09:00:00",
        "hasExam": true,
        "examDate": "2024-06-01T09:00:00",
        "location": "第一教室",
        "teachingSessions": 36
    }
}
```

- **响应失败** (HTTP 404):

```json
{
    "code": 500,
    "message": "Course not found",
    "data": null
}
```

---

### **4. 删除课程**

- **请求路径**: `/courses/{id}`
- **请求方法**: `DELETE`
- **请求参数**:
  - `id`: 课程的 ID
- **响应成功** (HTTP 200):

```json
{
    "code": 200,
    "message": "Course deleted successfully",
    "data": null
}
```

- **响应失败** (HTTP 404):

```json
{
    "code": 500,
    "message": "Course not found or could not be deleted",
    "data": null
}
```

---

### **5. 更新课程**

- **请求路径**: `/courses/{id}`
- **请求方法**: `PUT`
- **请求参数**:
  - `id`: 课程的 ID
- **请求体** (JSON):

```json
{
    "name": "Java高级课程",
    "description": "学习Java进阶知识",
    "price": 299.99,
    "credits": 4,
    "teacherName": "李四",
    "startDate": "2024-02-01T09:00:00",
    "endDate": "2024-07-01T09:00:00",
    "hasExam": true,
    "examDate": "2024-07-01T09:00:00",
    "location": "第二教室",
    "teachingSessions": 40
}
```

- **响应成功** (HTTP 200):

```json
{
    "code": 200,
    "message": "Success",
    "data": {
        "id": 1,
        "name": "Java高级课程",
        "description": "学习Java进阶知识",
        "price": 299.99,
        "credits": 4,
        "teacherName": "李四",
        "startDate": "2024-02-01T09:00:00",
        "endDate": "2024-07-01T09:00:00",
        "hasExam": true,
        "examDate": "2024-07-01T09:00:00",
        "location": "第二教室",
        "teachingSessions": 40
    }
}
```

- **响应失败** (HTTP 404):

```json
{
    "code": 500,
    "message": "Failed to update course",
    "data": null
}
```

---

### **6. 获取某个聊天室的所有课程**

- **请求路径**: `/courses/chatroom/{chatRoomId}`
- **请求方法**: `GET`
- **请求参数**:
  - `chatRoomId`: 聊天室 ID
- **响应成功** (HTTP 200):

```json
{
    "code": 200,
    "message": "Success",
    "data": [
        {
            "id": 1,
            "name": "Java基础课程",
            "description": "学习Java基础知识",
            "price": 199.99,
            "credits": 3,
            "teacherName": "张三",
            "startDate": "2024-01-01T09:00:00",
            "endDate": "2024-06-01T09:00:00",
            "hasExam": true,
            "examDate": "2024-06-01T09:00:00",
            "location": "第一教室",
            "teachingSessions": 36
        }
    ]
}
```

- **响应失败** (HTTP 404):

```json
{
    "code": 500,
    "message": "No courses found for the given chat room",
    "data": null
}
```

---

### **7. 获取某个教师的所有课程**

- **请求路径**: `/courses/teacher/{teacherName}`
- **请求方法**: `GET`
- **请求参数**:
  - `teacherName`: 教师姓名
- **响应成功** (HTTP 200):

```json
{
    "code": 200,
    "message": "Success",
    "data": [
        {
            "id": 1,
            "name": "Java基础课程",
            "description": "学习Java基础知识",
            "price": 199.99,
            "credits": 3,
            "teacherName": "张三",
            "startDate": "2024-01-01T09:00:00",
            "endDate": "2024-06-01T09:00:00",
            "hasExam": true,
            "examDate": "2024-06-01T09:00:00",
            "location": "第一教室",
            "teachingSessions": 36
        }
    ]
}
```

- **响应失败** (HTTP 404):

```json
{
    "code": 500,
    "message": "No courses found for the given teacher",
    "data": null
}
```

---

### **总结**

如上所示，所有返回的报文已经使用了统一的 `Response` 封装，并且根据你提供的 `Response` 类格式，所有成功的响应都会返回 `200` 的 `code`，并且包含 `data` 字段