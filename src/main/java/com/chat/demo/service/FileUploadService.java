package com.chat.demo.service;

import io.minio.*;
import io.minio.errors.MinioException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class FileUploadService {

    private final MinioClient minioClient;

    public FileUploadService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String uploadFile(MultipartFile file, String bucketName) throws MinioException {
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            InputStream fileStream = file.getInputStream();
            String objectName = file.getOriginalFilename();

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(fileStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());

            return "https://your-minio-url.com/" + bucketName + "/" + objectName;
        } catch (Exception e) {
            throw new MinioException("Error uploading file to MinIO");
        }
    }

    // 下载文件
    public InputStream downloadFile(String bucketName, String objectName) throws MinioException {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
        } catch (Exception e) {
            throw new MinioException("Error downloading file from MinIO");
        }
    }
}
