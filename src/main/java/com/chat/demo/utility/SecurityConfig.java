package com.chat.demo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF，因为 WebSocket 连接不需要 CSRF
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 跨域配置
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/login", "/users/register","/messages/**","/ws/**","/actuator/**").permitAll() // 登录、注册接口公开
                        .requestMatchers("/ws/chat").permitAll() // 允许 WebSocket 连接
                        .anyRequest().authenticated() // 其余接口需要认证
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // 添加 JWT 过滤器
                .httpBasic(httpBasic -> {});  // 允许基础 HTTP 身份验证（如果需要）

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许所有来源
        configuration.addAllowedOriginPattern("*");  // 允许所有域名
        configuration.addAllowedMethod("*"); // 允许所有 HTTP 方法
        configuration.addAllowedHeader("*"); // 允许所有请求头
        configuration.setAllowCredentials(true); // 允许携带 Cookie
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
