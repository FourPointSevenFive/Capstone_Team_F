package com.hallyugo.hallyugo.proof.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hallyugo.hallyugo.common.exception.ExceptionCode;
import com.hallyugo.hallyugo.common.exception.InvalidFileException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    private static final String FILE_PATH_PREFIX = "proof_shot/";

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String fileName = generateUniqueFileName(multipartFile); // 고유한 파일 이름 생성
        InputStream inputStream = multipartFile.getInputStream(); // InputStream 추출
        long contentLength = multipartFile.getSize(); // 파일 크기
        String contentType = multipartFile.getContentType(); // 파일 Content-Type 가져오기

        return uploadFileToS3(inputStream, fileName, contentLength, contentType);
    }

    private String uploadFileToS3(InputStream fileStream, String fileName, long contentLength, String contentType) throws IOException {
        String filePath = FILE_PATH_PREFIX + fileName;

        // ObjectMetadata 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        metadata.setContentType(contentType != null ? contentType : "application/octet-stream"); // Content-Type 설정 (null 체크)

        try {
            amazonS3Client.putObject(new PutObjectRequest(bucket, filePath, fileStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)); // PublicRead 권한 설정
        } finally {
            fileStream.close(); // InputStream 닫기
        }

        return amazonS3Client.getUrl(bucket, filePath).toString();
    }

    private String generateUniqueFileName(MultipartFile file) throws RuntimeException {
        try {
            String originalFileName = file.getOriginalFilename();

            if (originalFileName == null || !originalFileName.contains(".")) {
                throw new InvalidFileException(ExceptionCode.INVALID_FILE_TYPE);
            }

            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            return UUID.randomUUID().toString() + extension;

        } catch (Exception e) {
            throw new InvalidFileException(ExceptionCode.FAILED_TO_GENERATE_UNIQUE_FILE_NAME);
        }
    }

    public void deleteFile(String fileName) {
        String filePath = FILE_PATH_PREFIX + fileName;
        amazonS3Client.deleteObject(bucket, filePath);
    }
}