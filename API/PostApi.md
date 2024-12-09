# Post API 文档

## 1. 创建帖子
**URL**: `/posts`  
**方法**: `POST`  
**描述**: 创建一个新的帖子。

### 请求参数:
**Body**:
```json
{
   "id": 1,
    "authorId": 1002,
    "title": "666",
    "content": "nb",
    "createdAt": "2024-12-08 11:29:24",
    "imageUrls": ["D:/post_image/R-C.jpg"
    ]
}
```

### 响应示例:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "id": 8,
    "authorId": 1002,
    "title": "666",
    "content": "nb",
    "createdAt": "2024-12-08 11:30:50",
    "imageUrls": [
      "D:/post_image/R-C.jpg"
    ]
  }
}
```

**失败**:
```json
{
  "code": 500,
  "message": "Failed to create post",
  "data": null
}
```

---

## 2. 获取所有帖子
**URL**: `/posts`  
**方法**: `GET`  
**描述**: 获取所有帖子。

### 请求参数: 无

### 响应示例:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    {
       "id": 8,
    "authorId": 1002,
    "title": "666",
    "content": "nb",
    "createdAt": "2024-12-08 11:30:50",
    "imageUrls": [
      "D:/post_image/R-C.jpg"
    ]
    },
    {
       "id": 9,
    "authorId": 1002,
    "title": "666",
    "content": "nb",
    "createdAt": "2024-12-08 11:30:50",
    "imageUrls": [
      "D:/post_image/R-C.jpg"
    ]
    }
  ]
}
```

---

## 3. 根据帖子ID获取帖子
**URL**: `/posts/{postId}`  
**方法**: `GET`  
**描述**: 根据帖子 ID 获取帖子详情。

### 请求参数:
**Path**:
- `postId` (Long): 帖子的唯一标识符。

### 响应示例:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "id": 2,
    "authorId": 1002,
    "title": "我的故事",
    "content": "分享一些有趣的故事和经历总是很愉快的。",
    "createdAt": "2024-12-02 19:30:00",
    "imageUrls": [
    ]
  }
}
```

**失败**:
```json
{
  
  "code": 500,
  "message": "未找到帖子ID: 1",
  "data": null

}
```

---

## 4. 删除帖子
**URL**: `/posts/{postId}`  
**方法**: `DELETE`  
**描述**: 根据帖子 ID 删除帖子。

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
  "message": "Post not found or could not be deleted",
  "data": null
}
```

---

## 5. 根据用户ID获取所有帖子
**URL**: `/posts/user/{userId}`  
**方法**: `GET`  
**描述**: 根据用户ID获取该用户的所有帖子。

### 请求参数:
**Path**:
- `userId` (Long): 用户的唯一标识符。

### 响应示例:

**成功**:
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    {
       "id": 8,
    "authorId": 1002,
    "title": "666",
    "content": "nb",
    "createdAt": "2024-12-08 11:30:50",
    "imageUrls": [
      "D:/post_image/R-C.jpg"
    ]
    },
    {
      "id": 8,
    "authorId": 1002,
    "title": "666",
    "content": "nb",
    "createdAt": "2024-12-08 11:30:50",
    "imageUrls": [
      "D:/post_image/R-C.jpg"
    ]
    }
  ]
}
```

**失败**:
```json
{
  "code": 500,
  "message": "No posts found for the given user",
  "data": null
}
```

---

## 6. 获取所有帖子
**URL**: `/posts`  
**方法**: `GET`  
**描述**: 获取所有帖子。

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
    "title": "666",
    "content": "nb",
    "createdAt": "2024-12-08 11:30:50",
    "imageUrls": [
      "D:/post_image/R-C.jpg"
    ]
    },
    {
       "id": 2,
    "authorId": 1002,
    "title": "666",
    "content": "nb",
    "createdAt": "2024-12-08 11:30:50",
    "imageUrls": [
      "D:/post_image/R-C.jpg"
    ]
    }
  ]
}
```

---

## 状态码说明
- `200`: 操作成功
- `500`: 操作失败（资源未找到或内部错误）

---

## 数据模型

**PostDto**
```json
{
   "id": 8,
    "authorId": 1002,
    "title": "666",
    "content": "nb",
    "createdAt": "2024-12-08 11:30:50",
    "imageUrls": [
      "D:/post_image/R-C.jpg"
    ]
}
```

