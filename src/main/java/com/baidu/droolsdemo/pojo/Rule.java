/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: drools-demo
 * @description: 规则实体，与数据库中的字段保持一致
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@Data
@NoArgsConstructor
public class Rule {

    /**
     * 唯一 id 主键
     */
    private int id;

    /**
     * 规则体内容
     */
    private String content;

    /**
     * 规则 key，用于唯一标识
     */
    private String ruleKey;

    /**
     * 删除标识
     */
    private Byte del;
}
