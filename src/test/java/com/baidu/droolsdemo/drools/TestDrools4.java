/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.drools;

import com.baidu.droolsdemo.pojo.Person;
import com.baidu.droolsdemo.pojo.Staff;
import com.baidu.droolsdemo.util.KieBaseUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @project: drools-demo
 * @description: drools 高级用法（决策表、DSL）
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDrools4 {

    /**
     * 按决策表规则给指定样本集数据打分
     *
     * @throws FileNotFoundException
     */
    @Test
    public void testXls1() throws FileNotFoundException {
        String filePath = "/Users/luyao12/code/drools/drools-demo/src/main/resources/com/baidu/rulepackage5/ruleTable2.xlsx";
        String rule = KieBaseUtil.transXlsToDrl(filePath);

        KieBase kieBase = KieBaseUtil.ruleKieBase(rule);
        KieSession kieSession = kieBase.newKieSession();

        List<Person> list = KieBaseUtil.getPersonEntityFromFile("doc/temp.csv");

        for (Person person : list) {
            kieSession.insert(person);
        }

        int count = kieSession.fireAllRules();
        System.out.println("一共执行了" + count + "条规则");
    }

    /**
     * 根据决策表输出对应的 drl 文件
     *
     * @throws FileNotFoundException
     */
    @Test
    public void transXls() throws FileNotFoundException {
        String filePath = "/Users/luyao12/code/drools/drools-demo/src/main/resources/com/baidu/rulepackage5/ruleTable2.xlsx";
        System.out.println(KieBaseUtil.transXlsToDrl(filePath));
    }

    /**
     * 领域专用语言（DSL）用法
     */
    @Test
    public void testDsl() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession6");

        Staff staff = new Staff();
        staff.setName("张三");
        staff.setAge(24);
        staff.setGender("女");

        Staff staff1 = new Staff();
        staff1.setName("李四");
        staff1.setAge(38);
        staff1.setGender("女");

        Staff staff2 = new Staff();
        staff2.setName("王五");
        staff2.setAge(19);
        staff2.setGender("男");

        Staff staff3 = new Staff();
        staff3.setName("卢瑶");
        staff3.setAge(24);
        staff3.setGender("男");

        kieSession.insert(staff);
        kieSession.insert(staff1);
        kieSession.insert(staff2);
        kieSession.insert(staff3);

        int count = kieSession.fireAllRules();
        System.out.println("一共执行了" + count + "条规则");
    }
}
