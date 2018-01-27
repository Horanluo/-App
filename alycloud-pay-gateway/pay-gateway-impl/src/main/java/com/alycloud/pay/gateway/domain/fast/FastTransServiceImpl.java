/*
 * 类文件名:  FastTransServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月6日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain.fast;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.modules.quickpay.FastTransBean;
import com.alycloud.pay.gateway.api.IFastTransService;
import com.alycloud.pay.gateway.mapper.FastTransMapper;
import com.alycloud.pay.gateway.models.quick.FastOrder;

/**
 * 快捷支付交易
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月6日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class FastTransServiceImpl implements IFastTransService
{
    @Autowired
    private FastTransMapper fastTransMapper;
    
    /**
     * 重载方法
     * @param entity
     * @return
     */
    @Override
    public int insert(FastOrder order)
    {
        FastTransBean trans = new FastTransBean();
        trans.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        trans.setAgentName(order.getAgentName());
        trans.setAgentno(order.getAgentno());
        trans.setAmount(order.getAmount());
        trans.setBankCard(order.getBankCard());
        trans.setBankName(order.getBankName());
        trans.setBranchFee(order.getBranchFee());
        trans.setBranchName(order.getBranchName());
        trans.setBranchno(order.getBranchno());
        trans.setChannelFee(order.getChannelFee());
        trans.setChannelOrderno(order.getChannelOrderno());
        trans.setChannelType(order.getChannelType());
        trans.setDescr(order.getDescr());
        trans.setIdCard(order.getIdCard());
        trans.setLiquidateType(order.getLiquidateType());
        trans.setMerchFee(order.getMerchFee());
        trans.setMerchName(order.getMerchName());
        trans.setMerchno(order.getMerchno());
        trans.setMobile(order.getMobile());
        trans.setOrderno(order.getOrderno());
        trans.setSettleDescr("未结算");
        trans.setSettleStatus(0);
        trans.setSettleTime(null);
        trans.setSettleType(order.getSettleType());
        trans.setStatus(order.getStatus());
        trans.setTrueName(order.getTrueName());
        trans.setPayerRemark(order.getPayerRemark());
        return fastTransMapper.insert(trans);
    }
    
    /**
     * 重载方法
     * @param orderno
     * @return
     */
    @Override
    public FastTransBean searchByOrderno(String orderno)
    {
        return fastTransMapper.searchByOrderno(orderno);
    }
}
