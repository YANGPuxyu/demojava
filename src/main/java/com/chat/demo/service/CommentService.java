package com.chat.demo.service;

import com.chat.demo.entity.Comment;
import com.chat.demo.entity.DTO.CommentDto;
import com.chat.demo.repository.CommentRepository;
import com.chat.demo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 获取所有评论
     */
    public Response<List<CommentDto>> getAllComments() {
        try {
            List<Comment> comments = commentRepository.findAll();
            List<CommentDto> commentDtos = comments.stream().map(this::convertToDto).collect(Collectors.toList());
            return Response.success(commentDtos);
        } catch (Exception e) {
            return Response.error("获取评论列表失败: " + e.getMessage());
        }
    }

    /**
     * 根据评论ID获取评论
     */
    public Response<CommentDto> getCommentById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isEmpty()) {
            return Response.error("未找到评论ID: " + commentId);
        }
        CommentDto commentDto = convertToDto(commentOptional.get());
        return Response.success(commentDto);
    }

    /**
     * 根据作者ID获取评论
     */
    public Response<List<CommentDto>> getCommentsByAuthorId(Long authorId) {
        List<Comment> comments = commentRepository.findByAuthorId(authorId);
        if (comments.isEmpty()) {
            return Response.error("未找到该作者的评论");
        }
        List<CommentDto> commentDtos = comments.stream().map(this::convertToDto).collect(Collectors.toList());
        return Response.success(commentDtos);
    }

    /**
     * 根据帖子ID获取评论
     */
    public Response<List<CommentDto>> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        if (comments.isEmpty()) {
            return Response.error("未找到该帖子的评论");
        }
        List<CommentDto> commentDtos = comments.stream().map(this::convertToDto).collect(Collectors.toList());
        return Response.success(commentDtos);
    }

    /**
     * 新增评论
     */
    @Transactional
    public Response<CommentDto> saveComment(CommentDto commentDto) {
        try {
            if (commentDto == null || commentDto.getContent() == null || commentDto.getContent().trim().isEmpty()) {
                return Response.error("评论内容不能为空");
            }

            Comment comment = convertToEntity(commentDto);
            Comment savedComment = commentRepository.save(comment);
            return Response.success(convertToDto(savedComment));
        } catch (Exception e) {
            return Response.error("评论保存失败: " + e.getMessage());
        }
    }

    /**
     * 根据评论ID删除评论
     */
    @Transactional
    public Response<Void> deleteCommentById(Long commentId) {
        try {
            Optional<Comment> commentOptional = commentRepository.findById(commentId);
            if (commentOptional.isEmpty()) {
                return Response.error("未找到评论ID: " + commentId);
            }
            commentRepository.deleteById(commentId);
            return Response.success(null);
        } catch (Exception e) {
            return Response.error("删除评论失败: " + e.getMessage());
        }
    }
    /**
     * 根据帖子ID删除评论，可用在帖子删除时删除该帖子下的所有评论
     */
    @Transactional
    public Response<Void> deleteCommentsByPostId(Long postId) {
        try {
            // 删除所有与 postId 关联的评论
            commentRepository.deleteByPostId(postId);
            return Response.success(null);
        } catch (Exception e) {
            return Response.error("根据帖子ID删除评论失败: " + e.getMessage());
        }
    }

    /**
     * 将 Comment 转换为 CommentDto
     */
    private CommentDto convertToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setPostId(comment.getPostId()); // 关联帖子ID
        commentDto.setAuthorId(comment.getAuthorId()); // 关联作者ID
        commentDto.setContent(comment.getContent());
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
    }

    /**
     * 将 CommentDto 转换为 Comment
     */
    private Comment convertToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setId(commentDto.getId());
        comment.setPostId(commentDto.getPostId());
        comment.setAuthorId(commentDto.getAuthorId());
        comment.setCreatedAt(commentDto.getCreatedAt());

        return comment;
    }
}
