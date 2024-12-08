package com.chat.demo.service;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import io.minio.BucketExistsArgs;  // 引入BucketExistsArgs
import io.minio.MakeBucketArgs;  // 引入MakeBucketArgs
import io.minio.PutObjectArgs;  // 引入PutObjectArgs
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class FileUploadService {

    private final MinioClient minioClient;

    // 构造函数注入 MinioClient
    public FileUploadService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String uploadFile(MultipartFile file, String bucketName) throws MinioException {
        try {
            // 确保 MinIO 存储桶存在
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // 获取文件输入流
            InputStream fileStream = file.getInputStream();
            String objectName = file.getOriginalFilename();

            // 上传文件到 MinIO
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(fileStream, file.getSize(), -1)  // 设置文件大小
                    .contentType(file.getContentType())
                    .build());

            // 返回文件在 MinIO 中的 URL
            return "https://your-minio-url.com/" + bucketName + "/" + objectName;
        } catch (Exception e) {
            throw new MinioException("Error uploading file to MinIO");
        }
    }
}
