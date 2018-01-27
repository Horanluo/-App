///*
// * 类文件名:  HxtcquickFastChannelInfo.java
// * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
// * 功能描述:  <描述>
// * 类创建人:  曾云龙
// * 创建时间:  2017年8月21日
// * 功能版本:  V001Z0001
// */
//package com.alycloud.channel.utils.hxtc;
//
//import java.math.BigDecimal;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import com.alycloud.pay.gateway.api.channel.IFastChannelInfo;
//import com.alycloud.pay.gateway.api.enums.SysChannelType;
//import com.alycloud.pay.gateway.api.enums.SysLiquidateType;
//import com.alycloud.pay.gateway.api.enums.SysT0RateType;
//
///**
// * 汇享天成费率基本信息
// * 
// * @author 曾云龙
// * @version V001Z0001
// * @date 2017年8月21日
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@ConfigurationProperties(prefix = "quick.pay")
//@Component
//public class HxtcquickFastChannelInfo implements IFastChannelInfo
//{
//    /**
//     * 最低费率
//     */
//    private BigDecimal minFee;
//    
//    private BigDecimal advanceRate;
//    
//    /**
//     * T1费率
//     */
//    private BigDecimal rateT1;
//    
//    /**
//     * D0费率
//     */
//    private BigDecimal rateT0;
//    
//    /**
//     * 前端通知地址
//     */
//    private String baseUrl;
//    
//    /**
//     * 重载方法
//     * 
//     * @return
//     */
//    @Override
//    public SysChannelType getType()
//    {
//        return SysChannelType.HXTC;
//    }
//    
//    /**
//     * 重载方法
//     * 
//     * @return
//     */
//    @Override
//    public BigDecimal getT0Rate()
//    {
//        return rateT0;
//    }
//    
//    /**
//     * 重载方法
//     * 
//     * @return
//     */
//    @Override
//    public BigDecimal getT1Rate()
//    {
//        return rateT1;
//    }
//    
//    /**
//     * 重载方法
//     * 
//     * @return
//     */
//    @Override
//    public BigDecimal getPaymentFee()
//    {
//        return advanceRate;
//    }
//    
//    /**
//     * 重载方法
//     * 
//     * @return
//     */
//    @Override
//    public BigDecimal getMinFee()
//    {
//        return minFee;
//    }
//    
//    /**
//     * 重载方法
//     * 
//     * @return
//     */
//    @Override
//    public SysT0RateType getT0RateType()
//    {
//        return SysT0RateType.FULL;
//    }
//    
//    /**
//     * 重载方法
//     * 
//     * @return
//     */
//    @Override
//    public SysLiquidateType getLiquidateType()
//    {
//        return SysLiquidateType.CHANNEL;
//    }
//    
//    /**
//     * 设置 minFee
//     * 
//     * @param 对minFee进行赋值
//     */
//    public void setMinFee(BigDecimal minFee)
//    {
//        this.minFee = minFee;
//    }
//    
//    /**
//     * 设置 advanceRate
//     * 
//     * @param 对advanceRate进行赋值
//     */
//    public void setAdvanceRate(BigDecimal advanceRate)
//    {
//        this.advanceRate = advanceRate;
//    }
//    
//    /**
//     * 设置 rateT1
//     * 
//     * @param 对rateT1进行赋值
//     */
//    public void setRateT1(BigDecimal rateT1)
//    {
//        this.rateT1 = rateT1;
//    }
//    
//    /**
//     * 设置 rateT0
//     * 
//     * @param 对rateT0进行赋值
//     */
//    public void setRateT0(BigDecimal rateT0)
//    {
//        this.rateT0 = rateT0;
//    }
//
//    /**
//     * 获取 baseUrl
//     * @return 返回 baseUrl
//     */
//    public String getBaseUrl()
//    {
//        return baseUrl;
//    }
//
//    /**
//     * 设置 baseUrl
//     * @param 对baseUrl进行赋值
//     */
//    public void setBaseUrl(String baseUrl)
//    {
//        this.baseUrl = baseUrl;
//    }
//    
//}
