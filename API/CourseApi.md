
#### **1. 创建课程**
**URL**: `/courses`  
**方法**: `POST`  
**描述**: 创建一个新的课程。

**请求参数**:
- **Body**:
```json
{
  "name": "课程名称",
  "description": "课程描述",
  "chatRoom": {
    "name": "聊天室名称"
  }
}
```

**响应示例**:
- 成功:
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "id": 1,
    "name": "课程名称",
    "description": "课程描述",
    "chatRoom": {
      "id": 10,
      "name": "聊天室名称"
    }
  }
}
```
- 失败:
```json
{
  "code": 500,
  "message": "Failed to create course",
  "data": null
}
```

---

#### **2. 获取所有课程**
**URL**: `/courses`  
**方法**: `GET`  
**描述**: 获取所有课程的列表。

**请求参数**: 无

**响应示例**:
- 成功:
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    {
      "id": 1,
      "name": "课程名称1",
      "description": "课程描述1",
      "chatRoom": {
        "id": 10,
        "name": "聊天室名称1"
      }
    },
    {
      "id": 2,
      "name": "课程名称2",
      "description": "课程描述2",
      "chatRoom": {
        "id": 11,
        "name": "聊天室名称2"
      }
    }
  ]
}
```

---

#### **3. 根据 ID 获取课程**
**URL**: `/courses/{id}`  
**方法**: `GET`  
**描述**: 根据课程 ID 获取课程详情。

**请求参数**:
- **Path**:
    - `id` (Long): 课程的唯一标识符。

**响应示例**:
- 成功:
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "id": 1,
    "name": "课程名称",
    "description": "课程描述",
    "chatRoom": {
      "id": 10,
      "name": "聊天室名称"
    }
  }
}
```
- 失败:
```json
{
  "code": 500,
  "message": "Course not found",
  "data": null
}
```

---

#### **4. 删除课程**
**URL**: `/courses/{id}`  
**方法**: `DELETE`  
**描述**: 根据课程 ID 删除课程及其关联的聊天室。

**请求参数**:
- **Path**:
    - `id` (Long): 课程的唯一标识符。

**响应示例**:
- 成功:
```json
{
  "code": 200,
  "message": "Success",
  "data": null
}
```
- 失败:
```json
{
  "code": 500,
  "message": "Course not found or could not be deleted",
  "data": null
}
```

---

#### **5. 更新课程**
**URL**: `/courses/{id}`  
**方法**: `PUT`  
**描述**: 根据课程 ID 更新课程信息。

**请求参数**:
- **Path**:
    - `id` (Long): 课程的唯一标识符。
- **Body**:
```json
{
  "name": "新的课程名称",
  "description": "新的课程描述",
  "chatRoom": {
    "name": "新的聊天室名称"
  }
}
```

**响应示例**:
- 成功:
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "id": 1,
    "name": "新的课程名称",
    "description": "新的课程描述",
    "chatRoom": {
      "id": 10,
      "name": "新的聊天室名称"
    }
  }
}
```
- 失败:
```json
{
  "code": 500,
  "message": "Failed to update course",
  "data": null
}
```

---

### 状态码说明
- **200**: 操作成功。
- **500**: 操作失败（可能是资源未找到或内部错误）。

---

### 数据模型

#### **CourseDto**
```json
{
  "id": 1,
  "name": "课程名称",
  "description": "课程描述",
  "chatRoom": {
    "id": 10,
    "name": "聊天室名称"
  }
}
```

#### **ChatRoomDto**
```json
{
  "id": 10,
  "name": "聊天室名称"
}
```