/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: drools-demo
 * @description: 样本实体
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@Data
@NoArgsConstructor
public class Person {
    /**
     * 样本id
     */
    private Integer id;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 小度分
     */
    private Integer xdScore;
}
