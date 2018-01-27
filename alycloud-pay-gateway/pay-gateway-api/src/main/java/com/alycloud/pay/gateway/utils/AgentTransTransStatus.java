/*
 * 类文件名:  AgentTransTransStatus.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.utils;

/**
 * 代理商分润状态
 * @author Moyq5
 * @date 2017年4月7日
 */
public enum AgentTransTransStatus
{
    /**
     * 未处理
     */
    NEW("未处理"),
    /**
     * 成功
     */
    SUCCESS("成功"),
    /**
     * 强制成功
     */
    FORCE_SUCCESS("强制成功"),
    /**
     * 强制失败
     */
    FORCE_FAIL("强制失败");
    
    private String text;

    AgentTransTransStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
