package com.chat.demo.service;

import com.chat.demo.entity.DTO.FavoriteDto;
import com.chat.demo.entity.Favorite;
import com.chat.demo.repository.FavoriteRepository;
import com.chat.demo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    /**
     * 获取所有收藏记录
     */
    public Response<List<FavoriteDto>> getAllFavorites() {
        try {
            List<Favorite> favorites = favoriteRepository.findAll();
            List<FavoriteDto> favoriteDtos = favorites.stream().map(this::convertToDto).collect(Collectors.toList());
            return Response.success(favoriteDtos);
        } catch (Exception e) {
            return Response.error("获取收藏记录失败: " + e.getMessage());
        }
    }

    /**
     * 根据收藏记录ID获取收藏记录
     */
    public Response<FavoriteDto> getFavoriteById(Long favoriteId) {
        Optional<Favorite> favoriteOptional = favoriteRepository.findById(favoriteId);
        if (favoriteOptional.isEmpty()) {
            return Response.error("未找到收藏记录ID: " + favoriteId);
        }
        FavoriteDto favoriteDto = convertToDto(favoriteOptional.get());
        return Response.success(favoriteDto);
    }

    /**
     * 根据作者ID获取收藏记录
     */
    public Response<List<FavoriteDto>> getFavoritesByAuthorId(Long authorId) {
        List<Favorite> favorites = favoriteRepository.findByAuthorId(authorId);
        if (favorites.isEmpty()) {
            return Response.error("未找到该作者的收藏记录");
        }
        List<FavoriteDto> favoriteDtos = favorites.stream().map(this::convertToDto).collect(Collectors.toList());
        return Response.success(favoriteDtos);
    }

    /**
     * 根据帖子ID获取收藏记录
     */
    public Response<List<FavoriteDto>> getFavoritesByPostId(Long postId) {
        List<Favorite> favorites = favoriteRepository.findByPostId(postId);
        if (favorites.isEmpty()) {
            return Response.error("未找到该帖子的收藏记录");
        }
        List<FavoriteDto> favoriteDtos = favorites.stream().map(this::convertToDto).collect(Collectors.toList());
        return Response.success(favoriteDtos);
    }

    /**
     * 新增收藏记录
     */
    @Transactional
    public Response<FavoriteDto> addFavorite(FavoriteDto favoriteDto) {
        try {
            if (favoriteDto == null || favoriteDto.getPostId() == null || favoriteDto.getAuthorId() == null) {
                return Response.error("帖子ID和用户ID不能为空");
            }

            Favorite favorite = convertToEntity(favoriteDto);
            Favorite savedFavorite = favoriteRepository.save(favorite);
            return Response.success(convertToDto(savedFavorite));
        } catch (Exception e) {
            return Response.error("收藏记录保存失败: " + e.getMessage());
        }
    }

    /**
     * 根据收藏记录ID删除收藏记录
     */
    @Transactional
    public Response<String> deleteFavoriteById(Long favoriteId) {
        try {
            Optional<Favorite> favoriteOptional = favoriteRepository.findById(favoriteId);
            if (favoriteOptional.isEmpty()) {
                return Response.error("未找到收藏记录ID: " + favoriteId);
            }
            favoriteRepository.deleteById(favoriteId);
            return Response.success("收藏记录删除成功");
        } catch (Exception e) {
            return Response.error("删除收藏记录失败: " + e.getMessage());
        }
    }

    /**
     * 根据帖子ID删除所有收藏记录
     */
    @Transactional
    public Response<String> deleteFavoritesByPostId(Long postId) {
        try {
            favoriteRepository.deleteByPostId(postId);
            return Response.success("所有收藏记录删除成功");
        } catch (Exception e) {
            return Response.error("根据帖子ID删除收藏记录失败: " + e.getMessage());
        }
    }

    /**
     * 将 Favorite 转换为 FavoriteDto
     */
    private FavoriteDto convertToDto(Favorite favorite) {
        return new FavoriteDto(
                favorite.getId(),
                favorite.getPostId(),
                favorite.getAuthorId(),
                favorite.getCreatedAt()
        );
    }

    /**
     * 将 FavoriteDto 转换为 Favorite
     */
    private Favorite convertToEntity(FavoriteDto favoriteDto) {
        Favorite favorite = new Favorite();
        favorite.setId(favoriteDto.getId());
        favorite.setPostId(favoriteDto.getPostId());
        favorite.setAuthorId(favoriteDto.getAuthorId());
        favorite.setCreatedAt(favoriteDto.getCreatedAt());
        return favorite;
    }
}
