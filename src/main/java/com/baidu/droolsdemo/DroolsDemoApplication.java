/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baidu.droolsdemo.mapper")
public class DroolsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroolsDemoApplication.class, args);
    }

}
