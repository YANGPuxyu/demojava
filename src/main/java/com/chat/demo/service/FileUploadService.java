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
            System.out.println("[DEBUG] Attempting to download file from bucket: " + bucketName + ", object: " + objectName);

            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());

            System.out.println("[DEBUG] Successfully downloaded file: " + objectName + " from bucket: " + bucketName);
            return inputStream;
        } catch (Exception e) {
            System.err.println("[ERROR] Error downloading file from MinIO. Bucket: " + bucketName + ", Object: " + objectName);
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace();
            throw new MinioException("Error downloading file from MinIO: " + e.getMessage());
        }
    }

}
