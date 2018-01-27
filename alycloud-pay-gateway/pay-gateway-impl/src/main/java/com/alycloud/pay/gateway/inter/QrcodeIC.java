/*
 * 类文件名:  QrcodeIC.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.inter;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月25日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface QrcodeIC
{
    /**
     * 交易类型-支付宝
     */
    public static final int TRANS_TYPE_ZFB = 1;
    /**
     * 交易类型-微信
     */
    public static final int TRANS_TYPE_WX = 2;
    /**
     * 交易类型-百度
     */
    public static final int TRANS_TYPE_BAIDU = 4;
    /**
     * 交易类型-QQ
     */
    public static final int TRANS_TYPE_QQ = 8;
    /**
     * 交易类型-京东
     */
    public static final int TRANS_TYPE_JINGDONG = 16;
    
    /**
     * 扫码类型-主扫
     */
    public static final int PAY_TYPE_ACTIVE = 1;
    
    /**
     * 扫码类型-被扫
     */
    public static final int PAY_TYPE_PASSIVE = 2;
    
    /**
     * 扫码类型-公众号
     */
    public static final int PAY_TYPE_OFFICE = 4;
    
    /**
     * 扫码类型-WAP
     */
    public static final int PAY_TYPE_WAP = 8;
    
    /**
     * 二维码支付尚未完成
     */
    public static final int RESP_NOTPAY = 0;
    /**
     * 二维码支付成功
     */
    public static final int RESP_SUCCESS = 1;
    /**
     * 二维码支付失败
     */
    public static final int RESP_FAILURE = 2;
    /**
     * 结算方式T+0
     */
    public static final int SETTLE_T0 = 1;
    /**
     * 结算方式T+1
     */
    public static final int SETTLE_T1 = 2;

    /**
     * 订单状态(尚未支付)
     */
    public static final int ORDER_STATUS_NOT_PAY = 0;
    /**
     * 订单状态(支付成功)
     */
    public static final int ORDER_STATUS_SUCCESS = 1;
    
    /**
     * 订单状态(支付失败)
     */
    public static final int ORDER_STATUS_FAILURE = 2;
    
    /**
     * 订单状态(退款成功)
     */
    public static final int REFOUND_STATUS_SUCCESS= 3;
}
