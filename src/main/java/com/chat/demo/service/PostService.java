package com.chat.demo.service;

import com.chat.demo.entity.DTO.PostDto;
import com.chat.demo.entity.Post;
import com.chat.demo.repository.PostRepository;
import com.chat.demo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * 获取所有帖子
     */
    public Response<List<PostDto>> getAllPosts() {
        try {
            List<Post> posts = postRepository.findAll();
            List<PostDto> postDtos = posts.stream().map(this::convertToDto).collect(Collectors.toList());
            return Response.success(postDtos);
        } catch (Exception e) {
            return Response.error("获取帖子列表失败: " + e.getMessage());
        }
    }



    /**
     * 根据帖子ID查询帖子
     */
    public Response<PostDto> getPostById(Long postId) {
        if (postId == null) {
            return Response.error("帖子ID不能为空");
        }

        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            return Response.error("未找到帖子ID: " + postId);
        }

        Post post = postOptional.get();
        return Response.success(convertToDto(post));
    }

    /**
     * 根据作者ID查询所有帖子
     */
    public Response<List<PostDto>> getPostsByAuthorId(Long authorId) {
        if (authorId == null) {
            return Response.error("作者ID不能为空");
        }

        List<Post> posts = postRepository.findByAuthorId(authorId); // 使用 authorId 查询
        if (posts.isEmpty()) {
            return Response.error("未找到与作者ID " + authorId + " 相关的帖子");
        }
        List<PostDto> postDtos = posts.stream().map(this::convertToDto).collect(Collectors.toList());
        return Response.success(postDtos);
    }

    /**
     * 保存帖子
     */
    @Transactional
    public Response<PostDto> savePost(PostDto postDto) {
        try {
            if (postDto == null) {
                return Response.error("帖子不能为空");
            }
            if (postDto.getAuthorId() == null ) {
                return Response.error("作者ID不能为空");
            }
            if (postDto.getTitle() == null || postDto.getTitle().trim().isEmpty()) {
                return Response.error("标题不能为空");
            }
            if (postDto.getContent() == null || postDto.getContent().trim().isEmpty()) {
                return Response.error("内容不能为空");
            }

            // 使用 authorId 代替完整的 User 对象
            Post post = convertToEntity(postDto);
            Post savedPost = postRepository.save(post);
            return Response.success(convertToDto(savedPost));
        } catch (Exception e) {
            return Response.error("发帖失败: " + e.getMessage());
        }
    }

    /**
     * 根据帖子ID删除帖子
     */
    @Transactional
    public Response<Void> deletePostById(Long postId) {
        try {
            Optional<Post> postOptional = postRepository.findById(postId);
            if (postOptional.isEmpty()) {
                return Response.error("未找到帖子ID: " + postId);
            }
            postRepository.deleteById(postId);
            return Response.success(null);
        } catch (Exception e) {
            return Response.error("删除失败: " + e.getMessage());
        }
    }


    /**
     * 将 Post 转换为 PostDto
     */
    private PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());

        if (post.getAuthorId() != null) {
            postDto.setAuthorId(post.getAuthorId()); // 直接设置作者ID
        }

        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setImageUrls(post.getImageUrls());
        return postDto;
    }

    /**
     * 将 PostDto 转换为 Post
     */

    private Post convertToEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        if (postDto.getAuthorId() != null) {
            // 使用 authorId 来设置作者
            post.setAuthorId(postDto.getAuthorId());
        }

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCreatedAt(postDto.getCreatedAt());
        post.setImageUrls(postDto.getImageUrls());
        return post;
    }

}
