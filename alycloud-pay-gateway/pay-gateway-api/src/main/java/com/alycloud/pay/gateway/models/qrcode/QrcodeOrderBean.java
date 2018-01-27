/*
 * 类文件名:  QrcodeOrderBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models.qrcode;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二维码订单
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月25日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeOrderBean implements Serializable
{

    private static final long serialVersionUID = -8803187996792720094L;
    
    private int id;
    /**
     * 机构号
     */
    private String branchno;
    /**
     * 机构名称
     */
    private String branchName;
    /**
     * 代理商号
     */
    private String agentno;
    /**
     * 代理商名称
     */
    private String agentName;
    /**
     * 商户号
     */
    private String merchno;
    /**
     * 商户名称
     */
    private String merchName;
    /**
     * 交易日期
     */
    private String transDate;
    /**
     * 交易时间
     */
    private String transTime;
    /**
     * 支付类型
     */
    private int payType;
    /**
     * 扫码方式
     */
    private int scanType;
    /**
     * 商户流水号
     */
    private String traceno;
    /**
     * 订单号
     */
    private String orderno;
    /**
     * 交易金额
     */
    private BigDecimal amount;
    /**
     * 通知地址
     */
    private String notifyUrl;
    /**
     * 返回地址
     */
    private String returnUrl;
    /**
     * 结算方式(1-T+0结算 2-T+1结算)
     */
    private int settleType;
    /**
     * 订单状态(0-下单成功 1-支付成功 2-支付失败)
     */
    private int status;
    /**
     * 状态说明
     */
    private String statusDesc;
    /**
     * 用户OpenId
     */
    private String openId;
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 渠道商户号
     */
    private String channelMerchno;
    /**
     * 通道方交易地址
     */
    private String channelUrl;
    /**
     * 商户手续费
     */
    private BigDecimal merchFee;
    /**
     * 渠道手续费
     */
    private BigDecimal channelFee;
    /**
     * 机构手续费
     */
    private BigDecimal branchFee;
    /**
     * 身份证号
     */
    private String certno;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 结算卡号
     */
    private String accountno;
    /**
     * 结算户名
     */
    private String accountName;
    /**
     * 联行号
     */
    private String bankno;
    /**
     * 银行类别
     */
    private String bankType;
    /**
     * 支行名称
     */
    private String bankName;
    
  /**
   * 银行卡类型
   */
  private String bankCardType;
}
