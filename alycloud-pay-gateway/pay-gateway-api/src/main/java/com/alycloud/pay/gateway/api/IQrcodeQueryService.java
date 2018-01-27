/*
 * 类文件名:  IQrcodeQueryService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月4日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api;

import java.util.Map;

import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.models.ResponseBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeChannelBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
import com.alycloud.pay.gateway.response.QrcodeQueryResponseBean;

/**
 * <一句话功能简述>
 * 二维码交易查询
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月4日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IQrcodeQueryService
{

    /**
     * 创建查询的响应信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月11日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeQueryResponseBean buildResponseBean(QrcodeOrderBean order, Map<String, String> result,String merchKey);

    /**
     * 执行二维码查询交易
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @throws YzyueException 
     * @throws Exception 
     * @date     2017年8月11日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    Map<String, String> query(QrcodeOrderBean order, QrcodeChannelBean channel, QrcodeMerchBean qrcodeMerch) throws YzyueException, Exception;

    /**
     * 执行退款交易
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @throws YzyueException 
     * @throws Exception 
     * @date     2017年8月18日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    Map<String, String> refundOrder(QrcodeOrderBean order, QrcodeChannelBean channel, QrcodeMerchBean qrcodeMerch) throws YzyueException, Exception;
    
}
