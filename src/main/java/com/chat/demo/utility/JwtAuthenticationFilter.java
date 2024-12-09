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

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//
//        // 如果请求头包含 Bearer token
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);  // 去掉 "Bearer " 前缀
//            Claims claims = jwtUtil.validateToken(token);  // 验证 token
//            Long userId = Long.valueOf(claims.getSubject());  // 从 token 中提取用户Id
//            String role = claims.get("role", String.class);  // 从 token 中提取角色
//
//            // 创建认证对象，并设置到 SecurityContext 中
//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(userId, null, List.of(() -> role));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        // 继续执行其他过滤器
//        filterChain.doFilter(request, response);
//    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            Claims claims;
//            try {
//                claims = jwtUtil.validateToken(token);
//            } catch (JwtException e) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
//                return;
//            }
//
//            Long userId = Long.valueOf(claims.getSubject());
//            String role = claims.get("role", String.class);
//
//            // 创建认证对象，并设置到 SecurityContext 中
//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(userId, null, List.of(() -> role));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String requestURI = request.getRequestURI();
//
//            // 检查用户是否有权限访问自己的信息
//            if (requestURI.contains("/users/") && !requestURI.contains("/users/login") && !requestURI.contains("/users/register")) {
//                String[] uriParts = requestURI.split("/");
//                Long requestedUserId = Long.valueOf(uriParts[uriParts.length - 1]);
//                if (!userId.equals(requestedUserId)) {
//                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
//                    return;
//                }
//            }
//
//            // 检查用户角色是否匹配
//            if (requestURI.contains("/admin/") && !"ADMIN".equals(role)) {
//                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
//                return;
//            }
//
////            // 其他 API 的鉴权逻辑
////            if (requestURI.contains("/friend-requests/")) {
////                // 例如，只有管理员可以查看所有好友请求
////                if (requestURI.contains("/admin/") && !"ADMIN".equals(role)) {
////                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
////                    return;
////                }
////            }
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing or invalid");
//            return;
//        }
//
//        // 继续执行其他过滤器
//        filterChain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims;
            try {
                claims = jwtUtil.validateToken(token);
            } catch (JwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "不合法的 JWT token");
                return;
            }

            Long userId = Long.valueOf(claims.getSubject());
            String role = claims.get("role", String.class);

            // 创建认证对象，并设置到 SecurityContext 中
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, null, List.of(() -> role));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String requestURI = request.getRequestURI();

            // 如果用户是管理员，允许访问所有 API
            if (!"ADMIN".equals(role)) {
                // 检查用户是否有权限访问自己的信息
                if (requestURI.contains("/self/")) {
                    String[] uriParts = requestURI.split("/");
                    Long requestedUserId = Long.valueOf(uriParts[uriParts.length - 1]);
                    if (!userId.equals(requestedUserId)) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "非管理员用户只能访问关于自己的信息");
                        return;
                    }
                }

                // 检查用户角色是否匹配
                if (requestURI.contains("/admin/")) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "你需要管理员权限才能访问此资源");
                    return;
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization 请求头丢失或非法");
            return;
        }

        // 继续执行其他过滤器
        filterChain.doFilter(request, response);
    }
}
