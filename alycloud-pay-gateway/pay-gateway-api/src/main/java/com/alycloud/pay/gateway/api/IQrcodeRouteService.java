/*
 * 类文件名:  IQrcodeRouteService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api;

import java.math.BigDecimal;
import java.util.List;

import com.alycloud.pay.gateway.models.qrcode.QrcodeRouteBean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月27日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IQrcodeRouteService
{

    /**
     * 获取某个商户特定的可用的渠道信息
     * 
     * @param interType
     *            接口类型(1-APP支付 2-传统POS支付 4-接口接入)
     * @param scanType
     *            扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     * @param payType
     *            支付方式(1-支付宝 2-微信)
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param amount
     *            交易金额
     * @param merchno
     *            商户号
     * @return
     * @throws Exception
     */
    List<QrcodeRouteBean> routeByPMerch(Integer payType, Integer scanType, Integer settleType, String merchno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月27日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    List<QrcodeRouteBean> routeByMerch(Integer interType, Integer payType, Integer scanType, Integer settleType,
        BigDecimal amount, String merchno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月27日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    List<QrcodeRouteBean> routeByAgent(Integer interType, Integer payType, Integer scanType, Integer settleType,
        BigDecimal amount, String branchno, String agentno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月27日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    List<QrcodeRouteBean> routeByBranch(Integer interType, Integer payType, Integer scanType, Integer settleType,
        BigDecimal amount, String branchno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月27日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    List<QrcodeRouteBean> routeByOther(Integer interType, Integer payType, Integer scanType, Integer settleType,
        BigDecimal amount);
    
}
