/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: drools-demo
 * @description: 短地址跳转实体类
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@Data
@NoArgsConstructor
public class UrlTrans {

    /**
     * 输入的短地址
     */
    private String shortUrl;

    /**
     * 规则匹配过后的真实地址
     */
    private String longUrl;
}
