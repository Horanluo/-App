/*
 * 类文件名:  QrcodePaySuccess.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月18日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.factory;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alycloud.pay.gateway.inter.QrcodeIC;
import com.alycloud.pay.gateway.mapper.QrcodeOrderMapper;
import com.alycloud.pay.gateway.mapper.QrcodeTransMapper;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;

/**
 * 二维码支付成功业务处理
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月18日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Slf4j
@Component
public class QrcodePaySuccess extends AbstractPaySuccess
{
    @Autowired
    private QrcodeOrderMapper qrcodeOrderMapper;
    
    @Autowired
    private QrcodeTransMapper qrcodeTransMapper;

    /**
     * 重载方法
     * @param orderno
     * @throws Exception
     */
    @Override
    protected void process(String orderno)
        throws Exception
    {
        QrcodeOrderBean order = qrcodeOrderMapper.getByOrderno(orderno);
        if (order == null) {
            throw new Exception("交易信息有误");
        }
        if (order.getStatus() == QrcodeIC.ORDER_STATUS_SUCCESS) {
            log.warn("扫码支付订单[{}]已经是成功状态，不能重复执行“支付成功”逻辑", order.getOrderno());
            return;
        }
        
//        trans = qrcodeSuccessService.buildQrcodeTrans(order, result.get("channelOrderno"), result.get("channelTraceno"));
//        qrcodeSuccessService.process(order, trans, "客户端二维码查询,系统自动充值");
//        qrcodeOrderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_SUCCESS, "支付成功");
//        String bankCardType = result.get("bankCardType");
//        if (null == bankCardType)
//        {
//            bankCardType = "";
//        }
//        qrcodeOrderService.updateBankCardType(order.getOrderno(), bankCardType);
        
        
//        // 更新订单状态为"已支付"
//        updateOrderStatusToSuccess(order);
//        
//        // 检查交易流水是否已经存在
//        QrcodeTrans trans = getTransByOrderno(orderno);
//        if (null != trans) {
//            // 更新支付状态为“已支付”
//            updateTransStatusToSuccess(trans);
//        } else {
//            // 创建交易流水
//            trans = addTransByOrder(order);
//        }
//        
//        String payeeMerchno = paramDao.getByCode(SystemParamCode.SYSTEM_PAYEE_MERCHNO).getValue();
//        if (order.getMerchno().equals(payeeMerchno)) {
//            new MerchUpgradeBiz().execute(trans);
//            return;
//        } else {
//            
//            // 计算分润
//            AgentTransCreatorFactory.getAgentTransCreator().process(trans);
//            // 分润充值
//            SettleUtil.agentRecharge(LogUtil.getQrcodeLog(), trans.getOrderno());
//            
//            pay(trans);
//            officalNotice(trans);
//        }
    }
    
}
