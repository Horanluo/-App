/*
 * 类文件名:  IQrcodePassivePayService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api;

import java.math.BigDecimal;
import java.util.Map;

import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.pay.gateway.core.qrcode.QrcodePay;
import com.alycloud.pay.gateway.dto.QrcodeOrderDTO;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.models.qrcode.QrcodeChannelBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodePassivePayRespBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeRouteBean;

/**
 * 二维码支付类
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月25日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IQrcodePassivePayService
{
//    ResponseBean qrcodePassivePay();
    
    void validTraceno(String merchno, String traceno) throws Exception;

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月25日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    void validSignature(QrcodeOrderDTO qrcodeOrderDTO, String merchKey);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月25日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    void validRiskInfo(MerchInfo merch, String notifyUrl, Object object, BigDecimal amount, int payType, int settleType) throws    Exception;

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @throws YzyueException 
     * @date     2017年7月25日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeRouteBean qrcodeRoute(QrcodeOrderBean order) throws YzyueException;

    /**
     * 创建返回的对象信息
     * 
     * @param param
     *            上送的参数信息
     * @param trans
     *            交易信息
     * @param result
     *            渠道交易结果
     * @return
     */
    QrcodePassivePayRespBean buildResponseBean(QrcodeOrderDTO qrcodeOrderDTO, QrcodeOrderBean order, String merchKey, Map<String, String> result);

    /**
     * 二维码被扫的业务处理
     * 
     * @param trans
     * @return
     * @throws YzyueException
     * @throws Exception
     */
    Map<String, String> passivePay(QrcodeOrderBean order, QrcodeChannelBean channel, QrcodeMerchBean qrcodeMerch,
        String goodsName,MerchInfo merch) throws Exception;
    
    /**
     * 
     * 根据不同的交易渠道返回相应渠道的业务类
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月11日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodePay newQrcodePay(QrcodeChannelBean channel, QrcodeMerchBean qrcodeMerch, String merchName, int payType)
        throws YzyueException;

    /**
     * 验证退款流水是否存在
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @throws YzyueException 
     * @date     2017年8月24日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int validRefundTraceno(String merchno, String traceno) throws YzyueException;
}
