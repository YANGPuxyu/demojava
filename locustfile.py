from locust import HttpUser, task, between
import json

class MyUser(HttpUser):
    wait_time = between(1, 5)

    def on_start(self):
        """在每个虚拟用户开始时执行登录并获取 token"""
        response = self.client.post("/users/login", json={"email": "2229393511@qq.com", "password": "$2a$10$L2ItSfAshIqmnD6b88AQF.I8WfZN8LGdNFKgoCA6ssY124UjbcVw."})
        if response.status_code == 200:
            # 假设返回的 JSON 中包含 token
            response_data = response.json()  # 解析 JSON 响应
            self.auth_token = response_data.get("data", {}).get("token")
        else:
            print("Login failed")

    @task
    def index(self):
        if hasattr(self, "auth_token"):
            headers = {
                "Authorization": f"Bearer {self.auth_token}"
            }
            self.client.get("/messages/chat-room/2", headers=headers)
