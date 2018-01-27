/*
 * 类文件名:  PaySuccessFactory.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月18日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.factory;

import org.springframework.beans.factory.annotation.Autowired;

import com.alycloud.pay.gateway.utils.SysTransType;

/**
 * 支付成功处理工厂类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月18日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class PaySuccessFactory
{
    private static QrcodePaySuccess qrcodePaySuccess;
    public static AbstractPaySuccess getService(SysTransType type)
    {
        switch(type){
            case QRCODE:
                return qrcodePaySuccess;
            case FAST:
                break;
            default:
                return qrcodePaySuccess;
        }
        return null;
    }
    
    /**
     * 设置 qrcodePaySuccess
     * @param 对qrcodePaySuccess进行赋值
     */
    @Autowired
    public void setQrcodePaySuccess(QrcodePaySuccess qrcodePaySuccess)
    {
        PaySuccessFactory.qrcodePaySuccess = qrcodePaySuccess;
    }
    
    
}
