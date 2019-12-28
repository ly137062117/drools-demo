/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.controller;

import com.baidu.droolsdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @project: drools-demo
 * @description: drools-demo，测试控制器
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@Controller()
@RequestMapping("/droolsdemo/test")
public class TestController {
    /**
     * 测试服务
     */
    @Autowired
    private TestService testService;

    /**
     * 地址跳转，根据传递的短地址通过规则引擎匹配，输出真实地址，然后进行跳转
     *
     * @param url
     * @return
     */
    @RequestMapping("/redirect")
    public String redirect(String url) {
        return "redirect:" + testService.getUrl(url);
    }
}
