/*
 * 类文件名:  FastOrderServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain.fast;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.modules.channel.ChannelSubmerchInfoBean;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.pay.gateway.api.IFastOrderService;
import com.alycloud.pay.gateway.api.channel.IFastChannelInfo;
import com.alycloud.pay.gateway.api.enums.FastOrderStatus;
import com.alycloud.pay.gateway.api.enums.SysT0RateType;
import com.alycloud.pay.gateway.condition.QuickRequestCondition;
import com.alycloud.pay.gateway.mapper.AgentMapper;
import com.alycloud.pay.gateway.mapper.BranchMapper;
import com.alycloud.pay.gateway.mapper.FastOrderMapper;
import com.alycloud.pay.gateway.models.BranchBean;
import com.alycloud.pay.gateway.models.agent.AgentBean;
import com.alycloud.pay.gateway.models.quick.FastOrder;
import com.alycloud.pay.gateway.utils.SysSettleType;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class FastOrderServiceImpl implements IFastOrderService
{
    @Autowired
    private AgentMapper agentMapper;
    
    @Autowired
    private BranchMapper branchMapper;
    
    @Autowired
    private FastOrderMapper fastOrderMapper;

    /**
     * 重载方法
     * @param merch
     * @param channelInfo
     * @param condition
     * @return
     */
    @Override
    public FastOrder buildFastOrder(MerchInfo merch, IFastChannelInfo channelInfo, ChannelSubmerchInfoBean channelSubmerchInfo,QuickRequestCondition condition)
    {
        AgentBean agent =agentMapper.getByAgentno(merch.getSuperAgent());
        BranchBean branch = branchMapper.getByBranchno(merch.getBranchno());
        SysSettleType settleType = SysSettleType.T1;
        if (isD0Time() &&  condition.getAmount().compareTo(new BigDecimal("100")) != -1) {// TODO 100以上才D0
            settleType = SysSettleType.T0;
        }
        
        final BigDecimal amount = condition.getAmount();
        BigDecimal channelFee;
        BigDecimal branchFee;
        BigDecimal merchFee;
        if (settleType == SysSettleType.T0) {
            if (channelInfo.getT0RateType() == SysT0RateType.INCREMENT) {// 增量方式计算
                channelFee = statFeeOnT0AddRate(amount, channelInfo.getT0Rate(), channelInfo.getT1Rate());
                branchFee = statFeeOnT0AddRate(amount, branch.getFastPayRateT0(), branch.getFastPayRateT1());
                merchFee = statFeeOnT0AddRate(amount, channelSubmerchInfo.getD0payRate(), channelSubmerchInfo.getT1payRate());
            } else {// 全量方式计算
                channelFee = amount.multiply(channelInfo.getT0Rate());
                branchFee = amount.multiply(branch.getFastPayRateT0());
                merchFee = amount.multiply(channelSubmerchInfo.getD0payRate());
            }
            
        } else {
            channelFee = amount.multiply(channelInfo.getT1Rate());
            branchFee = amount.multiply(branch.getFastPayRateT1());
            merchFee = amount.multiply(channelSubmerchInfo.getT1payRate());
        }
        
        // 最低手续费
//        final BigDecimal minFee = channelInfo.getMinFee();
        final BigDecimal minFee = channelInfo.getMinFee();
        if (channelFee.compareTo(minFee) == -1) {
            channelFee = minFee;
        }
        if (branchFee.compareTo(minFee) == -1) {
            branchFee = minFee;
        }
        if (merchFee.compareTo(minFee) == -1) {
            merchFee = minFee;
        }
        
        // 代付费
        final BigDecimal paymentFee = channelInfo.getPaymentFee();
        if (settleType == SysSettleType.T0) {
            channelFee = channelFee.add(paymentFee);
            branchFee = branchFee.add(paymentFee);
            merchFee = merchFee.add(paymentFee);
        }
        
        Date date = new Date();
        FastOrder order = new FastOrder();
        order.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        order.setAgentName(null == agent ? branch.getBranchName() : agent.getAgentName());
        order.setAgentno(null == agent ? branch.getBranchno() : agent.getAgentno());
        order.setAmount(amount);
        order.setPayerRemark(condition.getPayerRemark());
        order.setBankCard(condition.getCardno());
        order.setBankName(condition.getBankName());
        order.setBranchFee(branchFee);
        order.setBranchName(branch.getBranchName());
        order.setBranchno(branch.getBranchno());
        order.setChannelFee(channelFee);
        //order.setChannelOrderno(channelOrderno);
        order.setChannelType(channelInfo.getType().ordinal());
        //order.setDescr(descr);
        order.setIdCard(condition.getIdentityCard());
        order.setLiquidateType(channelInfo.getLiquidateType().ordinal());
        order.setMerchFee(merchFee);
        order.setMerchName(merch.getMerchName());
        order.setMerchno(merch.getMerchno());
        order.setMobile(condition.getMobile());
        order.setOrderno(genOrderno(merch.getMerchno()));
        order.setSettleType(settleType.ordinal());
        order.setStatus(FastOrderStatus.NEW.ordinal());
        order.setTrueName(condition.getAccountName());
        order.setNotifyUrl(condition.getNotifyUrl());
        order.setCallbackUrl(condition.getCallbackUrl());
        order.setOutTraceno(condition.getOutTraceno());
        return order;
    }
    
    private boolean isD0Time() {
        
        Calendar cal = Calendar.getInstance();
        long time = cal.getTimeInMillis();
        cal.set(Calendar.HOUR_OF_DAY, 8); // TODO 7点之前T1结算
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long d0StartTime = cal.getTimeInMillis();
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 22); // TODO 22点之后T1结算
        long d0EndTime = cal.getTimeInMillis();
        
        return time > d0StartTime && time < d0EndTime;
    }
    
    private static BigDecimal statFeeOnT0AddRate(final BigDecimal amount, BigDecimal t0Rate, BigDecimal t1Rate) {
        BigDecimal t0AddRate = t0Rate.subtract(t1Rate);
        BigDecimal t1Fee = amount.multiply(t1Rate);
        BigDecimal t0AddFee = amount.subtract(t1Fee).multiply(t0AddRate);
        return t1Fee.add(t0AddFee);
    }
    
    public static String genOrderno(String merchno) {
        String system = "0";
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String date = sdf.format(new Date());
        String refno = merchno;
        if (refno.length() > 9) {
            refno = refno.substring(refno.length() - 9);
        }
        String rand0 = Integer.toString((int)(9 * Math.random()));
        String rand1 = Integer.toString((int)(1000 + 8999 * Math.random()));
        return system + rand0 + date + refno + rand1;
    }
    
    /**
     * 重载方法
     * @param fastOrder
     * @return
     */
    @Override
    public int insert(FastOrder fastOrder)
    {
        return fastOrderMapper.insert(fastOrder);
    }
    
    /**
     * 重载方法
     * @param params
     * @return
     */
    @ServiceLogAnnotation(moduleName="查询快捷支付订单列表")
    public List<FastOrder> search(Map<String, String> params)
    {
        return fastOrderMapper.search(params);
    }
    
    /**
     * 重载方法
     * @param orderno
     * @return
     */
    @ServiceLogAnnotation(moduleName="根据快捷支付流水号查询快捷支付订单")
    public FastOrder searchByOrderno(String orderno)
    {
        return fastOrderMapper.searchByOrderno(orderno);
    }
    
    /**
     * 
     */
    @ServiceLogAnnotation(moduleName="根据商户号和支付流水号查询快捷支付订单")
    public FastOrder queryOrder(String merchno, String traceno)
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("merchno", merchno);
        params.put("orderno", traceno);
        List<FastOrder> list = fastOrderMapper.search(params);
        if(list == null || list.size() == 0 || list.size() > 1)
        {
            return null;
        }
        return list.get(0);
    }
    
    /**
     * 重载方法
     * @param orderno
     * @param status
     * @param message
     * @return
     */
    @Override
    public int updateStatusInfo(String orderno, int status, String message)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderno", orderno);
        param.put("status", status);
        param.put("statusDesc", message);
        return fastOrderMapper.updateStatusInfo(param);
    }
}
