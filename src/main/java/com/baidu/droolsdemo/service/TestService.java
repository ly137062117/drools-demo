/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.service;

import com.baidu.droolsdemo.mapper.RuleMapper;
import com.baidu.droolsdemo.pojo.Rule;
import com.baidu.droolsdemo.pojo.UrlTrans;
import com.baidu.droolsdemo.util.KieBaseUtil;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project: drools-demo
 * @description: 测试服务类
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@Service
public class TestService {
    /**
     * Rule 表对应的 mapper，数据库访问接口
     */
    @Autowired
    private RuleMapper ruleMapper;

    /**
     * 根据输入获取长地址
     *
     * @param url
     * @return
     */
    public String getUrl(String url) {
        /**
         * 定义 URL 事实对象
         */
        UrlTrans urlTrans = new UrlTrans();
        urlTrans.setShortUrl(url);

        List<Rule> rules = ruleMapper.selectAll();

        /**
         * 动态解析 rule 规则内容，规则体中根据规则获取真实地址
         */
        KieBase kieBase = KieBaseUtil.ruleKieBase(rules.get(0).getContent());
        KieSession kieSession = kieBase.newKieSession();

        /**
         * 插入事实对象
         */
        kieSession.insert(urlTrans);

        /**
         * 触发规则
         */
        kieSession.fireAllRules();

        return urlTrans.getLongUrl();
    }
}
