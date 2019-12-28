/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.drools;

import com.baidu.droolsdemo.pojo.Company;
import com.baidu.droolsdemo.pojo.Staff;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @project: drools-demo
 * @description: drools 基本语法测试类
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDrools {
    /**
     * 测试 query 查询体功能
     */
    @Test
    public void testQuery() {
        /**
         * Kie 服务接口，调用 drools 提供的所有功能接口
         */
        KieServices kieService = KieServices.Factory.get();
        /**
         * 规则容器，包含classpath 下的各规则文件组成的规则库的集合
         */
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        /**
         * 会话，与 kmodule.xml 中配置的 ksession 的 name 一致，创建对应的规则库的会话
         */
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff = new Staff();
        staff.setName("张三");
        staff.setAge(18);
        staff.setGender("男");

        Staff staff1 = new Staff();
        staff1.setName("李四");
        staff1.setAge(18);
        staff1.setGender("女");

        kieSession.insert(staff);
        kieSession.insert(staff1);

        /**
         * query 参数定义
         */
        Object[] objects = new Object[]{"张三"};
        QueryResults queryResults = kieSession.getQueryResults("queryByName", objects);
        for (QueryResultsRow row : queryResults) {
            Staff res = (Staff) row.get("staff");
            System.out.println("查询返回的结果：" + res);
        }

        /**
         * 有状态的 session 需要手动清除
         */
        kieSession.dispose();
    }

    /**
     * 测试规则体中通过 declare 自定义事实对象的功能
     */
    @Test
    public void testDeclare() {
        /**
         * Kie 服务接口，调用 drools 提供的所有功能接口
         */
        KieServices kieService = KieServices.Factory.get();
        /**
         * 规则容器，包含classpath 下的各规则文件组成的规则库的集合
         */
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        /**
         * 会话，与 kmodule.xml 中配置的 ksession 的 name 一致，创建对应的规则库的会话
         */
        KieSession kieSession = kieContainer.newKieSession("kession1");

        Staff staff = new Staff();
        staff.setName("卢瑶");
        kieSession.insert(staff);
        /**
         * 返回执行的规则的条数
         */
        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则1
     */
    @Test
    public void testRules1() {
        /**
         * Kie 服务接口，调用 drools 提供的所有功能接口
         */
        KieServices kieService = KieServices.Factory.get();
        /**
         * 规则容器，包含classpath 下的各规则文件组成的规则库的集合
         */
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        /**
         * 会话，与 kmodule.xml 中配置的 ksession 的 name 一致，创建对应的规则库的会话
         */
        KieSession kieSession = kieContainer.newKieSession("kession1");
        /**
         * 返回执行的规则的条数
         */
        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则2
     */
    @Test
    public void testRules2() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Company company = new Company();
        Staff staff = new Staff("卢瑶", "男", 18, company);
        kieSession.insert(company);
        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则3
     */
    @Test
    public void testRules3() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Company company = new Company();
        company.setName("百度");

        Staff staff = new Staff();
        staff.setName("卢瑶");
        staff.setGender("男");
        staff.setAge(18);

        company.setStaffList(Lists.newArrayList(staff));

        kieSession.insert(company);
        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则4
     */
    @Test
    public void testRules4() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */

        Staff staff1 = new Staff();
        staff1.setName("卢瑶1");
        staff1.setGender("男");
        staff1.setAge(18);

        Staff staff2 = new Staff();
        staff2.setName("卢瑶2");
        staff2.setGender("男");
        staff2.setAge(18);

        kieSession.insert(staff1);
        kieSession.insert(staff2);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则5
     */
    @Test
    public void testRules5() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */

        Staff staff1 = new Staff();
        staff1.setName("卢瑶");
        staff1.setGender("男");
        staff1.setAge(36);

        kieSession.insert(staff1);
        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则6
     */
    @Test
    public void testRules6() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */

        Company company = new Company();
        company.setName("百度");

        Company company1 = new Company();
        company1.setName("google");

        kieSession.insert(company);
        kieSession.insert(company1);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则7
     */
    @Test
    public void testRules7() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff = new Staff();
        staff.setName("卢瑶");
        staff.setAge(20);

        Company company = new Company();
        company.setName("百度");
        company.setStaffList(Lists.newArrayList(staff));

        kieSession.insert(company);
        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则8
     */
    @Test
    public void testRules8() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff = new Staff();
        staff.setName("卢瑶");
        staff.setAge(20);

        Company company = new Company();
        company.setName("百度");

        kieSession.insert(company);
        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则9
     */
    @Test
    public void testRules9() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff = new Staff();
        staff.setName("卢瑶");
        staff.setAge(20);

        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则10
     */
    @Test
    public void testRules10() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff = new Staff();
        staff.setName("kiss");
        staff.setAge(20);

        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则11
     */
    @Test
    public void testRules11() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff = new Staff();
        staff.setName("卢瑶001");
        staff.setAge(20);

        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则12
     */
    @Test
    public void testRules12() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff = new Staff();
        staff.setName("卢瑶");
        staff.setAge(20);

        Company company = new Company();
        company.setName("百度");
        company.setStaffList(Lists.newArrayList(staff));

        kieSession.insert(company);
        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则14
     */
    @Test
    public void testRules14() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff = new Staff();
        staff.setName("卢瑶");
        staff.setAge(20);

        Company company = new Company();
        company.setName("百度");
        company.setStaffList(Lists.newArrayList(staff));
        Map<String, String> map = new HashMap<>();
        map.put(staff.getName(), "程序员");
        company.setLevel(map);

        kieSession.insert(company);
        kieSession.insert(staff);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则15
     */
    @Test
    public void testRules15() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff1 = new Staff();
        staff1.setName("卢瑶");
        staff1.setGender("男");

        Staff staff2 = new Staff();
        staff2.setName("张三");
        staff2.setGender("男");

        Staff staff3 = new Staff();
        staff3.setName("李四");
        staff3.setGender("男");

        Staff staff4 = new Staff();
        staff4.setName("王五");
        staff4.setGender("男");

        Company company = new Company();
        company.setName("百度");
        company.setStaffList(Lists.newArrayList(staff1, staff2, staff3, staff4));

        kieSession.insert(company);
        kieSession.insert(staff1);
        kieSession.insert(staff2);
        kieSession.insert(staff3);
        kieSession.insert(staff4);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则16
     */
    @Test
    public void testRules16() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff1 = new Staff();
        staff1.setName("卢瑶");
        staff1.setGender("男");

        Staff staff2 = new Staff();
        staff2.setName("张三");
        staff2.setGender("男");

        Staff staff3 = new Staff();
        staff3.setName("李四");
        staff3.setGender("男");

        Staff staff4 = new Staff();
        staff4.setName("王五");
        staff4.setGender("男");

        Staff staff5 = new Staff();
        staff5.setName("赵六");
        staff5.setGender("女");

        kieSession.insert(staff1);
        kieSession.insert(staff2);
        kieSession.insert(staff3);
        kieSession.insert(staff4);
        kieSession.insert(staff5);

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }

    /**
     * 测试规则17-18
     */
    @Test
    public void testRules17() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession1");

        /**
         * 向 drools 的 Working memory 中插入 Fact 事实
         */
        Staff staff1 = new Staff();
        staff1.setName("卢瑶");
        staff1.setGender("男");
        staff1.setAge(21);

        Staff staff2 = new Staff();
        staff2.setName("张三");
        staff2.setGender("男");
        staff2.setAge(30);

        Staff staff3 = new Staff();
        staff3.setName("李四");
        staff3.setGender("男");
        staff3.setAge(50);

        Staff staff4 = new Staff();
        staff4.setName("王五");
        staff4.setGender("男");
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

        int count = kieSession.fireAllRules();
        System.out.println("执行了" + count + "条规则！");
    }
}
