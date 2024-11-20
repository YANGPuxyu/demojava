package com.chat.demo.repository;

import com.chat.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);//我发现这个地方我都没有实现,我只是写出了声明,怎么就有这个函数了
}