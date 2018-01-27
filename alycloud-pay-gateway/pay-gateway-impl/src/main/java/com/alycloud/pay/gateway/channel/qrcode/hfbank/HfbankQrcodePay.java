/*
 * 类文件名:  HfbankQrcodePay.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.channel.qrcode.hfbank;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alycloud.pay.gateway.channel.util.CodeUtil;
import com.alycloud.pay.gateway.channel.util.SignUtil;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.models.qrcode.QrcodeChannelBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

/**
 * 恒丰银行二维码支付
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月27日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
public class HfbankQrcodePay extends AbstractHfbank
{
    // private MerchBean merch;
    
    
    /**
     * 支付交易调用的构造方法
     * 
     * @param channel
     * @param merch
     */
    public HfbankQrcodePay(QrcodeChannelBean channel, QrcodeMerchBean merch)
    {
        super(channel, merch);
    }
    
    /**
     * 支付交易调用的构造方法
     * 
     * @param channel
     * @param merch
     * @param merchName
     * @param payType
     */
    public HfbankQrcodePay(QrcodeChannelBean channel, QrcodeMerchBean merch, String merchName, int payType)
    {
        super(channel, merch, merchName, payType);
    }
    
    // public HfbankQrcodePay(QrcodeChannelBean channel, QrcodeMerchBean qrcodeMerch, String merchName, int payType,
    // MerchBean merch)
    // {
    // super(channel, qrcodeMerch, merchName, payType);
    // this.merch = merch;
    // }
    
    /**
     * 二维码被扫
     * 
     * @param settleType 结算类型(1-T+0结算 2-T+1结算)
     * @param orderno 订单号
     * @param amount 交易金额
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     * @throws Exception
     */
    public Map<String, String> passivePay(int settleType, String orderno, long amount)
        throws YzyueException, Exception
    {
        // 构造请求信息
        Map<String, String> param = buildPayRequest(orderno, amount, settleType);
        // 发送数据并从国付接收数据返回
        Map<String, String> returnMap = receiveBySend(param);
        
        // 解析返回串
        String key = channel.getKeyMd5();
        Map<String, String> result = new HashMap<String, String>();
        if(returnMap.get("amount") != null)
        {
            String amountResult = String.valueOf(returnMap.get("amount"));
            DecimalFormat formater = new DecimalFormat("###0.00");
            returnMap.put("amount",formater.format(new BigDecimal(amountResult)));
        }
        // 验签
        if (SignUtil.validSign(returnMap, key))
        {
            String returnCode = returnMap.get("returnCode");
            String resultCode = returnMap.get("resultCode");
            
            if (returnCode.equals("0") && resultCode.equals("0"))
            {
                result.put("respCode", "1");
                String codeUrl = "";
                
                // 根据不同渠道类型做处理
                String channelType = channel.getChannel();
                if (channelType.equals("wxPubQR") || channelType.equals("alipayQR") || channelType.equals("jdQR"))
                {
                    // 展示二维码，请商户调用第三方库将code_url生成二维码图片
                    codeUrl = returnMap.get("codeUrl");
                    result.put("barCode", CodeUtil.strDecode(codeUrl, "UTF-8"));
                }
                else if (channelType.equals("wxApp"))
                {
                    // 请商户调用sdk控件发起支付
                    result.put("payCode", returnMap.get("payCode"));
                }
                else if (channelType.equals("wxMicro") || channelType.equals("alipayMicro")
                    || channelType.equals("jdMicro"))
                {
                    // 支付成功后的处理，更新订单状态等
                }
                else
                {
                    String payCode = returnMap.get("payCode");
                    result.put("payCode", payCode);
                    // if(StringUtils.indexOf(payCode, "http") == 0){
                    // //request.setAttribute("payCode", returnMap.get("payCode"));
                    // return "redirect:" + returnMap.get("payCode");
                    // }else{
                    // result.put("payCode", payCode);
                    // }
                }
            }
        }
        else
        {
            result.put("respCode", "0");
            result.put("message", "支付失败, " + returnMap.get("errCodeDes"));
            result.put("errCodeDes", returnMap.get("errCodeDes"));
            result.put("returnMsg", returnMap.get("returnMsg"));
        }
        
        return result;
    }
    
    /**
     * 二维码交易查询
     * 
     * @param orderno 订单号
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     * @throws Exception
     */
    public Map<String, String> query(QrcodeOrderBean order, String transDate, String orderno)
        throws YzyueException, JsonParseException, JsonMappingException, IOException
    {
        // 解析返回串
        String key = channel.getKeyMd5();
        
        // 构造请求信息
        Map<String, String> param = buildQueryRequest(orderno, order.getSettleType());
        Map<String, String> returnMap = receiveBySend(param);
        // 解析返回串
        Map<String, String> result = new HashMap<String, String>();
        // 验签
        if (SignUtil.validSign(returnMap, key))
        {
            String returnCode = returnMap.get("returnCode");
            String resultCode = returnMap.get("resultCode");
            String status = returnMap.get("status");// 订单状态
            
            if (returnCode.equals("0") && resultCode.equals("0"))
            {
                result.put("respCode", "00");
                result.put("refno", orderno);
                if ("02".equalsIgnoreCase(status))
                {
                    result.put("respCode", "1");
                    result.put("message", "支付成功");
                    String channelOrderno = returnMap.get("outChannelNo");// 交易单号
                    result.put("backupOrderno", channelOrderno);
                    result.put("channelOrderno", channelOrderno);
                    
                    String bankCardType = returnMap.get("channel"); // 支付卡类型
                    result.put("bankCardType", bankCardType);
                }
                else if ("01".equalsIgnoreCase(status))
                {
                    result.put("respCode", "0");
                    result.put("message", "未支付");
                }
                else if ("04".equalsIgnoreCase(status))
                {
                    result.put("respCode", "2");
                    result.put("message", "已关闭");
                }
                else if ("10".equalsIgnoreCase(status))
                {
                    result.put("respCode", "2");
                    result.put("message", "订单超时");
                }
                else
                {
                    result.put("respCode", "2");
                    result.put("message", returnMap.get("returnMsg"));
                }
            }
        }
        else
        {
            result.put("respCode", "0");
            result.put("message", "支付未知");
        }
        
        return result;
    }
    
    /**
     * 执行退款交易
     * 
     * @param order
     * @param transDate
     * @param orderno
     * @return
     * @throws IOException 
     * @throws YzyueException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @Override
    public Map<String, String> refundOrder(QrcodeOrderBean order, String transDate, String orderno, long amount) throws JsonParseException, JsonMappingException, YzyueException, IOException
    {
     // 解析返回串
        String key = channel.getKeyMd5();
        
        // 构造请求信息
        Map<String, String> param = buildRefundOrderRequest(orderno, amount , order.getSettleType());
        Map<String, String> returnMap = receiveBySend(param);
        // 解析返回串
        Map<String, String> result = new HashMap<String, String>();
        // 验签
        if (SignUtil.validSign(returnMap, key))
        {
            String returnCode = returnMap.get("returnCode");
            String resultCode = returnMap.get("resultCode");
//            String status = returnMap.get("status");// 订单状态
            
            /**
             * {
                    "returnCode":"0",
                    "returnMsg":"退款申请中",
                    "resultCode":"0",
                    "sign":"8BC54648F98169DCBD2F661B1729B45A",
                    "outRefundNo":"R010102",
                    "outTradeNo":"010102",
                    "channelRefundNo":"00005000000000599201608220000008"
                }
             */
            if (returnCode.equals("0") && resultCode.equals("0"))
            {
                result.put("respCode", "1");
                result.put("channelRefundNo", returnMap.get("channelRefundNo"));
                result.put("message", "退款申请成功");
            }
        }
        else
        {
            result.put("respCode", "0");
            result.put("message", returnMap.get("errCodeDes"));
        }
        
        return result;
    }
    
    public Map<String, String> activePay(int settleType, String orderno, long amount, String authCode)
        throws YzyueException, Exception
    {
        return null;
    }
    
    public Map<String, String> officalPay(int settleType, String orderno, long amount)
        throws YzyueException, Exception
    {
        // 构造请求信息
        Map<String, String> param = buildPayRequest(orderno, amount, settleType);
        // 发送数据并从国付接收数据返回
        
        Map<String, String> returnMap = receiveBySend(param);
        
        // // 响应验签
        // JszdMsgUtils.signCheck(obj, merch.getKeyMd5());
        //
        // // 解析响应结果
        // String pl_code = JsonUtil.getJsonStr(obj, Constants.pl_code);
        // String pl_message = JsonUtil.getJsonStr(obj, Constants.pl_message);
        // Map<String, String> result = buildResponse(pl_code, pl_message);
        // if ("0000".equals(result.get("respCode")))
        // {
        // result.put("respCode", "00");
        //
        // String codeUrl = JsonUtil.getJsonStr(obj, Constants.pl_url);
        // result.put("barCode", CodeUtil.strDecode(codeUrl, "UTF-8"));
        // }
        
        return null;
    }
    
    public Map<String, String> wapPay(int settleType, String orderno, long amount)
        throws YzyueException, Exception
    {
        return null;
    }
    
    /**
     * 华付通商户注册
     * 
     * @param orderno 注册的流水号
     * @param payType 支付方式
     * @param merchno 平台商户号
     * @param address 商户地址
     * @param accountno 结算卡号
     * @param accountName 结算户名
     * @param mobile 手机号码
     * @param certno 身份证号码
     * @param bankno 联行号
     * @param bankName 银行名称
     * @param t1Rate T+1费率
     * @param t1WithDraw T+1清算费
     * @param t0Rate T+0费率
     * @param t0WithDraw T+0清算费
     * @return
     * @throws Exception
     */
    public Map<String, String> register(String orderno, int payType, String merchno, String merchName, String address,
        String accountno, String accountName, String mobile, String certno, String bankno, String bankName,
        BigDecimal t1Rate, BigDecimal t1WithDraw, BigDecimal t0Rate, BigDecimal t0WithDraw)
        throws Exception
    {
        Map<String, Object> body = new HashMap<String, Object>();
        
        // 设置商户基本信息
        Map<String, String> merch = new HashMap<String, String>();
        merch.put("merchantNo", merchno);// 平台商户编号
        merch.put("merchantName", merchName);// 商户名称
        merch.put("shortName", merchName);// 商户简称
        merch.put("address", address);// 商户地址
        merch.put("serPhone", mobile);// 客服电话
        merch.put("category", "2016051000165496");// 经营类目
        merch.put("idCard", certno);// 身份证号
        merch.put("name", accountName);// 法人姓名
        body.put("info", merch);
        
        // 添加商户账号信息
        Map<String, String> account = new HashMap<String, String>();
        account.put("bankCard", accountno);// 银行卡号
        account.put("bankCode", bankno);// 联行号
        account.put("bankName", bankName);// 开户行名称（到支行）
        account.put("accountName", accountName);// 持卡人姓名
        account.put("isRealAccount", "Y");// 是否T0商户Y:是，N:否（默认T1）
        account.put("accountType", "N");
        body.put("account", account);
        
        List<Map<String, String>> rate = new ArrayList<Map<String, String>>();
        
        // 微信公众号
        Map<String, String> wxmp = new HashMap<String, String>();
        wxmp.put("payCode", "WXGZHZF");// 支付通道代码
        wxmp.put("withdrawAmt", t0WithDraw.toString());// 提现服务费(元)
        wxmp.put("withdrawRate", t0Rate.subtract(t1Rate).toString());// 提现手续费率
        wxmp.put("tradeRate", t1Rate.toString());// TI清算手续费率
        wxmp.put("settleAmt", t1WithDraw.toString());
        rate.add(wxmp);
        
        // 支付宝扫码
        Map<String, String> zfb = new HashMap<String, String>();
        zfb.put("payCode", "ZFBSMZF");// 支付通道代码
        zfb.put("withdrawAmt", t0WithDraw.toString());// 提现服务费(元)
        zfb.put("withdrawRate", t0Rate.subtract(t1Rate).toString());// 提现手续费率
        zfb.put("tradeRate", t1Rate.toString());// TI清算手续费率
        zfb.put("settleAmt", t1WithDraw.toString());
        rate.add(zfb);
        
        // 微信扫码
        Map<String, String> wx = new HashMap<String, String>();
        wx.put("payCode", "WXSMZF");// 支付通道代码
        wx.put("withdrawAmt", t0WithDraw.toString());// 提现服务费(元)
        wx.put("withdrawRate", t0Rate.subtract(t1Rate).toString());// 提现手续费率
        wx.put("tradeRate", t1Rate.toString());// TI清算手续费率
        wx.put("settleAmt", t1WithDraw.toString());
        rate.add(wx);
        body.put("payType", rate);
        return request("merchant.join", body, orderno);
    }
}
