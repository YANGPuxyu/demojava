package com.chat.demo.repository;

import com.chat.demo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // 根据作者ID查找收藏记录
    List<Favorite> findByAuthorId(Long authorId);

    // 根据帖子ID查找收藏记录
    List<Favorite> findByPostId(Long postId);

    // 根据帖子ID删除收藏记录
    void deleteByPostId(Long postId);
}
