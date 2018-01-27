/*
 * 类文件名:  HxtcBills.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月29日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.hfbank;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 汇享天成对账单
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月29日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HxtcBillsBean
{
    private Integer id;
    
    /**
     * 交易时间
     */
    private String transTime;
    
    /**
     * 订单号
     */
    private String orderno;
    
    /**
     * 支付状态
     */
    private String status;
    
    /**
     * 交易金额
     */
    private BigDecimal amount;
    
    /**
     * T0费率
     */
    private BigDecimal d0rate;
    
    /**
     * T0单笔手续费
     */
    private BigDecimal d0payfee;
    
    /**
     * T1费率
     */
    private BigDecimal t1rate;
    
    /**
     * T1单笔手续费
     */
    private BigDecimal t1payfee;
    
    /**
     * 手续费
     */
    private BigDecimal transferCharge;
    
    /**
     * 实际到账金额
     */
    private BigDecimal actualAmount;
    
    /**
     * 提现状态
     */
    private String cashStatus;
    
}
