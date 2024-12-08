package com.chat.demo.controller;

import com.chat.demo.entity.DTO.CommentDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 获取所有评论
     * @return 全部评论列表
     */
    @GetMapping
    public Response<List<CommentDto>> getAllComments() {
        return commentService.getAllComments();
    }

    /**
     * 根据评论ID获取评论
     * @param commentId 评论ID
     * @return 具体评论
     */
    @GetMapping("/{commentId}")
    public Response<CommentDto> getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    /**
     * 根据作者ID获取评论
     * @param userId 作者ID
     * @return 该作者的所有评论
     */
    @GetMapping("/user/{userId}")
    public Response<List<CommentDto>> getCommentsByAuthorId(@PathVariable Long userId) {
        return commentService.getCommentsByAuthorId(userId);
    }

    /**
     * 根据帖子ID获取评论
     * @param postId 帖子ID
     * @return 该帖子的所有评论
     */
    @GetMapping("/post/{postId}")
    public Response<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    /**
     * 新增评论
     * @param commentDto 评论数据
     * @return 新增的评论
     */
    @PostMapping
    public Response<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        return commentService.saveComment(commentDto);
    }

    /**
     * 根据评论ID删除评论
     * @param commentId 评论ID
     * @return 删除操作结果
     */
    @DeleteMapping("/{commentId}")
    public Response<Void> deleteComment(@PathVariable Long commentId) {
        return commentService.deleteCommentById(commentId);
    }

    /**
     * 根据帖子ID删除评论，即在删除该帖子时删除该帖子的所有评论
     * @param postId 帖子ID
     * @return 删除操作结果
     */
    @DeleteMapping("/post/{postId}")
    public Response<Void> deleteCommentsByPostId(@PathVariable Long postId) {
        return commentService.deleteCommentsByPostId(postId);
    }
}
