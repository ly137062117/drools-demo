/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: drools-demo
 * @description: 员工实体
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工性别
     */
    private String gender;

    /**
     * 员工年龄
     */
    private Integer age;

    /**
     * 员工所在公司
     */
    private Company company;
}
