package com.chat.demo.repository;

import com.chat.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 可以自定义查询方法，例如：
    User findByName(String name);
}