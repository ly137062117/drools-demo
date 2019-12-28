/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.droolsdemo.mapper;

import com.baidu.droolsdemo.pojo.Rule;

import java.util.List;

/**
 * @project: drools-demo
 * @description: RuleMapper 类，与 resource/*Mapper.xml对应
 * @author: luyao12
 * @create: 2019-12-27 15:09
 **/
public interface RuleMapper {
    /**
     * 取出所有规则实体（不可取，应按条件分页查询，或者精确匹配获得想要的规则实体类）
     *
     * @return
     */
    List<Rule> selectAll();
}
