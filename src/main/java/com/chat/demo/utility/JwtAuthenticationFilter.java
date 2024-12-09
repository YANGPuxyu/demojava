package com.chat.demo.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        // 如果请求头包含 Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);  // 去掉 "Bearer " 前缀
            Claims claims = jwtUtil.validateToken(token);  // 验证 token
            Long userId = Long.valueOf(claims.getSubject());  // 从 token 中提取用户Id
            String role = claims.get("role", String.class);  // 从 token 中提取角色

            // 创建认证对象，并设置到 SecurityContext 中
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, null, List.of(() -> role));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 继续执行其他过滤器
        filterChain.doFilter(request, response);
    }
}