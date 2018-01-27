/*
 * 类文件名:  QrcodeMerchBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models.qrcode;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二维码商户信息
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月27日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeMerchBean implements Serializable
{
    private static final long serialVersionUID = -2879311821892381645L;
    
    private int id;
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 渠道商户ID
     */
    private String channelMerchno;
    /**
     * 渠道商户名称
     */
    private String merchName;
    /**
     * 渠道机具号
     */
    private String channelTermno;
    /**
     * 平台商户号
     */
    private String platformMerchno;
    /**
     * 商户状态
     */
    private int status;
    /**
     * 商户私钥
     */
    private String keyRsa;
    /**
     * 商户密钥
     */
    private String keyMd5;
    /**
     * 公众号的AppId(威富通渠道用到)
     */
    private String appId;
    /**
     * 公众号的秘钥(威富通渠道用到)
     */
    private String appKey;
    /**
     * 支持的结算类型(1-T+0 2-T+1)
     */
    private int settleType;
    /**
     * 支持的扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     */
    private int scanType;
    /**
     * 支持的支付方式(1-支付宝 2-微信 4-百度钱包 8-QQ钱包 16-京东钱包)
     */
    private int payType;
    
 // 恒丰T1商户号
    private String hfbankT1MerchNo;
    
    // 恒丰D0商户号
    private String hfbankD0MerchNo;
    
    //订单查询类型, 1：支付订单  3：退款
    private int queryType;
}
