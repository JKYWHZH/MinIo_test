package com.test.minio.config;

import io.minio.LoadBalanceMinioClient;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MinIoClientConfig {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;


    /**
     * 注入minio 客户端
     * @return
     */
   @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(endpoint.split(",")[0])
                .credentials(accessKey, secretKey)
                .build();
    }

    @Bean
    public LoadBalanceMinioClient loadBalanceMinioClient(){
        return LoadBalanceMinioClient
                .builder()
                .endpoints(endpoint.split(","))
                .secretKey(secretKey)
                .accessKey(accessKey)
                .build();
    }
}
