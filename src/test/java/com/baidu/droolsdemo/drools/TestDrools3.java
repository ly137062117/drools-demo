/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.drools;

import com.baidu.droolsdemo.pojo.Staff;
import com.baidu.droolsdemo.pojo.UrlTrans;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @project: drools-demo
 * @description: drools 按规则名过滤测试类
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDrools3 {

    /**
     * 按【规则名/开头/结尾/正则表达式】匹配
     */
    @Test
    public void testFilter1() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession3");

        /**
         * 向 drools 的 Working memory 中插入事实
         */
        Staff staff1 = new Staff();
        staff1.setName("卢瑶");
        staff1.setGender("男");
        staff1.setAge(24);

        Staff staff2 = new Staff();
        staff2.setName("张三");
        staff2.setGender("男");
        staff2.setAge(16);

        Staff staff3 = new Staff();
        staff3.setName("李四");
        staff3.setGender("男");
        staff3.setAge(50);

        Staff staff4 = new Staff();
        staff4.setName("王五");
        staff4.setGender("女");
        staff4.setAge(38);

        Staff staff5 = new Staff();
        staff5.setName("赵六");
        staff5.setGender("女");
        staff5.setAge(42);

        kieSession.insert(staff1);
        kieSession.insert(staff2);
        kieSession.insert(staff3);
        kieSession.insert(staff4);
        kieSession.insert(staff5);

        int count = kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("staffNameStartsWith"));
        // int count = kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("LessThan18"));
        // int count = kieSession.fireAllRules(new RuleNameMatchesAgendaFilter(".*Gender.*"));

        System.out.println("一共执行了" + count + "条规则！");
    }

    /**
     * 测试短地址跳转
     */
    @Test
    public void testFilter2() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入事实
         */
        UrlTrans urlTrans = new UrlTrans();
        urlTrans.setShortUrl("baidu");

        kieSession.insert(urlTrans);

        int count = kieSession.fireAllRules();
        // int count = kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("LessThan18"));
        // int count = kieSession.fireAllRules(new RuleNameMatchesAgendaFilter(".*Gender.*"));

        System.out.println("一共执行了" + count + "条规则！");
        System.out.println(urlTrans);
    }
}
