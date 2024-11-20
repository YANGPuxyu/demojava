
## **用户管理 API**

### **Base URL**:  
### 后续的这些都是基于这个基础url,比如/user/login
`http://localhost:8080/users`

---

### **1. 获取所有用户**
- **URL**: `/`
- **Method**: `GET`
- **Description**: 获取所有用户的信息。
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**:
      ```json
      [
          {
              "id": 1,
              "username": "John",
              "email": "john@example.com",
              "password": "******",
              "role": "Student"
          },
          {
              "id": 2,
              "username": "Alice",
              "email": "alice@example.com",
              "password": "******",
              "role": "Teacher"
          }
      ]
      ```

---

### **2. 根据 ID 获取用户**
- **URL**: `/{id}`
- **Method**: `GET`
- **Description**: 获取指定用户 ID 的详细信息。
- **Path Parameters**:
  - `id` (Long): 用户 ID。
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**:
      ```json
      {
          "id": 1,
          "username": "John",
          "email": "john@example.com",
          "password": "******",
          "role": "Student"
      }
      ```
    - **Status Code**: `404 NOT FOUND`  
      用户不存在时返回。

---

### **3. 用户注册**
- **URL**: `/register`
- **Method**: `POST`
- **Description**: 新建用户信息。
- **Request Body**:
    - **Content-Type**: `application/json`
    - **Example**:
      ```json
      {
          "username": "NewUser",
          "email": "newuser@example.com",
          "password": "securePassword",
          "role": "Student"
      }
      ```
- **Response**:
    - **Status Code**: `201 CREATED`
    - **Body**:
      ```json
      {
          "id": 3,
          "username": "NewUser",
          "email": "newuser@example.com",
          "password": "******",
          "role": "Student"
      }
      ```

---

### **4. 删除用户**
- **URL**: `/{id}`
- **Method**: `DELETE`
- **Description**: 删除指定用户 ID。
- **Path Parameters**:
  - `id` (Long): 用户 ID。
- **Response**:
    - **Status Code**: `204 NO CONTENT`
    - **Description**: 用户删除成功。
    - **Status Code**: `404 NOT FOUND`  
      用户不存在时返回。

---

### **5. 用户登录**
- **URL**: `/login`
- **Method**: `POST`
- **Description**: 用户登录并获取令牌。
- **Request Body**:
    - **Content-Type**: `application/json`
    - **Example**:
      ```json
      {
          "email": "user@example.com",
          "password": "securePassword"
      }
      ```
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**:
      ```json
      {
          "status": "success",
          "token": "jwt-token",
          "name": "John",
          "role": "Student"
      }
      ```
    - **Status Code**: `401 UNAUTHORIZED`  
      登录失败时返回，可能是邮箱或密码错误。

---

### **注意事项**
1. **验证**:  
   - 登录时返回的 JWT 令牌应在后续请求的 `Authorization` Header 中附带，格式为：`Bearer <token>`。
2. **错误处理**:  
   - 未找到资源时返回 `404`。
   - 请求体缺少必需字段时返回 `400`。
   - 权限不足时返回 `403`。

--- 

希望这份文档清晰地说明了 API 的用法！如果需要补充或修改，请告诉我。