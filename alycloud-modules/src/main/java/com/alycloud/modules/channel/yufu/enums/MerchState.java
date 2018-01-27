/*
 * 类文件名:  MerchState.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月14日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu.enums;

/**
 * 商户状态
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月14日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum MerchState
{
    /**
     * 待审核
     */
    NEW("待审核"),
    /**
     * 通过
     */
    PASS("通过"),
    /**
     * 拒绝
     */
    REJECTIVE("拒绝");
    
    private String text;
    
    MerchState(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
