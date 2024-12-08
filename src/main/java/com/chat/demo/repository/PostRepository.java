package com.chat.demo.repository;

import com.chat.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 根据作者ID查找所有帖子
    List<Post> findByAuthorId(Long authorId);
}