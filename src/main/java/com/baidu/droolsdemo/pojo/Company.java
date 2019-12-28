/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @project: drools-demo
 * @description: 公司实体类
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    /**
     * 公司名
     */
    private String name;

    /**
     * 员工列表
     */
    private List<Staff> staffList;

    /**
     * 员工对应的 级别/职位
     */
    private Map<String, String> level;
}
