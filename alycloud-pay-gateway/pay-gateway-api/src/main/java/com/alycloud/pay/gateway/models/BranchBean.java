/*
 * 类文件名:  BranchBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月22日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月22日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchBean implements Serializable
{
    /**
     * 分公司编码
     */
    private String branchno;
    /**
     * 分公司名称
     */
    private String branchName;
    /**
     * 业务编码(商户编号的前3位)
     */
    private String bizCode;
    /**
     * 默认的MCC(用于开通的时候自动生成商户编号)
     */
    private String mcc;
    /**
     * 是否开通一机多费率(1-未开通 2-已开通)
     */
    private int openMulti;
    /**
     * 是否开通T+0(1-未开通 2-已开通)
     */
    private int openT0;
    /**
     * T+0的最低金额(例如10,以元为单位)
     */
    private String t0MinAmount;
    /**
     * T+0的交易，多收的手续费(例如0.002,以1为单位)
     */
    private String t0Rate;
    
    /**
     * 快捷支付T+1费率
     */
    private BigDecimal fastPayRateT1;
    /**
     * 快捷支付T+0费率
     */
    private BigDecimal fastPayRateT0;
}
