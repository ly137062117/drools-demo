/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.filter;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: drools-demo
 * @description: 自定义规则名过滤器，根据容器匹配，只有规则名存在于我们定义的 list 中的规则才会被触发
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
public class RuleNameContainsAgendaFilter implements AgendaFilter {

    /**
     * 规则名列表
     */
    private List<String> ruleNameList;

    public RuleNameContainsAgendaFilter() {
        ruleNameList = new ArrayList<>();
        ruleNameList.add("staffNameStartsWith");
        ruleNameList.add("staffAgeLessThan18");
    }

    /**
     * 重写匹配函数
     *
     * @param match
     * @return
     */
    @Override
    public boolean accept(Match match) {
        return ruleNameList.contains(match.getRule().getName());
    }
}
