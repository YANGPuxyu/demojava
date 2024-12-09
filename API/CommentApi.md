

# Comment API 文档

## 1. 创建评论
**URL**: `/comments`  
**方法**: `POST`  
**描述**: 创建一条新的评论。

### 请求参数:  注意在api测试时，body里面会自动有个id，最好把那一句删掉，
### 否则会会将数据库中的该id的内容给覆盖掉，而不是id自动升序生成
### 不知道对前端是否有影响
**Body**:
```json
{
  "authorId": 1002,
  "postId": 1001,
  "content": "这是一条新评论",
  "createdAt": "2024-12-08 11:29:24"
}
```

### 响应示例:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "id": 1,
    "authorId": 1002,
    "postId": 1001,
    "content": "这是一条新评论",
    "createdAt": "2024-12-08 11:29:24"
  }
}
```

**失败**:
```json
{
  "code": 500,
  "message": "Failed to create comment",
  "data": null
}
```

---

## 2. 获取所有评论
**URL**: `/comments`  
**方法**: `GET`  
**描述**: 获取所有评论。

### 请求参数: 无

### 响应示例:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    {
      "id": 1,
      "authorId": 1002,
      "postId": 1,
      "content": "这是第一条评论",
      "createdAt": "2024-12-08 10:00:00"
    },
    {
      "id": 2,
      "authorId": 1003,
      "postId": 1,
      "content": "这是第二条评论",
      "createdAt": "2024-12-08 10:05:00"
    }
  ]
}
```

---

## 3. 根据评论ID获取评论
**URL**: `/comments/{commentId}`  
**方法**: `GET`  
**描述**: 根据评论的 ID 获取具体的评论信息。

### 请求参数:
**Path**:
- `commentId` (Long): 评论的唯一标识符。

### 响应示例:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "id": 1,
    "authorId": 1002,
    "postId": 1,
    "content": "这是评论内容",
    "createdAt": "2024-12-08 10:00:00"
  }
}
```

**失败**:
```json
{
  "code": 500,
  "message": "Comment not found",
  "data": null
}
```

---

## 4. 根据作者ID获取评论
**URL**: `/comments/user/{userId}`  
**方法**: `GET`  
**描述**: 根据作者的 ID 获取该作者的所有评论。

### 请求参数:
**Path**:
- `userId` (Long): 作者的唯一标识符。

### 响应示例:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    {
      "id": 1,
      "authorId": 1002,
      "postId": 1001,
      "content": "这是评论内容",
      "createdAt": "2024-12-08 10:00:00"
    },
    {
      "id": 2,
      "authorId": 1002,
      "postId": 1002,
      "content": "这是另一个评论",
      "createdAt": "2024-12-08 10:05:00"
    }
  ]
}
```

**失败**:
```json
{
  "code": 500,
  "message": "No comments found for this author",
  "data": null
}
```

---

## 5. 根据帖子ID获取评论
**URL**: `/comments/post/{postId}`  
**方法**: `GET`  
**描述**: 根据帖子 ID 获取该帖子的所有评论。

### 请求参数:
**Path**:
- `postId` (Long): 帖子的唯一标识符。

### 响应示例:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    {
      "id": 1,
      "authorId": 1002,
      "postId": 1001,
      "content": "这是帖子1001的评论",
      "createdAt": "2024-12-08 10:00:00"
    },
    {
      "id": 2,
      "authorId": 1003,
      "postId": 1001,
      "content": "这是帖子1001的另一条评论",
      "createdAt": "2024-12-08 10:05:00"
    }
  ]
}
```

**失败**:
```json
{
  "code": 500,
  "message": "No comments found for this post",
  "data": null
}
```

---

## 6. 删除评论
**URL**: `/comments/{commentId}`  
**方法**: `DELETE`  
**描述**: 根据评论的 ID 删除该评论。

### 请求参数:
**Path**:
- `commentId` (Long): 评论的唯一标识符。

### 响应示例:

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
  "message": "Comment not found or could not be deleted",
  "data": null
}
```

---

## 7. 根据帖子ID删除评论
**URL**: `/comments/post/{postId}`  
**方法**: `DELETE`  
**描述**: 根据帖子 ID 删除该帖子的所有评论。

### 请求参数:
**Path**:
- `postId` (Long): 帖子的唯一标识符。

### 响应示例:

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
  "message": "Failed to delete comments for post",
  "data": null
}
```

---

## 状态码说明
- `200`: 操作成功。
- `500`: 操作失败（可能是资源未找到或内部错误）。

---

## 数据模型

**CommentDto**:
```json
{
  "id": 1,
  "authorId": 1002,
  "postId": 1,
  "content": "评论内容",
  "createdAt": "2024-12-08 10:00:00"
}
```

---

