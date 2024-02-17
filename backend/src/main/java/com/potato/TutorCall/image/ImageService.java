package com.potato.TutorCall.image;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.potato.TutorCall.config.S3Config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService{
    private final S3Config s3config;
    private final String localLocation = "/data/image/";


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    public String imageUpload(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.indexOf("."));
        String uuidFilename = UUID.randomUUID() + ext;

        String localPath = localLocation + uuidFilename;

        File localFile = new File(localPath);
        file.transferTo(localFile);

        //s3 이미지 업로드
        s3config.amazonS3Client().putObject(new PutObjectRequest(bucket,uuidFilename,localFile).withCannedAcl(CannedAccessControlList.PublicRead));
        String s3URL = s3config.amazonS3Client().getUrl(bucket, uuidFilename).toString();

        //우리 서버 파일 삭제
        // localFile.delete();
        return s3URL;
    }
}
