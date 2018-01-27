/*
 * 类文件名:  YufuPayType.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月18日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu.enums;

/**
 * 支付类型
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月18日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum YufuPayType
{
    /**
     * 扫码
     */
    CODE("扫码"),
    /**
     * 银行卡快捷支付
     */
    FAST("银行卡快捷支付");
    
    private String text;
    
    YufuPayType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
