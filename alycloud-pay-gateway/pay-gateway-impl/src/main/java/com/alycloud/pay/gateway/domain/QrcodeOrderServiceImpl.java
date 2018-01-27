/*
 * 类文件名:  QrcodeOrderServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.pay.gateway.api.IQrcodeOrderService;
import com.alycloud.pay.gateway.api.IQrcodeSettleService;
import com.alycloud.pay.gateway.dto.QrcodeOrderDTO;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.inter.QrcodeIC;
import com.alycloud.pay.gateway.mapper.AgentMapper;
import com.alycloud.pay.gateway.mapper.BranchMapper;
import com.alycloud.pay.gateway.mapper.MerchMapper;
import com.alycloud.pay.gateway.mapper.QrcodeOrderMapper;
import com.alycloud.pay.gateway.models.BranchBean;
import com.alycloud.pay.gateway.models.agent.AgentBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;

/**
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月25日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class QrcodeOrderServiceImpl implements IQrcodeOrderService
{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Autowired
    private BranchMapper branchMapper;
    
    @Autowired
    private AgentMapper agentMapper;
    
    @Autowired
    private MerchMapper merchMapper;
    
    @Autowired
    private QrcodeOrderMapper qrcodeOrderMapper;
    
    @Autowired
    private IQrcodeSettleService qrcodeSettleService;
    
    /**
     * 获取二维码订单的参考号
     * 
     * @return
     * @throws Exception
     */
    public String genRefno()
        throws Exception
    {
        return qrcodeOrderMapper.genRefno();
    }
    
    /**
     * 根据上送的参数构造订单信息
     * 
     * @param qrcodeOrderDTO
     * @param orderno
     * @param payTypePassive
     * @return
     */
    @Override
    public QrcodeOrderBean buildByParam(QrcodeOrderDTO qrcodeOrderDTO, String orderno, int payTypePassive,
    	MerchInfo merch)
    {
        Date date = new Date();
        QrcodeOrderBean bean = new QrcodeOrderBean();
        bean.setMerchno(qrcodeOrderDTO.getMerchno());
        bean.setTransDate(dateFormat.format(date));
        bean.setTransTime(timeFormat.format(date));
        bean.setPayType(qrcodeOrderDTO.getPayType());
        bean.setScanType(payTypePassive);
        bean.setTraceno(qrcodeOrderDTO.getTraceno());
        bean.setOrderno(orderno);
        bean.setAmount(qrcodeOrderDTO.getAmount());
        bean.setNotifyUrl(qrcodeOrderDTO.getNotifyUrl());
        // bean.setReturnUrl(param.get("returnUrl"));
//        if (0 == qrcodeOrderDTO.getSettleType())
//        {
//            bean.setSettleType(QrcodeIC.SETTLE_T0);
//            // bean.setMerchFee(new BigDecimal(param.get("fee")));
//        }
//        else
//        {
//            bean.setSettleType(QrcodeIC.SETTLE_T1);
//        }
        
        bean.setSettleType(qrcodeOrderDTO.getSettleType());
        
        bean.setStatus(2);// 默认为下单失败
        bean.setCertno(merch.getIdentityCard());
        bean.setMobile(merch.getMobile());
        bean.setAccountno(merch.getAccountno());
        bean.setAccountName(merch.getAccountName());
        bean.setBankno(merch.getBankno());
        String bankType = merchMapper.getBankByCardno(merch.getAccountno());
        bean.setBankType(bankType);
        bean.setBankName(merch.getBankName());
        return bean;
    }
    
    /**
     * 重载方法
     * 
     * @param order
     * @return
     */
    @Override
    public int insert(QrcodeOrderBean order)
    {
        return qrcodeOrderMapper.insert(order);
    }
    
    /**
     * 重载方法
     * 
     * @param orderno
     * @param orderStatusFailure
     * @param message
     */
    @Override
    public int updateStatusInfo(String orderno, int status, String message)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderno", orderno);
        param.put("status", status);
        param.put("statusDesc", message);
        return qrcodeOrderMapper.updateStatusInfo(param);
    }
    
    /**
     * 更新二维码的渠道信息
     * 
     * @param order
     * @return
     * @throws Exception
     */
    @Override
    public int updateChannelInfo(QrcodeOrderBean order)
    {
       return qrcodeOrderMapper.updateChannelInfo(order);
    }
    
    /**
     * 重载方法
     * 
     * @param order
     * @param branch
     * @param agent
     * @param merch
     * @throws Exception 
     */
    @Override
    public void setMerchInfo(QrcodeOrderBean order, BranchBean branch, AgentBean agent, MerchInfo merch) throws Exception
    {
        order.setBranchno(branch.getBranchno());
        order.setBranchName(branch.getBranchName());
        if (agent == null)
        {
            order.setAgentno(branch.getBranchno());
            order.setAgentName(branch.getBranchName());
        }
        else
        {
            order.setAgentno(agent.getAgentno());
            order.setAgentName(agent.getAgentName());
        }
        order.setMerchno(merch.getMerchno());
        order.setMerchName(merch.getMerchName());
        BigDecimal amount = order.getAmount();
        int payType = order.getPayType();
        int scanType = order.getScanType();
        int settleType = order.getSettleType();
        if (settleType == QrcodeIC.SETTLE_T1)
        {
            BigDecimal merchFee =
                qrcodeSettleService.calMerchFee(amount, merch.getMerchno(), payType, scanType, settleType);
            order.setMerchFee(merchFee);
        }
        
        // 江苏电子渠道计算机构手续费
        BigDecimal branchFee =
            qrcodeSettleService.calBranchFee(amount, branch.getBranchno(), payType, scanType, settleType);
        // ------------新增代付手续费--------------------
        branchFee = qrcodeSettleService.calAdvanceFee(branchFee, order.getChannelCode());
        // ------------新增代付手续费--------------------
        order.setBranchFee(branchFee);
    }
    
    /**
     * 重载方法
     * 
     * @param order
     * @return
     */
    @Override
    public int updateMerchInfo(QrcodeOrderBean order)
    {
        return qrcodeOrderMapper.updateMerchInfo(order);
    }
    
    /**
     * 重载方法
     * @param order
     * @param qrcodeMerch
     * @param object
     * @throws Exception 
     */
    @Override
    public void setChannelInfo(QrcodeOrderBean order, QrcodeMerchBean merch, String barCode) throws Exception
    {
        order.setChannelCode(merch.getChannelCode());
        order.setChannelMerchno(merch.getChannelMerchno());
        order.setChannelUrl(barCode);
        BigDecimal amount = order.getAmount();
        String channelCode = order.getChannelCode();
        int payType = order.getPayType();
        int scanType = order.getScanType();
        int settleType = order.getSettleType();
        BigDecimal channelFee = qrcodeSettleService.calChannelFee(amount, channelCode, payType, scanType, settleType);
          // ------------新增代付手续费--------------------
         //代付费待实现 TODO
        channelFee = qrcodeSettleService.calAdvanceFee(channelFee, order.getChannelCode());
        // ------------新增代付手续费--------------------
        order.setChannelFee(channelFee);
        merch.setPlatformMerchno(order.getMerchno());
        
        BigDecimal merchFee = qrcodeSettleService.calMerchFee(amount, order.getMerchno(), payType, scanType, settleType);
        // 江苏电子渠道计算机构手续费
        // ------------新增代付手续费--------------------
        merchFee = qrcodeSettleService.calAdvanceFee(merchFee, order.getChannelCode());
        order.setMerchFee(merchFee);
        // ------------新增代付手续费--------------------
    }
    
    /**
     * 重载方法
     * @param orderQueryDTO
     * @return
     */
    @Override
    public QrcodeOrderBean queryOrder(String merchno, String traceno) throws YzyueException
    {
        QrcodeOrderBean order = null;
//        if (StrUtil.isEmpty(orderQueryDTO.getRefno())) {
            order = qrcodeOrderMapper.getByTraceno(merchno, traceno);
//        } else {
//            order = qrcodeOrderMapper.getByOrderno(orderQueryDTO.getRefno());
//        }
        if (order == null) {
            throw new YzyueException("25", "找不到订单");
        }
        return order;
    }
    
    
    /**
     * 重载方法
     * @param orderno
     * @param bankCardType
     * @return
     */
    @Override
    public int updateBankCardType(String orderno, String bankCardType)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderno", orderno);
        param.put("bankCardType", bankCardType);
        
        return qrcodeOrderMapper.updateBankCardType(param);
    }
    
    /**
     * 重载方法
     * @param minute
     * @return
     */
    @Override
    public List<QrcodeOrderBean> listNotPay(int minute)
    {
        Date currentDate = new Date();
        long currentTime = currentDate.getTime();
        
        Date queryDate = new Date(currentTime - minute * 60000);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("transTime", sdf.format(queryDate));
        return qrcodeOrderMapper.listNotPay(param);
    }
    
    /**
     * 重载方法
     * @param orderno
     * @return
     */
    @Override
    public int updateQueryInfo(String orderno)
    {
        Date currentDate = new Date();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("queryTime", sdf.format(currentDate));
        param.put("orderno", orderno);
        return qrcodeOrderMapper.updateQueryInfo(param);
    }
    
    public static void main(String[] args)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int minute = 2000;
        Date currentDate = new Date();
        long currentTime = currentDate.getTime();
        Date queryDate = new Date(currentTime - minute * 60000);
        System.out.println(sdf.format(queryDate));
    }
    
    /**
     * 重载方法
     * @param order
     * @return
     */
    @Override
    public int insertRefundOrder(QrcodeOrderBean order)
    {
        return qrcodeOrderMapper.insertRefundOrder(order);
    }
    
    /**
     * 重载方法
     * @param orderno
     * @param status
     * @param message
     * @return
     */
    @Override
    public int updateRefundOrderStatusInfo(String orderno,String channelRefundNo,int status, String message)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderno", orderno);
        param.put("status", status);
        param.put("channelRefundNo", channelRefundNo);
        param.put("statusDesc", message);
        return qrcodeOrderMapper.updateRefundOrderStatusInfo(param);
    }
    
    
}
