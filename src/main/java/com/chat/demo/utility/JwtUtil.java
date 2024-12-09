package com.chat.demo.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    // 获取密钥，确保至少32字符长度
    private Key getSigningKey() {
        if (secretKey.length() < 32) {
            throw new IllegalArgumentException("JWT 密钥长度不足，需至少 32 字符");
        }
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // 生成 JWT token
    public String generateToken(Long userId, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))  // Use user ID as the subject
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 360000000)) // 设置过期时间为1小时
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 验证 token 是否有效，包括过期验证
    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)  // 会自动检测 token 是否过期
                    .getBody();
        } catch (ExpiredJwtException e) {
            // 捕获过期异常，返回特定的错误信息
            throw new JwtException("Token 已过期", e);
        } catch (JwtException e) {
            // 捕获其他 JWT 错误（如无效签名等）
            throw new JwtException("无效的 Token", e);
        }
    }
}