/*
 * 类文件名:  YufuPayType.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu.enums;

/**
 * 支付服务类型
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum YufuPayServiceType
{
    /**
     * 微信
     */
    WX("微信"),
    /**
     * 手机QQ
     */
    QQ("手机QQ"),
    /**
     * 支付宝
     */
    ZFB("支付宝"),
    /**
     * 银行卡
     */
    CARD("银行卡"),
    /**
     * 快捷支付
     */
    KJ("快捷支付"),
    /**
     * 微信APP
     */
    WXAPP("微信APP");
    
    private String text;
    
    YufuPayServiceType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
