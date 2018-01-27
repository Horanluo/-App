/*
 * 类文件名:  IFastChannelInfo.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api.channel;

import java.math.BigDecimal;

import com.alycloud.pay.gateway.api.enums.SysChannelType;
import com.alycloud.pay.gateway.api.enums.SysLiquidateType;
import com.alycloud.pay.gateway.api.enums.SysT0RateType;

/**
 * 快捷支付渠道配置信息
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IFastChannelInfo
{
    /**
     * 渠道类型
     */
    public SysChannelType getType();
    /**
     * T0费率
     */
    public BigDecimal getT0Rate();
    /**
     * T1费率
     */
    public BigDecimal getT1Rate();
    /**
     * 单笔代付费（元）
     */
    public BigDecimal getPaymentFee();
    
    /**
     * 最低手续费（元）
     */
    public BigDecimal getMinFee();
    
    /**
     * T0费率类型
     */
    public SysT0RateType getT0RateType();
    
    /**
     * 清算类型
     */
    public SysLiquidateType getLiquidateType();
    
    public String getBaseUrl();
}
