/*
 * 类文件名:  QrcodePassivePayServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.core.utils.ReflectUtils;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.pay.gateway.api.IQrcodePassivePayService;
import com.alycloud.pay.gateway.api.IQrcodeRouteService;
import com.alycloud.pay.gateway.channel.qrcode.hfbank.HfbankQrcodePay;
import com.alycloud.pay.gateway.channel.util.FormatUtil;
import com.alycloud.pay.gateway.channel.util.SignUtil;
import com.alycloud.pay.gateway.core.qrcode.QrcodePay;
import com.alycloud.pay.gateway.dto.QrcodeOrderDTO;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.inter.QrcodeIC;
import com.alycloud.pay.gateway.mapper.QrcodeOrderMapper;
import com.alycloud.pay.gateway.mapper.QrcodeRiskMapper;
import com.alycloud.pay.gateway.models.qrcode.QrcodeChannelBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodePassivePayRespBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeRouteBean;

import lombok.extern.slf4j.Slf4j;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月25日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@Slf4j
public class QrcodePassivePayServiceImpl implements IQrcodePassivePayService
{
    @Autowired
    private QrcodeOrderMapper qrcodeOrderMapper;
    
    @Autowired
    private QrcodeRiskMapper qrcodeRiskMapper;
    
    @Autowired
    private IQrcodeRouteService qrcodeRouteService;
    
    /**
     * 重载方法
     * 
     * @param merchno
     * @param traceno
     */
    @Override
    public void validTraceno(String merchno, String traceno)
        throws Exception
    {
        QrcodeOrderBean trans = qrcodeOrderMapper.getByTraceno(merchno, traceno);
        if (trans != null)
        {
            throw new YzyueException("94", "商户流水号重复");
        }
    }
    
    /**
     * 重载方法
     * 
     * @param qrcodeOrderDTO
     * @param merchKey
     */
    @Override
    public void validSignature(QrcodeOrderDTO qrcodeOrderDTO, String merchKey)
    {
        // String systemSign = GuofuUtil.signature(log, param, keyInfo, "GBK");
        // String signature = param.get("signature");
        // if (!systemSign.equalsIgnoreCase(signature)) {
        // throw new YzyueException("A0", "签名有误,系统签名[" + systemSign + "],商户签名[" + signature + "]");
        // }
    }
    
    /**
     * 重载方法
     * 
     * @param merch
     * @param notifyUrl
     * @param object
     * @param amount
     * @param payType
     * @param settleType
     */
    @Override
    public void validRiskInfo(MerchInfo merch, String notifyUrl, Object object, BigDecimal amount, int payType,
        int settleType)
        throws Exception
    {
        log.info("验证当前商户[" + merch.getMerchno() + "]是否属于白名单商户,机构号[" + merch.getBranchno() + "]");
        if (!(qrcodeRiskMapper.isWhiteMerch(merch.getBranchno(), merch.getMerchno()) > 0))
        {
            log.info("当前商户属于白名单商户，无需验证风控信息");
        }
        else
        {
            log.info("验证当前商户[" + merch.getMerchno() + "]是否属于黑名单商户");
            qrcodeRiskMapper.isBlackMerch(merch.getBranchno(), merch.getMerchno());
            log.info("当前商户不属于黑名单商户");
            // if (!StrUtil.isEmpty(notifyUrl)) {
            // log.info("验证上送的通知地址[" + notifyUrl + "]是否规范");
            // qrcodeRiskMapper.limitNotifyUrl(notifyUrl, merch, payType);
            // log.info("通知地址验证通过");
            // }
            // if (!StrUtil.isEmpty(returnUrl)) {
            // log.info("验证上送的返回地址[" + returnUrl + "]是否规范");
            // qrcodeRiskMapper.limitReturnUrl(returnUrl, merch, payType);
            // log.info("返回地址验证通过");
            // }
            log.info("验证交易限额信息,当前交易金额[" + amount + "]");
            qrcodeRiskMapper.validRisk(merch, payType, amount);
            log.info("交易限额验证成功");
        }
        if (settleType == 0)
        {
            log.info("验证当前时间能否进行T+0交易");
            qrcodeRiskMapper.validT0Time();
            log.info("T+0交易时间验证通过");
        }
    }
    
    /**
     * 重载方法
     * 
     * @param order
     * @return
     * @throws YzyueException
     */
    @Override
    public QrcodeRouteBean qrcodeRoute(QrcodeOrderBean order)
        throws YzyueException
    {
        int payType = order.getPayType();
        int scanType = order.getScanType();
        int settleType = order.getSettleType();
        String merchno = order.getMerchno();
        String agentno = order.getAgentno();
        String branchno = order.getBranchno();
        BigDecimal amount = order.getAmount();
        log.info("根据接口类型[4],支付方式[" + payType + "],扫码方式[" + scanType + "],结算类型[" + settleType + "]和交易金额[" + amount
            + "]查找路由信息");
        QrcodeRouteBean route = route(4, payType, scanType, settleType, amount, branchno, agentno, merchno);
        log.info("查找到路由信息:" + route);
        return route;
    }
    
    /**
     * 查询系统后台配置的路由信息
     * 
     * @param interType 接口类型(1-APP支付 2-传统POS支付 4-接口接入)
     * @param scanType 扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     * @param payType 支付方式(1-支付宝 2-微信)
     * @param settleType 结算类型(1-T+0结算 2-T+1结算)
     * @param amount 交易金额
     * @param branchno 机构号
     * @param agentno 代理商号
     * @param merchno 商户号
     * @return
     * @throws YzyueException
     */
    private QrcodeRouteBean route(Integer interType, Integer payType, Integer scanType, Integer settleType,
        BigDecimal amount, String branchno, String agentno, String merchno)
        throws YzyueException
    {
        try
        {
            log.info("查找该平台商户[" + merchno + "]所对应的渠道商户");
            List<QrcodeRouteBean> routes = qrcodeRouteService.routeByPMerch(payType, scanType, settleType, merchno);
            if (routes == null || routes.size() == 0)
            {
                log.warn("找不到该商户[" + merchno + "所对应的渠道商户");
                log.info("查询该商户[" + merchno + "]配置的特定路由");
                routes = qrcodeRouteService.routeByMerch(interType, payType, scanType, settleType, amount, merchno);
                if (routes == null || routes.size() == 0)
                {
                    log.warn("找不到该商户[" + merchno + "]的特殊路由");
                    log.info("查询是否为该代理商[" + agentno + "]配置了特定的路由");
                    routes =
                        qrcodeRouteService.routeByAgent(interType,
                            payType,
                            scanType,
                            settleType,
                            amount,
                            branchno,
                            agentno);
                    if (routes == null || routes.size() == 0)
                    {
                        log.warn("找不到该代理商[" + agentno + "]的特殊路由");
                        log.info("查询是否为该机构[" + branchno + "]配置了特定的路由");
                        routes =
                            qrcodeRouteService.routeByBranch(interType, payType, scanType, settleType, amount, branchno);
                        if (routes == null || routes.size() == 0)
                        {
                            log.warn("找不到该机构[" + branchno + "]的特殊路由");
                            log.info("查询其它的有效路由");
                            routes = qrcodeRouteService.routeByOther(interType, payType, scanType, settleType, amount);
                        }
                    }
                }
            }
            if (routes == null || routes.size() == 0)
            {
                throw new YzyueException("58", "找不到二维码路由信息");
            }
            else
            {
                log.info("查找成功[" + routes.size() + "]");
            }
            int size = routes.size();
            int index = (int)(size * Math.random());
            QrcodeRouteBean qrcodeRoute = routes.get(index);
            return qrcodeRoute;
        }
        catch (Exception e)
        {
            log.error("二维码路由失败", e);
            throw new YzyueException("58", "找不到二维码路由信息");
        }
    }
    
    /**
     * 重载方法
     * 
     * @param order
     * @param channel
     * @param qrcodeMerch
     * @param goodsName
     * @return
     */
    @Override
    public Map<String, String> passivePay(QrcodeOrderBean order, QrcodeChannelBean channel,
        QrcodeMerchBean qrcodeMerch, String goodsName,MerchInfo merch) throws Exception
    {
        log.info("获取被扫的操作对象");
        QrcodePay pay = newQrcodePay(channel, qrcodeMerch, goodsName, order.getPayType());
        if (order.getSettleType() == QrcodeIC.SETTLE_T0)
        {
            if (pay.supportT0())
            {
                pay.setSettleInfo(order.getAccountno(),
                    order.getAccountName(),
                    order.getBankno(),
                    order.getBankType(),
                    order.getBankName(),
                    order.getCertno(),
                    order.getMobile(),
                    order.getMerchFee());
            }
            else
            {
                throw new YzyueException("59", "当前不支持T+0结算");
            }
        }
        long amountL = order.getAmount().multiply(BigDecimal.valueOf(100)).longValue();
        log.info("向渠道发送报文，并进行支付");
        pay.setQrcodeOrderBean(order);
        Map<String, String> result = pay.passivePay(order.getSettleType(), order.getOrderno(), amountL);
        log.info("渠道返回的结果为:\r\n" + FormatUtil.formatMap(result));
        return result;
    }
    
    /**
     * 创建支付对象
     * 
     * @param channel
     * @param merch
     * @param merchName
     * @param payType
     * @return
     * @throws YzyueException
     */
    public QrcodePay newQrcodePay(QrcodeChannelBean channel, QrcodeMerchBean qrcodeMerch, String merchName, int payType)
        throws YzyueException
    {
        String channelCode = channel.getChannelCode();
        // if("GUOCAI".equals(channelCode)){
        // return new GuoCaiQrcodePay(log, channel, merch, merchName, payType);
        // }else if("GUOFU".equals(channelCode)){
        // return new GuoFuQrcodePay(log, channel, merch, merchName, payType);
        // }else if("HRT".equals(channelCode)){
        // return new HrtQrcodePay(log, channel, merch, payType);
        // }else if("SHXA".equals(channelCode)){
        // return new ShxaQrcodePay(log, channel, merch, merchName, payType);
        // }else if("WFT".equals(channelCode)){
        // return new WftQrcodePay(log, channel, merch, merchName, payType);
        // }else if("XMMS".equals(channelCode)){
        // return new XmmsQrcodePay(log, channel, merch, merchName, payType);
        // }else if("CISCO".equals(channelCode)){
        // return new CiscoQrcodePay(log, channel, merch, merchName, payType);
        // }else if("SDYL".equals(channelCode)){
        // return new SdylQrcodePay(log, channel, merch, merchName, payType);
        // }else if("HFT".equals(channelCode)){
        // return new HftQrcodePay(log, channel, merch, merchName, payType);
        // }else if("GZMS".equals(channelCode)){
        // return new GzmsQrcodePay(log, channel, merch, merchName, payType);
        // }else if("IELPM".equals(channelCode)){
        // return new IelpmQrcodePay(log, channel, merch, merchName, payType);
        // }else if("JSDZ".equals(channelCode)){
        // return new JsdzQrcodePay(log, channel, merch, merchName, payType);
        // }else if("HFBANKD0".equals(channelCode)){
        // return new HfbankQrcodePay(log, channel, merch, merchName, payType);
        // }else if("HFBANKT1".equals(channelCode)){
        // return new HfbankQrcodePay(log, channel, merch, merchName, payType);
        // }else{
        // throw new YzyueException("56", "未知的渠道编码");
        // }
        
        if ("HFBANKD0".equals(channelCode))
        {
            return new HfbankQrcodePay(channel, qrcodeMerch, merchName, payType);
        }
        else if ("HFBANKT1".equals(channelCode))
        {
            return new HfbankQrcodePay(channel, qrcodeMerch, merchName, payType);
        }
        else
        {
            throw new YzyueException("56", "未知的渠道编码");
        }
    }
    
    /**
     * 重载方法
     * 
     * @param qrcodeOrderDTO
     * @param order
     * @param result
     * @return
     */
    @Override
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
    public QrcodePassivePayRespBean buildResponseBean(QrcodeOrderDTO qrcodeOrderDTO, QrcodeOrderBean order, String merchKey,
        Map<String, String> result)
    {
        //根据不同渠道类型做处理
        String channelType = qrcodeOrderDTO.getChannel();
        QrcodePassivePayRespBean bean = new QrcodePassivePayRespBean();
        bean.setMerchno(order.getMerchno());
        bean.setTraceno(order.getTraceno());
        bean.setRespCode(result.get("respCode"));
        bean.setRefno(order.getOrderno());
        bean.setRemark(qrcodeOrderDTO.getRemark());
        if ("1".equals(bean.getRespCode()))
        {
            bean.setMessage("下单成功");
           
            if(channelType.equals("wxPubQR") || channelType.equals("alipayQR") || channelType.equals("jdQR") ){
                //展示二维码，请商户调用第三方库将code_url生成二维码图片
                bean.setBarCode(result.get("barCode"));
            }else if(channelType.equals("wxApp")){
                //请商户调用sdk控件发起支付
                bean.setBarCode(result.get("payCode"));
            }else if(channelType.equals("wxMicro") || channelType.equals("alipayMicro") || channelType.equals("jdMicro")){
                //支付成功后的处理，更新订单状态等
            }else {
                bean.setBarCode(result.get("payCode"));
            }
        }
        else
        {
            bean.setMessage(result.get("message"));
        }
        
        String sign = SignUtil.genSign(merchKey, SignUtil.createLinkString(SignUtil.paraFilter(ReflectUtils.convertToMaps(bean))));
        bean.setSign(sign);
        return bean;
    }
    
    /**
     * 重载方法
     * @param merchno
     * @param traceno
     * @return
     */
    @Override
    public int validRefundTraceno(String merchno, String traceno) throws YzyueException
    {
        QrcodeOrderBean trans = qrcodeOrderMapper.getByRefundTraceno(merchno, traceno);
        if (trans != null)
        {
            throw new YzyueException("94", "商户退款流水号重复");
        }
        return 0;
    }
}
