/*
 * 类文件名:  QuickRequestCondition.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.condition;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 快捷支付请求条件
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuickRequestCondition implements Serializable
{
    private static final long serialVersionUID = 1100866337223333034L;
    
    /**
     * 代理商编号
     */
//    private String agentno;
    
    /**
     * 商户号
     */
    private String merchno;
    
    /**
     * 商户密钥
     */
//    private String merchKey;
    
    /**
     * 交易金额，单位元
     */
    private BigDecimal amount;
    
    
    /**
     * 银行名称
     */
    private String bankName;
    
    /**
     * 下游流水号
     */
    private String outTraceno;
    
    /**
     * 支付银行卡预留手机号
     */
    private String mobile;
    
    /**
     * 付款人姓名
     */
    private String accountName;
    
    /**
     * 付款人身份证号
     */
    private String identityCard;
    
    /**
     * 支付银行卡号
     */
    private String cardno;
    
    /**
     * 支付备注
     */
    private String payerRemark;
    
    /**
     * 后台异步回调地址
     */
    private String notifyUrl;
    
    /**
     * 前台同步回调地址
     */
    private String callbackUrl;
    
    private String version;
    
    /**
     * 签名
     */
    private String sign;
}
