package com.test.minio.controller;

import com.test.minio.utils.MinIoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
public class MinioController {

    @Autowired
    private MinIoUtil minIoUtil;

    @Value("${minio.endpoint}")
    private String address;

    @Value("${minio.bucketName}")
    private String bucketName;

    @PostMapping("/upload")
    public Object upload(MultipartFile file) {

        minIoUtil.existBucket("test");

        List<String> upload = minIoUtil.upload(new MultipartFile[]{file});

        return address+"/"+bucketName+"/"+upload.get(0);
    }
}
