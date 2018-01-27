/*
 * 类文件名:  QrcodeSuccessServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.pay.gateway.api.IAgentService;
import com.alycloud.pay.gateway.api.IQrcodeOrderService;
import com.alycloud.pay.gateway.api.IQrcodeSuccessService;
import com.alycloud.pay.gateway.api.IQrcodeTransService;
import com.alycloud.pay.gateway.inter.QrcodeIC;
import com.alycloud.pay.gateway.mapper.MerchMapper;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeTrans;
import com.alycloud.pay.gateway.utils.SettleUtil;
import com.alycloud.pay.gateway.utils.StrUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 二维码交易成功的业务处理
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月15日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Slf4j
@Service
public class QrcodeSuccessServiceImpl implements IQrcodeSuccessService
{
    /**
     * 商户的业务处理
     */
    @Autowired 
    private MerchMapper merchMapper;
    
    /**
     * 二维码订单的业务处理
     */
    @Autowired
    private IQrcodeOrderService qrcodeOrderService;
    
    /**
     * 二维码交易的业务处理
     */
    @Autowired
    private IQrcodeTransService qrcodeTransService;
    
    @Autowired
    private IAgentService agentService;
//    private AgentMapper agentMapper;

    /**
     * 重载方法
     * @param order
     * @param channelOrderno
     * @param channelTraceno
     * @return
     * @throws Exception 
     */
    @Override
    public QrcodeTrans buildQrcodeTrans(QrcodeOrderBean order, String channelOrderno, String channelTraceno) throws Exception
    {
        QrcodeTrans trans = null;
        if (order.getStatus() == QrcodeIC.ORDER_STATUS_SUCCESS) {
            trans = qrcodeTransService.getByOrderno(order.getOrderno());
            if (trans == null) {
                trans = qrcodeTransService.getOnHisotry(order.getOrderno());
                if(trans != null){
                    log.info("当前交易在历史中已存在,订单号[" + order.getOrderno() + "]");
                    return trans;
                }
            }else{
                log.info("当前交易已经创建,订单号[" + order.getOrderno() + "]");
                return trans;
            }
        } else {
            log.info("订单[" + order.getOrderno() + "]支付成功,更新订单状态");
            order.setStatus(QrcodeIC.ORDER_STATUS_SUCCESS);
            qrcodeOrderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_SUCCESS, "支付成功");
        }
        trans = qrcodeTransService.buildBasicTrans(order);
        trans.setChannelOrderno(channelOrderno);
        trans.setBackupOrderno(channelTraceno);
        trans.setStatus(QrcodeIC.ORDER_STATUS_SUCCESS);
        QrcodeTrans tmp = qrcodeTransService.getOnHisotry(order.getOrderno());
        if(tmp == null){
            qrcodeTransService.insert(trans);
            log.info("交易创建成功,订单号[" + order.getOrderno() + "]:\r\n" + trans);
        }
        return trans;
    }

    /**
     * 重载方法
     * @param order
     * @param trans
     * @param desc
     * @throws Exception 
     */
    @Override
    public void process(QrcodeOrderBean order, QrcodeTrans trans, String remark) throws Exception
    {
        String date = trans.getTransDate();
        String time = trans.getTransTime();
        String merchno = trans.getMerchno();
        String orderno = trans.getOrderno();
        BigDecimal amount = trans.getTransAmount();
        BigDecimal fee = trans.getTotalFee();
        /*
        if (Config.getBoolean("offical-enable")) {// 公众号推送
            if(merchUserService.existOpenId(merchno)){
                SettleUtil.offcialNotify(log, merchno, "3", "1", orderno, amount);
            }else{
                log.warn("该商户[" + merchno + "]不存在持有open-id的用户");
            }
        }else{
            log.warn("系统尚未开启公众号推送服务");
        }
        if (Config.getBoolean("virtual-enable")) {// 商户自动充值
            if(merchService.existVirtCard(merchno)){
                boolean flag = SettleUtil.merchRecharge(log, "2100", "4", date, time, merchno, orderno, amount, fee, remark);
                if(flag){
                    transService.updateRechargeStatus(orderno, 2);
                }else{
                    transService.updateRechargeStatus(orderno, 3);
                }
            }else{
                log.warn("该商户[" + merchno + "]不存在虚拟账户");
            }
        }else{
            log.warn("系统尚未开启虚拟账户充值服务");
        }
        */
        MerchInfo merch = merchMapper.getByMerchno(merchno);
        log.info("代理商分润入库");
        // app代理分润已记录
        if (1 != trans.getInterType())
        {
            agentService.saveAgentTrans(trans, merch.getId());
            SettleUtil.agentRecharge(trans.getOrderno());
        }
        
        // app不需要进行通知
        if (null != order)
        {
            String notifyUrl = buildNotifyUrl(order, merch);
            if(!StrUtil.isEmpty(notifyUrl)){
                String notifyData = buildNotifyData(order, trans, merch);
                String notifyType = "http";
                if (notifyUrl.startsWith("https://")) {
                    notifyType = "https";
                }
                boolean flag = SettleUtil.merchNotify(merchno, orderno, "2", "1", notifyType, notifyUrl, notifyData);
                if(flag){
                    qrcodeTransService.updateNotifyStatus(orderno, 2);
                }else{
                    qrcodeTransService.updateNotifyStatus(orderno, 3);
                }
            }
        }
        // 获取真实的记录信息（为了获得此前创建该实例时未付值的属性的真实值）
        trans = qrcodeTransService.getByOrderno(orderno);
//        pay(trans);
//        checkToOpenVip(trans);
//        officalNotice(trans);
    }
    
    /**
     * 获取通知的地址
     * 
     * @param order
     *            订单信息
     * @param merch
     *            商户信息
     * @return
     */
    private String buildNotifyUrl(QrcodeOrderBean order, MerchInfo merch) {
        String urlNotify = order.getNotifyUrl();
        if (StrUtil.isEmpty(urlNotify)) {
            urlNotify = merch.getOnlineUrlNotify();
            if (StrUtil.isEmpty(urlNotify)) {
                //urlNotify = Config.getString("url-notify-" + order.getBranchno());
            }
        }
        return urlNotify;
    }
    
    /**
     * 创建通知的数据
     * 
     * @param order
     *            订单信息
     * @param trans
     *            交易信息
     * @param merch
     *            商户信息
     * @return
     * @throws Exception
     */
    private String buildNotifyData(QrcodeOrderBean order, QrcodeTrans trans, MerchInfo merch) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        result.put("merchno", order.getMerchno());
        result.put("merchName", order.getMerchName());
        if (!StrUtil.isEmpty(merch.getCustomerno())) {
            result.put("customerno", merch.getCustomerno());
        }
        result.put("amount", order.getAmount().toString());
        result.put("transDate", order.getTransDate());
        result.put("transTime", order.getTransTime());
        result.put("orderno", order.getOrderno());
        result.put("payType", Integer.toString(order.getPayType()));
        result.put("traceno", order.getTraceno());
        if (!StrUtil.isEmpty(order.getOpenId())) {
            result.put("openId", order.getOpenId());
        }
        if(trans != null){
            result.put("channelOrderno", trans.getChannelOrderno());
            result.put("channelTraceno", trans.getBackupOrderno());
        }
        result.put("status", Integer.toString(order.getStatus()));
//        String signature = GuofuUtil.signature(logger, result, merch.getMerchKey(), "GBK");
//        result.put("signature", signature);
        return StrUtil.map2str(result, "GBK");
    }
}
