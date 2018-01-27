/*
 * 类文件名:  IQrcodeSettleService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月26日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api;

import java.math.BigDecimal;

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
public interface IQrcodeSettleService
{

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
    BigDecimal calMerchFee(BigDecimal amount, String merchno, int payType, int scanType, int settleType) throws Exception;

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @throws YzyueException 
     * @date     2017年7月26日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    BigDecimal calBranchFee(BigDecimal amount, String branchno, int payType, int scanType, int settleType) throws YzyueException;

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
    BigDecimal calAdvanceFee(BigDecimal branchFee, String channelCode);

    /**
     * 设置渠道的成本
     * @param trans 交易信息(必须要设置好交易金额和结算类别)
     * @param channelCode 渠道编码
     * @param payType 支付方式
     * @param scanType 扫码方式
     * @throws YzyueException
     */
    BigDecimal calChannelFee(BigDecimal amount, String channelCode, int payType, int scanType, int settleType) throws YzyueException;
    
}
