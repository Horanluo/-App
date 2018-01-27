/*
 * 类文件名:  ChannelInfoNotFoundException.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.exception;

/**
 * 找不到渠道接口
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChannelInfoNotFoundException extends Exception
{
    private static final long serialVersionUID = 8975636521239891012L;

    public ChannelInfoNotFoundException() {
        super();
    }

    public ChannelInfoNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ChannelInfoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChannelInfoNotFoundException(String message) {
        super(message);
    }

    public ChannelInfoNotFoundException(Throwable cause) {
        super(cause);
    }
}
