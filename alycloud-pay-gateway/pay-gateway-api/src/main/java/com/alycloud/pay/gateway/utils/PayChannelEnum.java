/*
 * 类文件名:  HfbankPayChannelEnum.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月19日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.utils;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月19日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum PayChannelEnum
{
    /**
     * 微信公众账号支付
     */
    WEIXIN_PUB("wxPub"),
    
    /**
     * 微信公众账号扫码支付
     */
    WEIXIN_PUBQR("wxPubQR"),
    
    /**
     * 微信app支付
     */
    WEIXIN_APP("wxApp"),
    
    /**
     * 微信付款码支付
     */
    WEIXIN_MICRO("wxMicro"),
    
    /**
     * 支付宝扫码支付
     */
    ALIPAY_QR("alipayQR"),
    
    /**
     * 支付宝APP支付
     */
    ALIPAY_APP("alipayApp"),
    
    /**
     * 支付宝付款码支付
     */
    ALIPAY_MICRO("alipayMicro"),
    
    /**
     * 京东支付
     */
    JD_PAY("jdPay"),
    
    /**
     * 京东快捷支付
     */
    JD_QPAY("jdQpay"),
    
    /**
     * 京东网关
     */
    JD_GATEWAY("jdGateway"),
    
    /**
     * 京东付款码支付
     */
    JD_MICRO("jdMicro"),
    
    /**
     * 京东付款码支付
     */
    JD_QR("jdQR"),
    
    /**
     * 网关支付
     */
    GATEWAY("gateway"),
    
    /**
     * 快捷支付
     */
    QPAY("qpay"),
    
    /**
     * ' 银联扫码支付
     */
    UNIONPAY_QR("unionpayQR"),
    
    /**
     * QQ钱包扫码
     */
    QQWALLET("qqwalletQR");
    
    private  String value;
    
    private PayChannelEnum(String value)
    {
        this.value = value;
    }

    /**
     * 获取 value
     * @return 返回 value
     */
    public String getValue()
    {
        return value;
    }
    
    public static void main(String[] args)
    {
        System.out.println(PayChannelEnum.ALIPAY_APP.getValue());
    }
}
