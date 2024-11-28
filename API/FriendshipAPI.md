## Friend-Related API Documentation

### Overview
This document provides an overview of the Friend-related APIs in the application. These APIs allow you to manage friendships, including adding, retrieving, and removing friends.

### API Endpoints

#### 1. Add Friend
- **URL:** `/api/friends`
- **Method:** `POST`
- **Description:** Adds a new friend for a user.
- **Request Body:**
  ```json
  {
    "userId": 1,
    "friendId": 2
  }
  ```
- **Response:**
    - **Status:** `201 Created`
    - **Body:**
      ```json
      {
        "id": 1,
        "userId": 1,
        "friendId": 2,
        "createdAt": "2023-10-01T12:00:00",
        "updatedAt": "2023-10-01T12:00:00"
      }
      ```

#### 2. Get Friends
- **URL:** `/api/users/{userId}/friends`
- **Method:** `GET`
- **Description:** Retrieves the list of friends for a user.
- **Response:**
    - **Status:** `200 OK`
    - **Body:**
      ```json
      [
        {
          "id": 1,
          "userId": 1,
          "friendId": 2,
          "createdAt": "2023-10-01T12:00:00",
          "updatedAt": "2023-10-01T12:00:00"
        }
      ]
      ```

#### 3. Remove Friend
- **URL:** `/api/users/{userId}/friends/{friendId}`
- **Method:** `DELETE`
- **Description:** Removes a friend for a user.
- **Response:**
    - **Status:** `204 No Content`

### Testing the API

To test the Friend-related APIs, you can use an API testing tool like Postman. Below are the steps to test each endpoint:

#### 1. Add Friend
1. Open Postman.
2. Create a new `POST` request.
3. Set the URL to `http://localhost:8080/api/friends`.
4. In the `Body` tab, select `raw` and `JSON` format.
5. Enter the following JSON:
    ```json
    {
      "userId": 1,
      "friendId": 2
    }
    ```
6. Click `Send`.
7. Verify that the response status is `201 Created` and the response body contains the friendship details.

#### 2. Get Friends
1. Open Postman.
2. Create a new `GET` request.
3. Set the URL to `http://localhost:8080/api/users/1/friends`.
4. Click `Send`.
5. Verify that the response status is `200 OK` and the response body contains the list of friends.

#### 3. Remove Friend
1. Open Postman.
2. Create a new `DELETE` request.
3. Set the URL to `http://localhost:8080/api/users/1/friends/2`.
4. Click `Send`.
5. Verify that the response status is `204 No Content`.

By following these steps, you can effectively test the Friend-related APIs using Postman.