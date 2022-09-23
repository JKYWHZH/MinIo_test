package com.test.minio;

import io.minio.BucketExistsArgs;
import io.minio.LoadBalanceMinioClient;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class BucketExistsTest {

/*    @Autowired
    private MinioClient minioClient;*/

    @Autowired
    private LoadBalanceMinioClient loadBalanceMinioClient;

    @Test
    public void test01() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, InvalidBucketNameException {
        boolean test = loadBalanceMinioClient.getMinioClient().bucketExists(BucketExistsArgs.builder().bucket("test").build());
        System.out.println(test);
    }
}
