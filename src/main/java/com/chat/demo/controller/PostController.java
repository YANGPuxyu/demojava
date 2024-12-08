package com.chat.demo.controller;

import com.chat.demo.entity.DTO.PostDto;
import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.entity.Post;
import com.chat.demo.response.Response;
import com.chat.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 根据帖子ID查询帖子
     * @param postId 帖子ID
     * @return 帖子详情
     */
    @GetMapping("/{postId}")
    public Response<PostDto> getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    /**
     * 创建帖子
     * @param postDto 包含帖子信息的 DTO
     * @return 成功或失败的响应
     */
    @PostMapping
    public Response<PostDto> createPost(@RequestBody PostDto postDto) {
        return postService.savePost(postDto);
    }

    /**
     * 删除帖子
     * @param postId 帖子ID
     * @return 成功或失败的响应
     */
    @DeleteMapping("/{postId}")
    public Response<Void> deletePost(@PathVariable Long postId) {
        return postService.deletePostById(postId);
    }

    /**
     * 根据用户ID获取所有帖子
     * @param userId 用户ID
     * @return 用户的所有帖子
     */
    @GetMapping("/user/{userId}")
    public Response<List<PostDto>> getPostsByUser(@PathVariable Long userId) {
        // 这里的 userId 就是从 URL 中提取出来的路径变量
        return postService.getPostsByAuthorId(userId);
    }
    /**
     * 获取所有帖子
     * @return 全部帖子
     */
    @GetMapping
    public Response<List<PostDto>> getAllPosts() {
        return postService.getAllPosts();
    }
}