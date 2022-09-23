package com.test.minio.controller;

import com.test.minio.utils.MinIoUtil;
import io.minio.LoadBalanceMinioClient;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Slf4j
public class MinioController {

    @Autowired
    private MinIoUtil minIoUtil;

    @Autowired
    private LoadBalanceMinioClient loadBalanceMinioClient;

    @Value("${minio.endpoint}")
    private String address;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Autowired
    private MinioClient minioClient;

    @PostMapping("/upload")
    public Object upload(MultipartFile file) {

        minIoUtil.existBucket("test");

        List<String> upload = minIoUtil.upload(new MultipartFile[]{file});

        return address+"/"+bucketName+"/"+upload.get(0);
    }

    @GetMapping("/testRestTemplate/{val}")
    public String testRestTemplate(@PathVariable("val") String val) throws ServerException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Bucket> buckets = loadBalanceMinioClient.getMinioClient().listBuckets();
        String ans = "";
        for (Bucket bucket : buckets) {
            System.out.println(bucket.name());
            ans = ans.concat(bucket.name()).concat(",");
        }
        boolean test = minIoUtil.bucketExist("test");
      /*  List<Bucket> buckets = minioClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.name());
        }*/
        System.out.println(test);
        ans = ans.concat(String.valueOf(test));
        return ans;
    }
}
