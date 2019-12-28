/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.drools;

import com.baidu.droolsdemo.pojo.Staff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.TimedRuleExecutionOption;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @project: drools-demo
 * @description: drools 基本属性测试类
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDrools2 {

    /**
     * 测试属性1
     */
    @Test
    public void testAttr1() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession2");

        /**
         * 插入事实
         */
        Staff staff = new Staff();
        staff.setName("卢瑶");

        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("一共执行了" + count + "条规则");
    }

    /**
     * 测试属性2-3
     */
    @Test
    public void testAttr2() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession2");

        /**
         * 插入事实
         */
        Staff staff = new Staff();
        staff.setName("卢瑶");
        staff.setAge(24);

        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("一共执行了" + count + "条规则");
    }

    /**
     * 测试属性4-6
     */
    @Test
    public void testAttr4() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession2");

        /**
         * 插入事实
         */
        Staff staff = new Staff();
        staff.setName("卢瑶");
        staff.setAge(18);
        staff.setGender("男");

        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("一共执行了" + count + "条规则");
    }

    /**
     * 测试属性7-12
     */
    @Test
    public void testAttr7() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession2");

        /**
         * 激活 agenda-group
         */
        // kieSession.getAgenda().getAgendaGroup("agenda1").setFocus();
        int count = kieSession.fireAllRules();
        System.out.println("一共执行了" + count + "条规则");
    }

    /**
     * 测试属性13
     */
    @Test
    public void testAttr13() throws InterruptedException {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        KieSessionConfiguration ksconf = kieServices.newKieSessionConfiguration();
        KieBaseConfiguration kbconf = kieServices.newKieBaseConfiguration();
        ksconf.setOption(TimedRuleExecutionOption.YES);

        KieBase kieBase = kieContainer.newKieBase("kbase2", kbconf);
        KieSession kieSession = kieBase.newKieSession(ksconf, null);

        kieSession.fireAllRules();
        Thread.sleep(10000);
        kieSession.dispose();
    }
}
