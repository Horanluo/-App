/*
 * 类文件名:  QrcodeQueryServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月4日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.core.utils.ReflectUtils;
import com.alycloud.pay.gateway.api.IQrcodePassivePayService;
import com.alycloud.pay.gateway.api.IQrcodeQueryService;
import com.alycloud.pay.gateway.channel.util.FormatUtil;
import com.alycloud.pay.gateway.channel.util.SignUtil;
import com.alycloud.pay.gateway.core.qrcode.QrcodePay;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.models.qrcode.QrcodeChannelBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
import com.alycloud.pay.gateway.response.QrcodeQueryResponseBean;

/**
 * 二维码订单查询实现类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月4日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
@Slf4j
public class QrcodeQueryServiceImpl implements IQrcodeQueryService
{
    
    @Autowired
    private IQrcodePassivePayService qrcodePassivePayService;
    /**
     * 重载方法
     * @param order
     * @param result
     * @return
     */
    @Override
    public QrcodeQueryResponseBean buildResponseBean(QrcodeOrderBean order, Map<String, String> result,String merchKey)
    {
        String orderno = order.getOrderno();
        QrcodeQueryResponseBean bean = new QrcodeQueryResponseBean();
        bean.setRespCode("1");
        bean.setPayStatus(result.get("payStatus"));
//        bean.setMessage("支付成功");
        bean.setTraceno(order.getTraceno());
        bean.setPayType(Integer.toString(order.getPayType()));
        bean.setScanType(Integer.toString(order.getScanType()));
        bean.setOrderno(orderno);
        bean.setChannelOrderno(result.get("channelOrderno"));
        String sign = SignUtil.genSign(merchKey, SignUtil.createLinkString(SignUtil.paraFilter(ReflectUtils.convertToMaps(bean))));
        bean.setSign(sign);
        return bean;
    }
    
    /**
     * 重载方法
     * @param order
     * @param channel
     * @param qrcodeMerch
     * @return
     * @throws Exception 
     */
    @Override
    public Map<String, String> query(QrcodeOrderBean order, QrcodeChannelBean channel, QrcodeMerchBean qrcodeMerch) throws Exception
    {
        log.info("获取查询的操作对象");
        QrcodePay pay = qrcodePassivePayService.newQrcodePay(channel, qrcodeMerch, order.getMerchName(), order.getPayType());
        log.info("向渠道发送报文，并进行支付");
        Map<String, String> result = pay.query(order,order.getTransDate(), order.getOrderno());
        log.info("渠道返回的结果为:\r\n" + FormatUtil.formatMap(result));
        return result;
    }
    
    /**
     * 执行退款交易
     * @param order
     * @param channel
     * @param qrcodeMerch
     * @return
     * @throws Exception 
     */
    @Override
    public Map<String, String> refundOrder(QrcodeOrderBean order, QrcodeChannelBean channel, QrcodeMerchBean qrcodeMerch) throws Exception
    {
        log.info("获取查询的操作对象");
        QrcodePay pay = qrcodePassivePayService.newQrcodePay(channel, qrcodeMerch, order.getMerchName(), order.getPayType());
        long amountL = order.getAmount().multiply(BigDecimal.valueOf(100)).longValue();
        log.info("向渠道发送报文，并进行支付");
        Map<String, String> result = pay.refundOrder(order,order.getTransDate(), order.getOrderno(),amountL);
        log.info("渠道返回的结果为:\r\n" + FormatUtil.formatMap(result));
        return result;
    }
}
