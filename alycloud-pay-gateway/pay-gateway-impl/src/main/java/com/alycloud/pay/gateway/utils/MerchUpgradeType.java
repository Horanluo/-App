/*
 * 类文件名:  MerchUpgradeType.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月16日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.utils;

/**
 * 商户升级类型，
 * 注意，枚举顺序不可变
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月16日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum MerchUpgradeType
{
    /**
     * 商户升级
     */
    MERCH("商户升级"),
    /**
     * 代理商升级
     */
    AGENT("代理商升级");

    private String text;

    MerchUpgradeType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    } 
}
