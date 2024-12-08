package com.chat.demo.controller;

import com.chat.demo.entity.DTO.FavoriteDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {


    private final FavoriteService favoriteService;
    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }
    /**
     * 获取所有收藏记录
     */
    @GetMapping("/all")
    public ResponseEntity<Response<List<FavoriteDto>>> getAllFavorites() {
        Response<List<FavoriteDto>> response = favoriteService.getAllFavorites();
        return ResponseEntity.ok(response);
    }

    /**
     * 根据收藏记录ID获取收藏记录
     */
    @GetMapping("/{favoriteId}")
    public ResponseEntity<Response<FavoriteDto>> getFavoriteById(@PathVariable Long favoriteId) {
        Response<FavoriteDto> response = favoriteService.getFavoriteById(favoriteId);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据作者ID获取收藏记录
     */
    @GetMapping("/byAuthor/{authorId}")
    public ResponseEntity<Response<List<FavoriteDto>>> getFavoritesByAuthor(@PathVariable Long authorId) {
        Response<List<FavoriteDto>> response = favoriteService.getFavoritesByAuthorId(authorId);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据帖子ID获取收藏记录
     */
    @GetMapping("/byPost/{postId}")
    public ResponseEntity<Response<List<FavoriteDto>>> getFavoritesByPost(@PathVariable Long postId) {
        Response<List<FavoriteDto>> response = favoriteService.getFavoritesByPostId(postId);
        return ResponseEntity.ok(response);
    }

    /**
     * 新增收藏记录
     */
    @PostMapping("/add")
    public ResponseEntity<Response<FavoriteDto>> addFavorite(@RequestBody FavoriteDto favoriteDto) {
        Response<FavoriteDto> response = favoriteService.addFavorite(favoriteDto);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据收藏记录ID删除收藏记录
     */
    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<Response<String>> deleteFavoriteById(@PathVariable Long favoriteId) {
        Response<String> response = favoriteService.deleteFavoriteById(favoriteId);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据帖子ID删除所有收藏记录
     */
    @DeleteMapping("/deleteByPost/{postId}")
    public ResponseEntity<Response<String>> deleteFavoritesByPostId(@PathVariable Long postId) {
        Response<String> response = favoriteService.deleteFavoritesByPostId(postId);
        return ResponseEntity.ok(response);
    }
}
