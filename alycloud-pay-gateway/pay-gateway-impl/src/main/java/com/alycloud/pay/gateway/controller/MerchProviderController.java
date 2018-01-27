/*
 * 类文件名:  MerchProviderController.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.channel.api.IQuickpayService;
import com.alycloud.channel.factory.QuickpayFactory;
import com.alycloud.channel.utils.hxtc.HxtcUtils;
import com.alycloud.channel.utils.hxtc.MypaysRsaDataEncryptUtil;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.utils.JSONUtils;
import com.alycloud.core.utils.ReflectUtils;
import com.alycloud.modules.channel.ChannelSubmerchInfoBean;
import com.alycloud.modules.channel.yufu.YufuChannelMerchBean;
import com.alycloud.modules.channel.yufu.YufuMerchQueryBean;
import com.alycloud.modules.channel.yufu.YufuMerchQueryResultBean;
import com.alycloud.modules.channel.yufu.YufuPayBean;
import com.alycloud.modules.channel.yufu.YufuPayResultBean;
import com.alycloud.modules.channel.yufu.enums.MerchState;
import com.alycloud.modules.channel.yufu.enums.YufuPayType;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.quickpay.FastTransBean;
import com.alycloud.pay.gateway.api.IFastOrderService;
import com.alycloud.pay.gateway.api.IFastTransService;
import com.alycloud.pay.gateway.api.IMerchProviderService;
import com.alycloud.pay.gateway.api.IQrcodeChannelService;
import com.alycloud.pay.gateway.api.IQrcodeMerchService;
import com.alycloud.pay.gateway.api.IQrcodeOrderService;
import com.alycloud.pay.gateway.api.IQrcodePassivePayService;
import com.alycloud.pay.gateway.api.IQrcodeQueryService;
import com.alycloud.pay.gateway.api.IQrcodeSuccessService;
import com.alycloud.pay.gateway.api.IQrcodeTransService;
import com.alycloud.pay.gateway.api.channel.IChannelSubmerchService;
import com.alycloud.pay.gateway.api.channel.IFastChannelInfo;
import com.alycloud.pay.gateway.api.enums.SysChannelType;
import com.alycloud.pay.gateway.channel.util.HttpsClientUtil;
import com.alycloud.pay.gateway.channel.util.SignUtil;
import com.alycloud.pay.gateway.condition.QuickRequestCondition;
import com.alycloud.pay.gateway.condition.RefundOrderCondition;
import com.alycloud.pay.gateway.dto.MerchDTO;
import com.alycloud.pay.gateway.dto.OrderQueryDTO;
import com.alycloud.pay.gateway.dto.QrcodeOrderDTO;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.factory.FastChannelInfoFactory;
import com.alycloud.pay.gateway.inter.QrcodeIC;
import com.alycloud.pay.gateway.mapper.AgentMapper;
import com.alycloud.pay.gateway.mapper.BranchMapper;
import com.alycloud.pay.gateway.mapper.MerchMapper;
import com.alycloud.pay.gateway.mapper.channel.YufuChannelMerchMapper;
import com.alycloud.pay.gateway.models.BranchBean;
import com.alycloud.pay.gateway.models.ResponseBean;
import com.alycloud.pay.gateway.models.agent.AgentBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeChannelBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeRouteBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeTrans;
import com.alycloud.pay.gateway.models.quick.FastOrder;
import com.alycloud.pay.gateway.response.QrcodeActiviePayRespBean;
import com.alycloud.pay.gateway.response.QrcodeQueryResponseBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;
/**
 * restful接口
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月21日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/pay/cloudplatform/api")
@Api(value = "商户对外接口")
@Slf4j
public class MerchProviderController
{
    @Autowired
    private IMerchProviderService merchProviderService;
    
    @Autowired
    private IQrcodeOrderService qrcodeOrderService;
    
    @Autowired
    private IQrcodePassivePayService passiveService;
    
    @Autowired
    private IQrcodeChannelService channelService;
    
    @Autowired
    private MerchMapper merchMapper;
    
    @Autowired
    private BranchMapper branchMapper;
    
    @Autowired
    private AgentMapper agentMapper;
    
    @Autowired
    private IQrcodeMerchService qrcodeMerchService;
    
    @Autowired
    private IQrcodeSuccessService qrcodeSuccessService;
    
    @Autowired
    private IQrcodeQueryService qrcodeQueryService; // 二维码订单查询
    
    @Autowired
    private IQrcodeTransService qrcodeTransService; // 二维码交易查询
    
    @Autowired
    private IFastTransService fastTransService;
    
    @Autowired
    private IChannelSubmerchService channelSubmerchService;
    
    @Autowired
    private YufuChannelMerchMapper yufuChannelMerchMapper;
    
    @Value("${platformKey}")
    private String key;
    
    @Value("${hxtc.backNotifyUrl}")
    private String backNotifyUrl;
    
    /**
     * 快捷支付订单
     */
    @Autowired
    private IFastOrderService fastOrderService;
    
    @ApiOperation(notes = "调用 /register方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "商戶进件")
    @PostMapping(value = "/register")
    @SystemControllerLog(description = "商戶进件")
    public ResponseBean register(@Validated @RequestBody MerchDTO merchDTO)
    {
        //String key = "F868E97F0C09D48C71767BAA5F623A3C";//测试环境
//        String key = "EC6028C9215F67A19AEB4964917A8B6D";//生产环境
        try
        {
            Map<String, String> param = ReflectUtils.convertToMaps(merchDTO);
            if(!SignUtil.validSign(param, key))
            {
                return new ResponseBean("0", "签名不正确");
            }
            
            log.info("申请注册的商户信息 >>>> " + merchDTO);
            return merchProviderService.merchRegister(merchDTO,key);
            // return merchProviderService.merchRegister(merchBean);
        }
        catch (Exception e)
        {
            log.error("自动注册失败", e);
            return new ResponseBean("0", e.getMessage());
        }
    }
    
    @ApiOperation(notes = "调用 /qrcodePassivePay方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "扫码支付接口")
    @PostMapping(value = "/qrcodePassivePay")
    @SystemControllerLog(description = "扫码支付")
    public ResponseBean qrcodePassivePay(@RequestBody QrcodeOrderDTO qrcodeOrderDTO)
    {
        
        
        QrcodeOrderBean order = null;
        try
        {
        	MerchInfo merch = merchMapper.getByMerchno(qrcodeOrderDTO.getMerchno());
            log.info(merch.toString());
            
            Map<String, String> param = ReflectUtils.convertToMaps(qrcodeOrderDTO);
            if(!SignUtil.validSign(param, merch.getMerchKey()))
            {
                return new ResponseBean("0", "签名不正确");
            }
            
            /** 交易初始化(创建订单信息) **/
            String orderno = qrcodeOrderService.genRefno();
            log.info("产生当前交易的系统订单号 >>>> " + orderno);
            // int payType = Integer.parseInt(qrcodeOrderDTO.get("payType"));
            log.info("获取被扫类型的支付方式[" + qrcodeOrderDTO.getPayType() + "]");
            
           
            
            order = qrcodeOrderService.buildByParam(qrcodeOrderDTO, orderno, QrcodeIC.PAY_TYPE_PASSIVE, merch);
            log.info("验证商户[" + order.getMerchno() + "]的交易流水号[" + order.getTraceno() + "]是否重复");
            passiveService.validTraceno(order.getMerchno(), order.getTraceno());
            qrcodeOrderService.insert(order);
            
            /** 验证商户基本信息 **/
            log.info("根据商户号[" + order.getMerchno() + "]查询商户信息");
            
            log.info("根据机构号[" + merch.getBranchno() + "]查询机构信息");
            BranchBean branch = branchMapper.getByBranchno(merch.getBranchno());
            log.info(branch.toString());
            log.info("根据代理商号[" + merch.getFirstAgentno() + "]查询代理商信息");
            AgentBean agent = agentMapper.getByAgentno(merch.getFirstAgentno());
            log.info(agent.toString());
            qrcodeOrderService.setMerchInfo(order, branch, agent, merch);
            qrcodeOrderService.updateMerchInfo(order);
            
            /** 验证交易(风控)信息 **/
            log.info("验证交易的签名是否准确");
            passiveService.validSignature(qrcodeOrderDTO, merch.getMerchKey());
            // BigDecimal amount = new BigDecimal(param.get("amount"));
            // 以元为单位
            log.info("验证交易的风控信息,交易金额[" + qrcodeOrderDTO.getAmount() + "],支付方式[" + qrcodeOrderDTO.getPayType()
                + "],结算类型[" + order.getSettleType() + "]");
            // passiveService.validRiskInfo(merch, qrcodeOrderDTO.getNotifyUrl(), null, qrcodeOrderDTO.getAmount(),
            // qrcodeOrderDTO.getPayType(), order.getSettleType());
            
            /** 查找交易路由 **/
            QrcodeRouteBean route = passiveService.qrcodeRoute(order);
            log.info("根据渠道编码[" + route.getChannelCode() + "]获取渠道信息");
            QrcodeChannelBean channel = channelService.getByChannelCode(route.getChannelCode());
            channel.setChannel(qrcodeOrderDTO.getChannel());
            log.info(channel.toString());
            log.info("根据渠道编码[" + route.getChannelCode() + "]和渠道商户号[" + route.getMerchno() + "]获取渠道商户信息");
            QrcodeMerchBean qrcodeMerch = qrcodeMerchService.getByMerchno(route.getChannelCode(), route.getMerchno());
            log.info(qrcodeMerch.toString());
            qrcodeOrderService.setChannelInfo(order, qrcodeMerch, null);
            qrcodeOrderService.updateChannelInfo(order);
            
            // 获取商品名称
            String goodsName =
                (qrcodeOrderDTO.getGoodsName() == null || qrcodeOrderDTO.getGoodsName().equals("")) ? merch.getMerchName()
                    : qrcodeOrderDTO.getGoodsName();
            
            qrcodeMerch.setHfbankT1MerchNo(merch.getHfbankt1merchno());
            qrcodeMerch.setHfbankD0MerchNo(merch.getHfbankd0merchno());
            /** 执行二维码被扫交易 **/
            Map<String, String> result = passiveService.passivePay(order, channel, qrcodeMerch, goodsName, merch);
            String respCode = result.get("respCode");
            // 根据不同渠道类型做处理
            String channelType = qrcodeOrderDTO.getChannel();
            if ("1".equals(respCode))
            {
                String barCode = result.get("barCode");
                
                if (channelType.equals("wxPubQR") || channelType.equals("alipayQR") || channelType.equals("jdQR"))
                {
                    // 展示二维码，请商户调用第三方库将code_url生成二维码图片
                    // bean.setBarCode(result.get("barCode"));
                }
                else if (channelType.equals("wxApp"))
                {
                    // 请商户调用sdk控件发起支付
                    barCode = result.get("payCode");
                }
                else if (channelType.equals("wxMicro") || channelType.equals("alipayMicro")
                    || channelType.equals("jdMicro"))
                {
                    // 支付成功后的处理，更新订单状态等
                }
                else
                {
                    barCode = result.get("payCode");
                }
                qrcodeOrderService.setChannelInfo(order, qrcodeMerch, barCode);
                qrcodeOrderService.updateChannelInfo(order);
                qrcodeOrderService.updateStatusInfo(orderno, QrcodeIC.ORDER_STATUS_NOT_PAY, "下单成功");
            }
            else
            {
                qrcodeOrderService.updateStatusInfo(orderno, QrcodeIC.ORDER_STATUS_FAILURE, result.get("message"));
            }
            return passiveService.buildResponseBean(qrcodeOrderDTO, order, merch.getMerchKey(),result);
        }
        catch (YzyueException e)
        {
            log.info("交易失败", e);
            qrcodeOrderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_FAILURE, e.getMsg());
            return new QrcodeActiviePayRespBean(order.getMerchno(), order.getTraceno(), e.getCode(), "交易失败,"
                + e.getMsg());
        }
        catch (Exception e)
        {
            log.info("交易出现异常", e);
            qrcodeOrderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_FAILURE, e.getMessage());
            return new QrcodeActiviePayRespBean(order.getMerchno(), order.getTraceno(), "06", "系统异常," + e.getMessage());
        }
    }
    
    @ApiOperation(notes = "调用 qrcodeOrders方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "二维码订单查询")
    @PostMapping(value = "/qrcode/orders")
    @SystemControllerLog(description = "扫码支付订单查询")
    public ResponseBean qrcodeOrders(@RequestBody OrderQueryDTO orderQueryDTO)
    {
        
        
        QrcodeOrderBean order = null;
        try
        {
        	MerchInfo merch = merchMapper.getByMerchno(order.getMerchno());
            log.info(merch.toString());
            
            Map<String, String> param = ReflectUtils.convertToMaps(orderQueryDTO);
            if(!SignUtil.validSign(param, merch.getMerchKey()))
            {
                return new ResponseBean("0", "签名不正确");
            }
            
            /** 查询交易信息 **/
            order = qrcodeOrderService.queryOrder(orderQueryDTO.getMerchno(), orderQueryDTO.getTraceno());
            
            /** 验证商户基本信息 **/
            log.info("根据商户号[" + order.getMerchno() + "]查询商户信息");
           
            log.info("根据机构号[" + merch.getBranchno() + "]查询机构信息");
            BranchBean branch = branchMapper.getByBranchno(merch.getBranchno());
            log.info(branch.toString());
            log.info("根据代理商号[" + merch.getFirstAgentno() + "]查询代理商信息");
            AgentBean agent = agentMapper.getByAgentno(merch.getFirstAgentno());
            log.info(agent.toString());
            
            /** 验证交易(风控)信息 **/
            log.info("验证交易的签名是否准确");
            // queryService.validSignature(param, merch.getMerchKey());
            QrcodeTrans trans = qrcodeTransService.getByOrderno(order.getOrderno());
            
            if (trans == null)
            {
                // 当天交易表查询不到,则查询历史交易表
                trans = qrcodeTransService.getOnHisotry(order.getOrderno());
            }
            if (order.getStatus() == QrcodeIC.ORDER_STATUS_SUCCESS && trans != null)
            {
                /** 本地已经保存了成功的记录 **/
                Map<String, String> result = new HashMap<String, String>();
                result.put("channelOrderno", trans.getChannelOrderno());
                result.put("payStatus", "2");
                return qrcodeQueryService.buildResponseBean(order, result,merch.getMerchKey());
            }
            else
            {
                /** 查找交易路由 **/
                log.info("根据渠道编码[" + order.getChannelCode() + "]获取渠道信息");
                QrcodeChannelBean channel = channelService.getByChannelCode(order.getChannelCode());
                log.info(channel.toString());
                log.info("根据渠道编码[" + order.getChannelCode() + "]和渠道商户号[" + order.getChannelMerchno() + "]获取渠道商户信息");
                QrcodeMerchBean qrcodeMerch =
                    qrcodeMerchService.getByMerchno(order.getChannelCode(), order.getChannelMerchno());
                log.info(qrcodeMerch.toString());
                if (qrcodeMerch == null)
                    qrcodeMerch = new QrcodeMerchBean();
                qrcodeMerch.setHfbankT1MerchNo(merch.getHfbankt1merchno());
                qrcodeMerch.setHfbankD0MerchNo(merch.getHfbankd0merchno());
                qrcodeMerch.setQueryType(orderQueryDTO.getQueryType());
                
                /** 执行二维码查询交易 **/
                Map<String, String> result = qrcodeQueryService.query(order, channel, qrcodeMerch);
                String respCode = result.get("respCode");
                if ("1".equals(respCode))
                {
                    trans =
                        qrcodeSuccessService.buildQrcodeTrans(order,
                            result.get("channelOrderno"),
                            result.get("channelTraceno"));
                    qrcodeSuccessService.process(order, trans, "客户端二维码查询,系统自动充值");
                    qrcodeOrderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_SUCCESS, "支付成功");
                    String bankCardType = result.get("bankCardType");
                    if (null == bankCardType)
                    {
                        bankCardType = "";
                    }
                    result.put("payStatus", "2");
                    qrcodeOrderService.updateBankCardType(order.getOrderno(), bankCardType);
                }
                else if ("0".equals(respCode))
                {
                    result.put("payStatus", "0");
                    qrcodeOrderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_NOT_PAY, "尚未支付");
                }
                else
                {
                    result.put("payStatus", "3");
                    qrcodeOrderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_FAILURE, "支付失败");
                }
                return qrcodeQueryService.buildResponseBean(order, result,merch.getMerchKey());
            }
        }
        catch (YzyueException e)
        {
            log.error("二维码订单查询交易失败", e);
            return new QrcodeActiviePayRespBean(orderQueryDTO.getMerchno(), orderQueryDTO.getTraceno(), e.getCode(),
                "订单查询失败," + e.getMsg());
        }
        catch (Exception e)
        {
            log.error("二维码交易查询出现异常", e);
            return new QrcodeActiviePayRespBean(orderQueryDTO.getMerchno(), orderQueryDTO.getTraceno(), "06", "系统异常,"
                + e.getMessage());
        }
    }
    
    @ApiOperation(notes = "调用 refundOrderApply方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "退款申请接口")
    @PostMapping(value = "/orders/refundOrderApply/")
    @SystemControllerLog(description = "退款申请")
    public ResponseBean refundOrderApply(@RequestBody RefundOrderCondition condition)
    {
        
        QrcodeOrderBean order = null;
        try
        {
        	MerchInfo merch = merchMapper.getByMerchno(order.getMerchno());
            log.info(merch.toString());
            
            Map<String, String> param = ReflectUtils.convertToMaps( condition);
            if(!SignUtil.validSign(param, merch.getMerchKey()))
            {
                return new ResponseBean("0", "签名不正确");
            }
            
            /** 查询交易信息 **/
            order = qrcodeOrderService.queryOrder(condition.getMerchno(), condition.getTraceno());
            
            /** 验证商户基本信息 **/
            log.info("根据商户号[" + order.getMerchno() + "]查询商户信息");
            
            log.info("根据机构号[" + merch.getBranchno() + "]查询机构信息");
            BranchBean branch = branchMapper.getByBranchno(merch.getBranchno());
            log.info(branch.toString());
            log.info("根据代理商号[" + merch.getFirstAgentno() + "]查询代理商信息");
            AgentBean agent = agentMapper.getByAgentno(merch.getFirstAgentno());
            log.info(agent.toString());
            
            /** 验证交易(风控)信息 **/
            log.info("验证交易的签名是否准确");
            // queryService.validSignature(param, merch.getMerchKey());
            QrcodeTrans trans = qrcodeTransService.getByOrderno(order.getOrderno());
            if (trans == null)
            {
                // 当天交易表查询不到,则查询历史交易表
                trans = qrcodeTransService.getOnHisotry(order.getOrderno());
            }
            if (order.getStatus() == QrcodeIC.ORDER_STATUS_SUCCESS && trans != null)
            {
                /** 本地已经保存了成功的记录 **/
                // Map<String, String> result = new HashMap<String, String>();
                // result.put("channelOrderno", trans.getChannelOrderno());
                /** 查找交易路由 **/
                log.info("根据渠道编码[" + order.getChannelCode() + "]获取渠道信息");
                QrcodeChannelBean channel = channelService.getByChannelCode(order.getChannelCode());
                log.info(channel.toString());
                channel.setChannel(condition.getChannel());
                
                log.info("根据渠道编码[" + order.getChannelCode() + "]和渠道商户号[" + order.getChannelMerchno() + "]获取渠道商户信息");
                QrcodeMerchBean qrcodeMerch =
                    qrcodeMerchService.getByMerchno(order.getChannelCode(), order.getChannelMerchno());
                log.info(qrcodeMerch.toString());
                if (qrcodeMerch == null)
                    qrcodeMerch = new QrcodeMerchBean();
                qrcodeMerch.setHfbankT1MerchNo(merch.getHfbankt1merchno());
                qrcodeMerch.setHfbankD0MerchNo(merch.getHfbankd0merchno());
                
                passiveService.validRefundTraceno(order.getMerchno(), order.getTraceno());
                qrcodeOrderService.insertRefundOrder(order);
                
                /** 执行退款交易 **/
                Map<String, String> result = qrcodeQueryService.refundOrder(order, channel, qrcodeMerch);
                String respCode = result.get("respCode");
                if ("1".equals(respCode))
                {
                    // trans =
                    // qrcodeSuccessService.buildQrcodeTrans(order,
                    // result.get("channelRefundNo"),
                    // null);
                    // qrcodeSuccessService.process(order, trans, "客户端二维码查询,系统自动充值");
                    qrcodeOrderService.updateRefundOrderStatusInfo(order.getOrderno(),
                        result.get("channelRefundNo"),
                        QrcodeIC.REFOUND_STATUS_SUCCESS,
                        "退款成功");
                }
                return buildRefundResponseBean(order, result,merch.getMerchKey());
            }
            else
            {
                QrcodeQueryResponseBean bean = new QrcodeQueryResponseBean();
                bean.setRespCode("0");
                bean.setMessage("请确认订单流水是否存在,退款失败。");
                bean.setTraceno(condition.getTraceno());
                return bean;
            }
        }
        catch (YzyueException e)
        {
            log.error("退款交易失败", e);
            return new QrcodeActiviePayRespBean(condition.getMerchno(), condition.getTraceno(), e.getCode(), "退款失败,"
                + e.getMsg());
        }
        catch (Exception e)
        {
            log.error("退款交易出现异常", e);
            return new QrcodeActiviePayRespBean(condition.getMerchno(), condition.getTraceno(), "06", "系统异常,"
                + e.getMessage());
        }
    }
    
    public QrcodeQueryResponseBean buildRefundResponseBean(QrcodeOrderBean order, Map<String, String> result, String merchKey)
    {
        String orderno = order.getOrderno();
        QrcodeQueryResponseBean bean = new QrcodeQueryResponseBean();
        bean.setRespCode(result.get("respCode"));
        bean.setMessage(result.get("message"));
        bean.setTraceno(order.getTraceno());
        bean.setPayType(Integer.toString(order.getPayType()));
        bean.setScanType(Integer.toString(order.getScanType()));
        bean.setOrderno(orderno);
        String sign = SignUtil.genSign(merchKey, SignUtil.createLinkString(SignUtil.paraFilter(ReflectUtils.convertToMaps(bean))));
        bean.setSign(sign);
        // bean.setChannelOrderno(result.get("channelOrderno"));
        return bean;
    }
    
    @ApiOperation(notes = "调用 quickPay方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "快捷支付接口")
    @PostMapping(value = "/quickPay/")
    @SystemControllerLog(description = "快捷支付订单")
    public ResponseBean quickPay(@RequestBody QuickRequestCondition condition)
    {
        
        try
        {
        	MerchInfo merch = merchMapper.getByMerchno(condition.getMerchno());
            log.info(merch.toString());
            
            Map<String, String> param = ReflectUtils.convertToMaps( condition);
            if(!SignUtil.validSign(param, merch.getMerchKey()))
            {
                return new ResponseBean("0", "签名不正确");
            }
            
            if (condition.getAmount().compareTo(new BigDecimal(100)) == -1)
            {
                throw new Exception("支付金额不能少于100元");
            }
//            final MerchBean merch = merchMapper.getByMerchno(condition.getMerchno());
            // 创建支付订单
            IFastChannelInfo channelInfo = FastChannelInfoFactory.getChannel(SysChannelType.HXTC);
            ChannelSubmerchInfoBean hxtcChannelSubmerchInfo = channelSubmerchService.searchBySubmerchInfo(merch.getMerchno(), null, SysChannelType.HXTC.getCode(), "QUICKPAY", "QUICKPAY");
            FastOrder fastOrder = fastOrderService.buildFastOrder(merch, channelInfo, hxtcChannelSubmerchInfo,condition);
            
            String bankType = merchMapper.getBankByCardno(condition.getCardno());
            Map<String, String> payRequestParams =
                HxtcUtils.responsePayBuilder(merch,
                    condition.getAmount(),
                    condition.getCardno(),
                    fastOrder.getOrderno(),
                    hxtcChannelSubmerchInfo,
                    bankType,
                    backNotifyUrl, condition.getCallbackUrl());
            IQuickpayService quickpayService =   null;
            //YufuChannelMerchBean merchBean = yufuChannelMerchMapper.searchByKey(fastOrder.getMerchno(), fastOrder.getMobile(), MerchState.PASS.ordinal());
            YufuChannelMerchBean merchBean = yufuChannelMerchMapper.searchByKey(fastOrder.getMerchno(), fastOrder.getMobile(), null, null);
            
            if(condition.getAmount().compareTo(new BigDecimal(8000)) == -1 || merchBean == null)
            {
                quickpayService =   QuickpayFactory.getQuickpayService(SysChannelType.HXTC.getCode()); 
                Map<String, String>  result = quickpayService.pay(payRequestParams, fastOrder.getOrderno());
                if (result != null)
                {
                    fastOrder.setChannelOrderno(result.get("channelOrderno"));
                }
                fastOrderService.insert(fastOrder);
                if ("0".equals(result.get("responseCode")))
                {
                    QrcodeQueryResponseBean bean = new QrcodeQueryResponseBean();
                    bean.setRespCode("1");
                    bean.setMessage(result.get("respMsg"));
                    bean.setTraceno(fastOrder.getOrderno());
                    bean.setOutTraceno(condition.getOutTraceno());
                    bean.setMapping(result.get("pageContent"));
                    String sign = SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(ReflectUtils.convertToMaps(bean))));
                    bean.setSign(sign);
                    return bean;
                }
                else
                {
                    QrcodeQueryResponseBean bean = new QrcodeQueryResponseBean();
                    bean.setRespCode("0");
                    bean.setMessage(result.get("respMsg"));
                    bean.setTraceno(condition.getOutTraceno());
                    return bean;
                }
            }else{
                if(merchBean.getOneCodeUrl() == null || merchBean.getOneCodeUrl().equals(""))
                {
                    //从上游查询商户是否存在
                  quickpayService = QuickpayFactory.getQuickpayService(SysChannelType.YUFU.getCode());
                  YufuMerchQueryBean queryData = new YufuMerchQueryBean();
                  queryData.setPhone(merch.getMobile());
                  queryData.setSerialNo("E" + new Date().getTime());
                  YufuMerchQueryResultBean queryResultBean = quickpayService.queryChannelSubmerch(queryData);
                  if(queryResultBean.getState() == MerchState.NEW)
                  {
                      quickpayService =   QuickpayFactory.getQuickpayService(SysChannelType.HXTC.getCode()); 
                      Map<String, String>  result = quickpayService.pay(payRequestParams, fastOrder.getOrderno());
                      if (result != null)
                      {
                          fastOrder.setChannelOrderno(result.get("channelOrderno"));
                      }
                      fastOrderService.insert(fastOrder);
                      if ("0".equals(result.get("responseCode")))
                      {
                          QrcodeQueryResponseBean bean = new QrcodeQueryResponseBean();
                          bean.setRespCode("1");
                          bean.setMessage(result.get("respMsg"));
                          bean.setTraceno(fastOrder.getOrderno());
                          bean.setOutTraceno(condition.getOutTraceno());
                          bean.setMapping(result.get("pageContent"));
                          String sign = SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(ReflectUtils.convertToMaps(bean))));
                          bean.setSign(sign);
                          return bean;
                      }
                      else
                      {
                          QrcodeQueryResponseBean bean = new QrcodeQueryResponseBean();
                          bean.setRespCode("0");
                          bean.setMessage(result.get("respMsg"));
                          bean.setTraceno(condition.getOutTraceno());
                          return bean;
                      }
                  }
                  merchBean.setKjKey(queryResultBean.getKjKey());
                  merchBean.setMerchId(queryResultBean.getMerchId());
                  merchBean.setOneCodeUrl(queryResultBean.getOneCodeUrl());
                  merchBean.setRemark(queryResultBean.getRemark());
                  merchBean.setState(queryResultBean.getState().ordinal());
                  merchBean.setTermId(queryResultBean.getTermId());
                  yufuChannelMerchMapper.upYufuChannelMerch(merchBean);
                }
                ChannelSubmerchInfoBean submerchInfoBean = channelSubmerchService.searchBySubmerchInfo(merch.getMerchno(), null, SysChannelType.YUFU.getCode(), "QUICKPAY", "QUICKPAY");
                if(submerchInfoBean == null)
                {
                    submerchInfoBean = new ChannelSubmerchInfoBean();
                    submerchInfoBean.setBranchno(merch.getBranchno());
                    submerchInfoBean.setAgentno(merch.getSuperAgent());
                    submerchInfoBean.setMerchno(merch.getMerchno());
                    submerchInfoBean.setChannelCode(SysChannelType.YUFU.getCode());
                    submerchInfoBean.setChannelName("御付快捷通道");
                    submerchInfoBean.setD0payRate(hxtcChannelSubmerchInfo.getD0payRate());
                    submerchInfoBean.setT1payRate(hxtcChannelSubmerchInfo.getT1payRate());
                    submerchInfoBean.setPayFeeD0(hxtcChannelSubmerchInfo.getPayFeeD0());
                    submerchInfoBean.setPayFeeT1(hxtcChannelSubmerchInfo.getPayFeeT1());
                    submerchInfoBean.setPayType("QUICKPAY");
                    submerchInfoBean.setPayMethod("QUICKPAY");
                    submerchInfoBean.setAduitStatus("1");
                    submerchInfoBean.setRemark(JSONUtils.obj2json(merchBean));
                    submerchInfoBean.setChannelSubmerchno(merchBean.getMerchId());
                    channelSubmerchService.insert(submerchInfoBean);
                }
                if(StringUtils.isEmpty(submerchInfoBean.getChannelSubmerchno()))
                {
                    submerchInfoBean.setAduitStatus("1");
                    submerchInfoBean.setRemark(JSONUtils.obj2json(merchBean));
                    submerchInfoBean.setChannelSubmerchno(merchBean.getMerchId());
                    channelSubmerchService.update(submerchInfoBean);
                }
                
                quickpayService =   QuickpayFactory.getQuickpayService(SysChannelType.YUFU.getCode());
                String token  = merchBean.getOneCodeUrl().split("token\\=")[1];// 一码付地址中的token信息
                
                YufuPayBean payBean = new YufuPayBean();
                payBean.setAmount(fastOrder.getAmount().multiply(new BigDecimal("100")).setScale(0).toPlainString());// 元转分
                payBean.setMerchOrderNo(fastOrder.getOrderno());
                payBean.setPayType(YufuPayType.FAST.ordinal());
                payBean.setRemark("esytoAgent");
                payBean.setToken(token);
                YufuPayResultBean yufuResultBean =  quickpayService.pay(payBean, fastOrder.getOrderno());
                fastOrder.setChannelType(SysChannelType.YUFU.ordinal());
                fastOrderService.insert(fastOrder);
                if(yufuResultBean.getRespCode().equals("0000"))
                {
                    QrcodeQueryResponseBean bean = new QrcodeQueryResponseBean();
                    bean.setRespCode("1");
                    bean.setMessage("");
                    bean.setTraceno(fastOrder.getOrderno());
                    bean.setOutTraceno(condition.getOutTraceno());
//                    String mapping = "<script language='javascript' type='text/javascript'>　   "
//                                     + " window.location = '"+yufuResultBean.getPayUrl()+"';"
//                                     + "</script>";
                    String mapping = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>"
                        + "<meta http-equiv='refresh' content='0; URL="+yufuResultBean.getPayUrl()+"'>"
                        + "</head>"
                        + "</html>";
                    bean.setMapping(mapping);
                    String sign = SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(ReflectUtils.convertToMaps(bean))));
                    bean.setSign(sign);
                    return bean;
                }
            }
            
            QrcodeQueryResponseBean bean = new QrcodeQueryResponseBean();
            bean.setRespCode("0");
            bean.setMessage("支付订单生成失败");
            bean.setTraceno(condition.getOutTraceno());
            return bean;
            
            
            
        }
        catch (YzyueException e)
        {
            log.error("快捷交易失败", e);
            return new QrcodeActiviePayRespBean(condition.getMerchno(), condition.getOutTraceno(), e.getCode(), "交易失败,"
                + e.getMsg());
        }
        catch (Exception e)
        {
            log.error("快捷交易出现异常", e);
            return new QrcodeActiviePayRespBean(condition.getMerchno(), condition.getOutTraceno(), "0", "交易失败,"
                + e.getMessage());
        }
    }
    
    @ApiIgnore
    @PostMapping(value = "/quickPay/notify/{channelCode}")
    @SystemControllerLog(description = "快捷支付通知")
    public void quickPayNotify(@RequestBody Map<String, String> params,@PathVariable String channelCode)
    {
        String postStr = "{}";
        
        try
        {
            String client_trans_id = params.get("client_trans_id");
            String resp_code = params.get("resp_code");
            String toSign = "client_trans_id="+client_trans_id+"|resp_code="+resp_code+"";
                
            MypaysRsaDataEncryptUtil.rsaDataEncryptPub.verify(toSign.getBytes("UTF-8"), params.get("sign"));  
            
            FastOrder fastOrder = fastOrderService.searchByOrderno(client_trans_id);
            if(fastOrder == null)
            {
                log.error("快捷支付订单查询为空，{}, {}", params, channelCode);
                return; 
            }
            Map<String, String> postMap = new HashMap<String, String>();
            MerchInfo merchBean = merchMapper.getByMerchno(fastOrder.getMerchno());
            postMap.put("respCode", "1");
            if ("PAY_SUBMIT".equals(resp_code))
            {
                postMap.put("message", "未支付");
                postMap.put("status", "01");
            }else if ("PAY_SUCCESS".equals(resp_code))
            {
                postMap.put("message", "支付成功");
                postMap.put("status", "02");
            }
            else if("PAY_FAILURE".equals(resp_code))
            {
                postMap.put("message", "支付失败");
                postMap.put("status", "09");
                
            }else
            {
                postMap.put("message", "支付未知");
                postMap.put("status", "03");
                
            }
            postMap.put("outTradeNo", fastOrder.getOutTraceno());
            postMap.put("outChannelNo", fastOrder.getOrderno());
            String sign = SignUtil.genSign(merchBean.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(postMap)));
            postMap.put("sign", sign);
            postStr = JSONUtils.obj2json(postMap);
            HttpsClientUtil.sendRequest(fastOrder.getNotifyUrl(), postStr, "application/json");
        }
        catch (YzyueException e)
        {
            log.error("快捷交易通知失败", e);
        }
        catch (Exception e)
        {
            log.error("快捷交易通知出现异常", e);
        }
    }
    
    
    @ApiOperation(notes = "调用 quickPayQuery方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "快捷支付订单查询接口")
    @PostMapping(value = "/quickPay/orders/query")
    @SystemControllerLog(description = "快捷支付订单查询接口")
    public ResponseBean quickPayQuery(@RequestBody OrderQueryDTO orderQueryDTO)
    {
        FastOrder order = null;
        QrcodeQueryResponseBean bean = null;
        try
        {
        	MerchInfo merch = merchMapper.getByMerchno(orderQueryDTO.getMerchno());
            
            Map<String, String> param = ReflectUtils.convertToMaps(orderQueryDTO);
            if(!SignUtil.validSign(param, merch.getMerchKey()))
            {
                return new ResponseBean("0", "签名不正确");
            }
            
            /** 查询交易信息 **/
            order = fastOrderService.queryOrder(orderQueryDTO.getMerchno(), orderQueryDTO.getTraceno());
            if(order == null)
            {
                return new ResponseBean("0", "该流水号订单不存在");  
            }
            
            log.info(merch.toString());
            log.info("根据机构号[" + merch.getBranchno() + "]查询机构信息");
            BranchBean branch = branchMapper.getByBranchno(merch.getBranchno());
            log.info(branch.toString());
            log.info("根据代理商号[" + merch.getFirstAgentno() + "]查询代理商信息");
            AgentBean agent = agentMapper.getByAgentno(merch.getFirstAgentno());
            log.info(agent.toString());
            
            /** 验证交易(风控)信息 **/
            log.info("验证交易的签名是否准确");
            // queryService.validSignature(param, merch.getMerchKey());
            FastTransBean trans = fastTransService.searchByOrderno(order.getOrderno());
            
            if (order.getStatus() == QrcodeIC.ORDER_STATUS_SUCCESS && trans != null)
            {
                /** 本地已经保存了成功的记录 **/
                String orderno = order.getOrderno();
                bean = new QrcodeQueryResponseBean();
                bean.setRespCode("1");
                bean.setPayStatus("2");
                bean.setMessage("支付成功");
                bean.setTraceno(orderno);
                bean.setChannelOrderno(order.getChannelOrderno());
                String sign = SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(ReflectUtils.convertToMaps(bean))));
                bean.setSign(sign);
                return bean;
            }
            else
            {
                bean = new QrcodeQueryResponseBean();
                
                /** 执行二维码查询交易 **/
                Map<String, String> result =
                    HxtcUtils.quickPayOrderQuery(order.getOrderno());
                String respCode = result.get("resp_code");
                if ("PAY_SUBMIT".equals(respCode))
                {
                    bean.setPayStatus("1");
                    bean.setMessage("尚未支付");
                  fastOrderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_NOT_PAY, "尚未支付");
                }else if ("PAY_SUCCESS".equals(respCode))
                {
                      fastTransService.insert(order);
                  fastOrderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_SUCCESS, "支付成功");
                  bean.setPayStatus("2");
                  bean.setMessage("支付成功");
                }
                else if("PAY_FAILURE".equals(respCode))
                {
                    bean.setPayStatus("3");
                    bean.setMessage("支付失败");
                  fastOrderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_FAILURE, "支付失败");
                    
                }
                String orderno = order.getOrderno();
                
                bean.setRespCode("1");
                
                bean.setTraceno(orderno);
                bean.setChannelOrderno(result.get("channelOrderno"));
                String sign = SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(ReflectUtils.convertToMaps(bean))));
                bean.setSign(sign);
                return bean;
            }
        }
//        catch (YzyueException e)
//        {
//            log.error("快捷支付订单查询交易失败", e);
//            return new QrcodeActiviePayRespBean(orderQueryDTO.getMerchno(), orderQueryDTO.getTraceno(), e.getCode(),
//                "快捷支付订单查询失败," + e.getMsg());
//        }
        catch (Exception e)
        {
            log.error("二维码交易查询出现异常", e);
            return new QrcodeActiviePayRespBean(orderQueryDTO.getMerchno(), orderQueryDTO.getTraceno(), "06", "系统异常,"
                + e.getMessage());
        }
    }
}
