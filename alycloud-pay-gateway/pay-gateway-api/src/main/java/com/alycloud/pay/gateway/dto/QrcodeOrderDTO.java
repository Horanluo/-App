/*
 * 类文件名:  QrcodeOrderDTO.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.dto;

import java.math.BigDecimal;

import com.alycloud.pay.gateway.utils.PayChannelEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二维码订单
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月25日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeOrderDTO
{
    /**
     * 平台分配的商户号
     */
    private String merchno;
    
    /**
     * 商户密钥
     */
//    private String merchKey;
    
    /**
     * 外部流水号
     */
    private String traceno;
    
    /**
     * 交易金额
     */
    private BigDecimal amount;
    
    /**
     * 支付方式
     */
    private int payType;
    
    /**
     * 结算类型,结算方式1: T+0, 2: T+1
     */
    private int settleType;
    
    /**
     * 返回地址
     */
    private String notifyUrl;
    
    /**
     * 商品名称
     */
    private String goodsName;
    
    /**
     * 支付渠道
     */
    private String channel;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 签名
     */
    private String sign;
    
    /**
     * 版本
     */
    private String version;
}
