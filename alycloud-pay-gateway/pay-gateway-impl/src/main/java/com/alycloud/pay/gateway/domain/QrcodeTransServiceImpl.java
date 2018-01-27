/*
 * 类文件名:  QrcodeTransServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月4日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.pay.gateway.api.IQrcodeTransService;
import com.alycloud.pay.gateway.mapper.QrcodeTransMapper;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeTrans;
import com.alycloud.pay.gateway.utils.DateUtil;

/**
 * 二维码交易业务实现类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月4日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class QrcodeTransServiceImpl implements IQrcodeTransService
{
    @Autowired
    private QrcodeTransMapper qrcodeTransMapper;
    /**
     * 根据系统订单号获取二维码订单信息
     */
    @Override
    public QrcodeTrans getByOrderno(String orderno) throws Exception
    {
        return qrcodeTransMapper.getByOrderno(orderno);
    }
    
    /**
     * 当天交易表查询不到,则查询历史交易表
     * @param orderno
     * @return
     */
    @Override
    public QrcodeTrans getOnHisotry(String orderno) throws Exception
    {
        return qrcodeTransMapper.getOnHisotry(orderno);
    }
    
    /**
     * 重载方法
     * @param order
     * @return
     */
    @Override
    public QrcodeTrans buildBasicTrans(QrcodeOrderBean order)
    {
        Date date = new Date();
        QrcodeTrans trans = new QrcodeTrans();
        /** 设置交易的基本信息 **/
        trans.setBranchno(order.getBranchno());
        trans.setBranchName(order.getBranchName());
        trans.setAgentno(order.getAgentno());
        trans.setAgentName(order.getAgentName());
        trans.setMerchno(order.getMerchno());
        trans.setMerchName(order.getMerchName());
        trans.setTransDate(DateUtil.getDate(DateUtil.DATE_FORMAT, date));
        trans.setTransTime(DateUtil.getDate(DateUtil.TIME_FORMAT, date));
        trans.setTransAmount(order.getAmount());
        trans.setRebackAmount(BigDecimal.ZERO);
        trans.setDiscountAmount(BigDecimal.ZERO);
        trans.setTraceno(order.getTraceno());
        trans.setNotifyUrl(order.getNotifyUrl());
        trans.setReturnUrl(order.getReturnUrl());
        trans.setOrderno(order.getOrderno());
        trans.setStatus(order.getStatus());
        trans.setAccountno(order.getAccountno());
        trans.setAccountName(order.getAccountName());
        trans.setCertno(order.getCertno());
        trans.setBankno(order.getBankno());
        trans.setBankName(order.getBankName());
        trans.setBankType(order.getBankName());
        trans.setMobile(order.getMobile());
        trans.setSettleType(order.getSettleType());
        trans.setTotalFee(order.getMerchFee());
        trans.setInterType(4);// 4-接口接入
        trans.setScanType(order.getScanType());// 扫码方式
        trans.setPayType(order.getPayType());// 支付方式(1-支付宝 2-微信)
        trans.setBranchFee(order.getBranchFee());
        trans.setChannelCode(order.getChannelCode());
        trans.setPartnerId(order.getChannelMerchno());
        trans.setChannelFee(order.getChannelFee());
        trans.setBankCardType(order.getBankCardType());
        return trans;
    }
    
    /**
     * 重载方法
     * @param trans
     * @return
     */
    @Override
    public int insert(QrcodeTrans trans)
    {
        return qrcodeTransMapper.insert(trans);
    }
    
    /**
     * 更新当天交易的商户通知状态 
     * @param orderno 订单号
     * @param status 状态(1-无需通知 2-通知成功 3-通知失败)
     * @return
     */
    @Override
    public int updateNotifyStatus(String orderno, Integer status)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderno", orderno);
        param.put("status", status);
        int result = qrcodeTransMapper.updateNotifyStatusOnToday(param);
        if(result == 0){
            result = qrcodeTransMapper.updateNotifyStatusOnHistory(param);
        }
        return result;
    }
}
