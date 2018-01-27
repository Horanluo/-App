/*
 * 类文件名:  AgentServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.alycloud.pay.gateway.api.IAgentService;
import com.alycloud.pay.gateway.inter.QrcodeIC;
import com.alycloud.pay.gateway.mapper.AgentFeeMapper;
import com.alycloud.pay.gateway.mapper.AgentTransMapper;
import com.alycloud.pay.gateway.mapper.MerchFeeMapper;
import com.alycloud.pay.gateway.mapper.MerchRelateMapper;
import com.alycloud.pay.gateway.models.MerchRelateBean;
import com.alycloud.pay.gateway.models.agent.AgentBean;
import com.alycloud.pay.gateway.models.agent.AgentTransBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeTrans;
import com.alycloud.pay.gateway.utils.AgentTransTransStatus;
import com.alycloud.pay.gateway.utils.SysSettleType;

/**
 * 代理商信息的业务处理层
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月15日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Slf4j
@Service
public class AgentServiceImpl implements IAgentService
{
    @Autowired
    private AgentFeeMapper agentFeeMapper;
    
    @Autowired
    private AgentTransMapper agentTransMapper;
    
    @Autowired
    private MerchRelateMapper merchRelateMapper;
    
    /**
     * 重载方法
     * @param trans
     * @param id
     * @return
     */
    @Override
    public void saveAgentTrans(QrcodeTrans trans, int merchId)
    {
        try {
            int count = agentTransMapper.countByRefno(trans.getOrderno());
            if (count > 0) {
                log.warn(String.format("订单分润已经存在，订单号[%s]", trans.getOrderno()));
                return;
            }
            List<AgentTransBean> result = new ArrayList<AgentTransBean>();
            List<MerchRelateBean> agents = merchRelateMapper.listByMerchId(merchId);
            int size = agents.size();
            if (size > 1) {
                BigDecimal nextFee = getAgentFee(trans, agents.get(1).getAgentno());
                AgentTransBean b = buildAgentTrans(trans);
                b.setAgentno(trans.getBranchno());
                b.setAgentName(trans.getBranchName());
                b.setAgentLevel(1);
                b.setT0AgentFee(trans.getT0AddFee());
                b.setCostFee(trans.getBranchFee());
                b.setTotalAgentFee(trans.getTotalFee().subtract(trans.getBranchFee()));// 总分润=商户手续费 - 机构手续费
                b.setAgentFee(nextFee.subtract(trans.getBranchFee()));// 机构的分润=一级代理商手续费 - 机构手续费
                result.add(b);
                for (int i = 1; i < size; i++) {
                    MerchRelateBean mr = agents.get(i);
                    AgentTransBean bean = buildAgentTrans(trans);
                    bean.setAgentno(mr.getAgentno());
                    bean.setAgentName(mr.getAgentName());
                    bean.setAgentLevel(Integer.parseInt(mr.getAgentLevel()));
                    bean.setT0AgentFee(trans.getT0AddFee());
                    BigDecimal fee = BigDecimal.ZERO;
                    if (i < size - 1) {
                        fee = getAgentFee(trans, agents.get(i + 1).getAgentno());
                    } else {
                        fee = trans.getTotalFee();
                    }
                    bean.setCostFee(trans.getBranchFee());
                    bean.setTotalAgentFee(trans.getTotalFee().subtract(nextFee));// 第N级代理商总分润=商户手续费-第N级代理商手续费
                    bean.setAgentFee(fee.subtract(nextFee));// 第N级代理商分润=第N+1级代理商手续费-第N级代理商手续费
                    result.add(bean);
                    
                    nextFee = fee;
                }
            } else {
                createBranchProfit(result, trans);
            }
            if (result != null && result.size() > 0) {
                agentTransMapper.insert(result);
            }
        } catch (Exception e) {
            log.error("代理商分润失败," + e.getMessage());
        }
    }
    
    /**
     * 计算代理商的成本
     * @param trans 交易信息
     * @param agentno 代理商号
     * @return
     * @throws Exception
     */
    private BigDecimal getAgentFee(QrcodeTrans trans, String agentno) throws Exception {
        int payType = trans.getPayType();
        int scanType = trans.getScanType();
        int settleType = trans.getSettleType();
        BigDecimal amount = trans.getTransAmount();
        BigDecimal minFee = BigDecimal.valueOf(0.01);
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("agentno", agentno);
        param.put("payType", payType);
        param.put("scanType", 1<<scanType);
        param.put("settleType", 1<<settleType);
        
        //BigDecimal rate = agentFeeMapper.getQrcodeRate(agentno, payType, 1<<scanType, 1<<settleType);
        BigDecimal rate = agentFeeMapper.getQrcodeRate(param);
        if (rate == null) {
            throw new Exception("代理商[" + agentno + "]的成本价未设置");
        }
        BigDecimal totalAgentFee = amount.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
        if (totalAgentFee.compareTo(minFee) == -1) {
            totalAgentFee = minFee;
        }
        
        if (trans.getSettleType() == SysSettleType.T0.ordinal()) {// T+0才用计算代付费（T+1虚拟账户充值，不用）
            totalAgentFee = totalAgentFee.add(trans.getPaymentFee());
        }
        
        return totalAgentFee;
    }
    
    /**
     * 创建代理商分润信息
     * @param trans
     * @return
     */
    private AgentTransBean buildAgentTrans(QrcodeTrans trans) {
        AgentTransBean bean = new AgentTransBean();
        bean.setTransDate(trans.getTransDate());
        bean.setTransTime(trans.getTransTime());
        bean.setMerchno(trans.getMerchno());
        bean.setMerchName(trans.getMerchName());
        if (trans.getPayType() == QrcodeIC.TRANS_TYPE_WX) {
            bean.setTypeMemo("微信");
        } else if (trans.getPayType() == QrcodeIC.TRANS_TYPE_ZFB) {
            bean.setTypeMemo("支付宝");
        } else {
            bean.setTypeMemo(trans.getPayType() + "");
        }
        bean.setRefno(trans.getOrderno());
        bean.setAmount(trans.getTransAmount());
        bean.setDivide(100);
        bean.setTotalFee(trans.getTotalFee());
        bean.setChannelFee(trans.getChannelFee());
        bean.setTransStatus(trans.getStatus() == QrcodeIC.ORDER_STATUS_SUCCESS 
                ? AgentTransTransStatus.SUCCESS.ordinal() : AgentTransTransStatus.NEW.ordinal());
        bean.setLiquidStatus(1);
        bean.setLiquidDate("");
        bean.setPayFee(BigDecimal.ZERO);
        bean.setBranchno(trans.getBranchno());
        bean.setBranchName(trans.getBranchName());
        bean.setSettleDate(trans.getTransDate());
        return bean;
    }
    
    /**
     * 创建机构的分润信息
     * @param result
     * @param trans
     */
    private void createBranchProfit(List<AgentTransBean> result, QrcodeTrans trans){
        AgentTransBean b = buildAgentTrans(trans);
        b.setAgentno(trans.getBranchno());
        b.setAgentName(trans.getBranchName());
        b.setAgentLevel(1);
        b.setT0AgentFee(trans.getT0AddFee());
        b.setCostFee(trans.getBranchFee());
        if (trans.getTotalFee() != null && trans.getBranchFee() != null) {
            b.setTotalAgentFee(trans.getTotalFee().subtract(trans.getBranchFee()));
            b.setAgentFee(trans.getTotalFee().subtract(trans.getBranchFee()));
            result.add(b);
        }
    }
    
    /**
     * 重载方法
     * @param superAgent
     * @return
     */
    @Override
    public AgentBean getByAgentno(String superAgent)
    {
        return null;
    }
}
