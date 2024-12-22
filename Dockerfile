# 使用官方 Java 运行时镜像
FROM openjdk:17-jdk-alpine

# 指定工作目录
WORKDIR /app

# 将 JAR 包复制到容器中
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# 暴露 Spring Boot 默认端口
EXPOSE 8080
EXPOSE 8443

# 容器启动时运行 JAR 文件
ENTRYPOINT ["java", "-jar", "app.jar"]
