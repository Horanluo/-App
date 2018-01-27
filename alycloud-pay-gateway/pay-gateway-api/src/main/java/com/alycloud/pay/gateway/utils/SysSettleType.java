/*
 * 类文件名:  SysSettleType.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.utils;

/**
 * 结算方式<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年6月6日
 */
public enum SysSettleType
{
    /**
     * T0
     */
    T0("T0"),
    /**
     * T1
     */
    T1("T1");
    
    private String text;

    SysSettleType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
