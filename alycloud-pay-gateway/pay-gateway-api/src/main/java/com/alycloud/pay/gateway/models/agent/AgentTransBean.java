/*
 * 类文件名:  AgentTransBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models.agent;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代理商分润信息
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月15日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentTransBean implements Serializable
{
    private static final long serialVersionUID = -112322481146603251L;
    
    private int id;
    /**
     * 交易日期
     */
    private String transDate;
    /**
     * 交易时间
     */
    private String transTime;
    /**
     * 商户号
     */
    private String merchno;
    /**
     * 商户名称
     */
    private String merchName;
    /**
     * 代理商号
     */
    private String agentno;
    /**
     * 代理商名称
     */
    private String agentName;
    /**
     * 交易类型名称
     */
    private String typeMemo;
    /**
     * 订单号
     */
    private String refno;
    /**
     * 交易金额
     */
    private BigDecimal amount;
    /**
     * 分成比例
     */
    private int divide;
    /**
     * 商户手续费
     */
    private BigDecimal totalFee;
    /**
     * 渠道成本
     */
    private BigDecimal channelFee;
    /**
     * 代理商成本
     */
    private BigDecimal costFee;
    /**
     * 代理商总的手续费
     */
    private BigDecimal totalAgentFee;
    /**
     * 代理商分润
     */
    private BigDecimal agentFee;
    /**
     * 交易状态
     */
    private int transStatus;
    /**
     * 清算状态
     */
    private int liquidStatus;
    /**
     * 清算日期
     */
    private String liquidDate;
    /**
     * 提现费分成
     */
    private BigDecimal payFee;
    /**
     * 机构号
     */
    private String branchno;
    /**
     * 机构名称
     */
    private String branchName;
    /**
     * 清算日期
     */
    private String settleDate;
    /**
     * 代理商级别
     */
    private int agentLevel;
    /**
     * T+0新增手续费
     */
    private BigDecimal t0AgentFee;
}
