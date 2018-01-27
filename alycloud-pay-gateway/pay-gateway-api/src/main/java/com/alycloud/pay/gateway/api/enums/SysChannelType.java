/*
 * 类文件名:  SysChannelType.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api.enums;

/**
 * 支付渠道类型，注意枚举顺序不可变
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum SysChannelType
{
    /**
     * 江苏电子
     */
    JSDZ("JSDZ","江苏电子"),
    /**
     * 易联支付
     */
    PAYECO("PAYECO","易联支付"),
    /**
     * 江苏电子2.0（领贝）
     */
    LINKPAY("LINKPAY","江苏电子2.0"),
    /**
     * 御付
     */
    YUFU("YUFU","御付"),
    /**
     * 汇享天成
     */
    HXTC("HXTC","汇享天成"),
    /**
     * 平安
     */
    PINGAN("PINGAN","平安");
    
    private String text;
    private String code;

    SysChannelType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getCode() {
        return code;
    }
}
