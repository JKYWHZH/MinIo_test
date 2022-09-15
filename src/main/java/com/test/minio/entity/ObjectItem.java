package com.test.minio.entity;

import lombok.Data;

@Data
public class ObjectItem {

    private String objectName;

    private Long size;
}
