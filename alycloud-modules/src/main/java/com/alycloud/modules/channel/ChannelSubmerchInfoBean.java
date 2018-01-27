/*
 * 类文件名:  ChannelSubmerchInfoBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月7日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 渠道生成的子商户号信息
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月7日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelSubmerchInfoBean implements Serializable
{
    private static final long serialVersionUID = 8705075884029965487L;

    private int id;
    
    /**
     * 机构号
     */
    private String branchno;
    
    /**
     * 代理商号
     */
    private String agentno;
    
    /**
     * 商户号
     */
    private String merchno;
    
    /**
     * 渠道商户号申请时间
     */
    private Date createdate;
    
    /**
     * 渠道编码
     */
    private String channelCode;
    
    /**
     * 渠道名称
     */
    private String channelName;
    
    /**
     * 渠道分配的上游子商户号
     */
    private String channelSubmerchno;
    
    /**
     * 支付类型,QUICKPAY： 快捷支付，WEIXI,ALIPAY
     */
    private String payType;
    
    /**
     * 0: 待审核,1：审核通过,2: 审核拒绝
     */
    private String aduitStatus;
    
    /**
     * D0提现手续费
     */
    private BigDecimal payFeeD0;
    
    /**
     * T1提现费
     */
    private BigDecimal payFeeT1;
    
    /**
     * D0支付费率
     */
    private BigDecimal d0payRate;
    
    /**
     * T1支付费率
     */
    private BigDecimal t1payRate;
    
    /**
     * 支付方式，WEIXIN,ALIPAY,QUICKPAY
     */
    private String payMethod;
    
    /**
     * 备注
     */
    private String remark;
}
