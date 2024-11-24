以下是 `UserController` 的 API 文档
---

## API 文档

### 1. 登录接口

**URL**: `/users/login`  
**方法**: `POST`  
**请求体**:  
```json
{
  "email": "user@example.com",
  "password": "123456"
}
```  
**响应**:
- 成功:  
  ```json
  {
    "code": 200,
    "message": "Success",
    "data": {
      "id": 1,
      "username": "User",
      "email": "user@example.com",
      "role": "学生"
    }
  }
  ```
- 失败:  
  ```json
  {
    "code": 500,
    "message": "邮箱或密码错误",
    "data": null
  }
  ```

---

### 2. 获取所有用户

**URL**: `/users`  
**方法**: `GET`  
**响应**:  
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    {
      "id": 1,
      "username": "User1",
      "email": "user1@example.com",
      "role": "学生"
    },
    {
      "id": 2,
      "username": "User2",
      "email": "user2@example.com",
      "role": "教师"
    }
  ]
}
```

---

### 3. 根据 ID 获取用户

**URL**: `/users/{id}`  
**方法**: `GET`  
**响应**:
- 成功:  
  ```json
  {
    "code": 200,
    "message": "Success",
    "data": {
      "id": 1,
      "username": "User",
      "email": "user@example.com",
      "role": "学生"
    }
  }
  ```
- 失败:  
  ```json
  {
    "code": 500,
    "message": "用户不存在",
    "data": null
  }
  ```

---

### 4. 注册新用户

**URL**: `/users/register`  
**方法**: `POST`  
**请求体**:  
```json
{
  "username": "NewUser",
  "email": "newuser@example.com",
  "password": "123456",
  "role": "学生"
}
```  
**响应**:  
- 成功:  
  ```json
  {
    "code": 200,
    "message": "Success",
    "data": {
      "id": 3,
      "username": "NewUser",
      "email": "newuser@example.com",
      "role": "学生"
    }
  }
  ```
- 失败（邮箱已注册）:  
  ```json
  {
    "code": 500,
    "message": "邮箱已注册",
    "data": null
  }
  ```

---

### 5. 删除用户

**URL**: `/users/{id}`  
**方法**: `DELETE`  
**响应**:  
- 成功:  
  ```json
  {
    "code": 200,
    "message": "Success",
    "data": "用户删除成功"
  }
  ```
- 失败:  
  ```json
  {
    "code": 500,
    "message": "用户不存在",
    "data": null
  }
  ```

