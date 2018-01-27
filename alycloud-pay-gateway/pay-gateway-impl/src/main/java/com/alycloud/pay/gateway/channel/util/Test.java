package com.alycloud.pay.gateway.channel.util;
///*
// * 类文件名:  Test.java
// * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
// * 功能描述:  <描述>
// * 类创建人:  曾云龙
// * 创建时间:  2017年7月19日
// * 功能版本:  V001Z0001
// */
//package com.cspay.demo.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import cn.yzyue.api.servlet.except.YzyueException;
//import cn.yzyue.qrcode.hfbank.util.HfbankPayChannelEnum;
//import cn.yzyue.qrcode.hfbank.util.HfbankServiceCode;
//import cn.yzyue.qrcode.hfbank.util.SignUtil;
//
///**
// * <一句话功能简述>
// * <功能详细描述>
// * 
// * @author   曾云龙
// * @version  V001Z0001
// * @date     2017年7月19日
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class Test
//{
//    public static void main(String[] args)
//    {
//        String service = "";
////        if (1 == payType)
////        {
////            // 支付宝
////            service = HfbankPayChannelEnum.ALIPAY_QR.getValue();
////        }
////        else if (2 == payType)
////        {
//            // 微信扫码支付
////          service = "SMZF004";
//            // 微信公众号支付模式
//            service = "wxPubQR";
////        }
////        else
////        {
////            throw new YzyueException("05", "支付方式有误");
////        }
//        
//        String key = "a7e4fa8f770942fb89e2226e0293f81a";
//        
//        String requestUrl = "https://apihf.onepaypass.com/aps/cloudplatform/api/trade.html";
//        
//        //代理商号-mchId
//        String mchId = "000100003";
//        
//        //商户号-subMchId
//        String subMchId = "000100003000000003";
//        
//        //商品描述-body
//        String body = subMchId+"产生的交易";
//        
//        //交易金额-amount
//        //String amount = "";
//        
//        //附加数据-description
//        String description = "";
//        
//        //货币类型-currency
//        String currency = "CNY";
//        
//        //订单支付时间-timePaid
//        String timePaid = "";
//        
//        //订单失效时间-timeExpire
//        String timeExpire = "";
//        
//        //商户订单号-outTradeNo
//        String outTradeNo = "2222";
//        
//        //商品的标题-subject
//        String subject = "联想笔记本电脑";
//        
//        //获取参数
//        Map<String, String> param = new HashMap<String, String>();
//        param.put("tradeType", "");
//        param.put("version", HfbankServiceCode.VERSION);
//        param.put("mchId", mchId);
//        param.put("channel", service);
//        
//        //终端类型
////        param.put("terminalType", value);
//        
//        //操作员
////        param.put("cashierNo", value);
//        param.put("subMchId", subMchId);
//        
//        param.put("body", body);
//        param.put("outTradeNo", outTradeNo);
//        param.put("amount", String.valueOf(amount));
////        param.put("description", description);
//        param.put("currency", currency);
////        param.put("timePaid", value);
////        param.put("timeExpire", value);
//        param.put("subject", subject);
//        
////        param.put("sign", value);
//        
//        //过滤空值或null
//        Map<String, String> filterMap = SignUtil.paraFilter(param);
////        String channel = filterMap.get("channel");
//        
//        //拼接
//        String toSign = SignUtil.createLinkString(filterMap);
//        
//        //生成签名sign
//        String sign = SignUtil.genSign(key, toSign);
//        filterMap.put("sign", sign);
//    }
//}
