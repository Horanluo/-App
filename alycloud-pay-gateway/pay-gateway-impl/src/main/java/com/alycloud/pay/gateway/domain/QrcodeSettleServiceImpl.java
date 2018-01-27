/*
 * 类文件名:  QrcodeSettleServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月26日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.pay.gateway.api.IBranchFeeService;
import com.alycloud.pay.gateway.api.IChannelFeeService;
import com.alycloud.pay.gateway.api.IMerchFeeService;
import com.alycloud.pay.gateway.api.IQrcodeSettleService;
import com.alycloud.pay.gateway.exception.YzyueException;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月26日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class QrcodeSettleServiceImpl implements IQrcodeSettleService
{
    @Autowired
    private IMerchFeeService merchFeeService;
    
    @Autowired
    private IChannelFeeService channelFeeService;
    
    @Autowired
    protected IBranchFeeService branchFeeService;

    /**
     * 重载方法
     * @param amount
     * @param merchno
     * @param payType
     * @param scanType
     * @param settleType
     * @return
     * @throws Exception 
     */
    @Override
    public BigDecimal calMerchFee(BigDecimal amount, String merchno, int payType, int scanType, int settleType) throws Exception
    {
        BigDecimal merchRate = merchFeeService.getQrcodeRate(merchno, payType, scanType, settleType);
        return calFee(amount, merchRate);
    }
    
    /**
     * 计算手续费
     * @param amount 交易金额
     * @param rate 费率
     * @return
     * @throws YzyueException 
     */
    private BigDecimal calFee(BigDecimal amount, BigDecimal rate) throws YzyueException{
        if(rate == null){
            throw new YzyueException("09", "费率尚未设置");
        }
        if(rate.compareTo(BigDecimal.valueOf(0.001)) == -1){
            throw new YzyueException("09", "费率设置过低");
        }
        if(rate.compareTo(BigDecimal.valueOf(0.1)) == 1){
            throw new YzyueException("09", "费率设置过高");
        }
        BigDecimal minFee = BigDecimal.valueOf(0.01);
        BigDecimal fee = amount.multiply(rate);
        fee = fee.setScale(2, BigDecimal.ROUND_UP);
        if(fee.compareTo(minFee) == -1){
            fee = minFee;
        }
        return fee;
    }

    /**
     * 重载方法
     * @param amount
     * @param branchno
     * @param payType
     * @param scanType
     * @param settleType
     * @return
     * @throws YzyueException 
     */
    @Override
    public BigDecimal calBranchFee(BigDecimal amount, String branchno, int payType, int scanType, int settleType) throws YzyueException
    {
        BigDecimal branchRate = branchFeeService.getQrcodeRate(branchno, payType, scanType, settleType);
        return calFee(amount, branchRate);
    }

    /**
     * 重载方法
     * @param branchFee
     * @param channelCode
     * @return
     */
    @Override
    public BigDecimal calAdvanceFee(BigDecimal amount, String channelCode)
    {
        if ("JSDZ".equals(channelCode))
        {
            String advanceRateVal = "1";
            BigDecimal advanceRate = new BigDecimal(advanceRateVal);
            amount = amount.add(advanceRate);
        }
        else if ("YLPay".equals(channelCode))
        {
            String advanceRateVal = "1";
            BigDecimal advanceRate = new BigDecimal(advanceRateVal);
            amount = amount.add(advanceRate);
        }else
        {
            String advanceRateVal = "0";
            BigDecimal advanceRate = new BigDecimal(advanceRateVal);
            amount = amount.add(advanceRate); 
        }
        return amount;
    }
    
    /**
     * 设置渠道的成本
     * @param trans 交易信息(必须要设置好交易金额和结算类别)
     * @param channelCode 渠道编码
     * @param payType 支付方式
     * @param scanType 扫码方式
     * @throws YzyueException
     */
    @Override
    public BigDecimal calChannelFee(BigDecimal amount, String channelCode, int payType, int scanType, int settleType) throws YzyueException
    {
        BigDecimal channelRate = channelFeeService.getQrcodeRate(channelCode, payType, scanType, settleType);
        return calFee(amount, channelRate);
    }
    
}
