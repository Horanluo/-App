/*
 * 类文件名:  RequestBuilder.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.channel.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alycloud.pay.gateway.dto.MerchDTO;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchFeeBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 请求对象构造
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月23日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RequestBuilder
{
    static DecimalFormat formater = new DecimalFormat("###0.00");
    
    public static Map<String, String> registerBuilder(MerchDTO merchDTO, String mchId, int settleType)
    {
        
        Map<String, String> retMap = new HashMap<String, String>();
        
        retMap.put("tradeType", "cs.merchant.register");
        retMap.put("version", "1.3");
        retMap.put("mchId", mchId);
        retMap.put("merchantName", merchDTO.getFullName());
        retMap.put("merchantShortName", merchDTO.getMerchName());
        retMap.put("opName", merchDTO.getLegalName());
        retMap.put("opPhone", merchDTO.getMobile());
        retMap.put("customerTelephone", merchDTO.getMobile());
        retMap.put("proName", merchDTO.getProvince());
        retMap.put("cityName", merchDTO.getCity());
        retMap.put("areaName", merchDTO.getAreaName());
        retMap.put("address", merchDTO.getAddress());
        retMap.put("telephone", merchDTO.getMobile());
        retMap.put("email", merchDTO.getEmail());
        
        String categoryWx = merchDTO.getCategoryWx() == null ? "204" : merchDTO.getCategoryWx();
        
        
        // 微信经营类别
        retMap.put("categoryWx", categoryWx);
        
        // 支付宝经营类别
        String categoryZfb = merchDTO.getCategoryZfb() == null ? "2015091000052157" : merchDTO.getCategoryZfb();
        retMap.put("categoryZfb", categoryZfb);
        
        retMap.put("bankType", merchDTO.getBankType());
        retMap.put("bankName", merchDTO.getBankName());
        retMap.put("bankCardNo", merchDTO.getBankno());
        retMap.put("bankAccName", merchDTO.getAccountName());
        retMap.put("accountType", String.valueOf(merchDTO.getAccountType()));
        retMap.put("businessLicenseType", merchDTO.getBusinessLicenseType());
        retMap.put("businessLicense", merchDTO.getBusinessLicense());
        retMap.put("legalPersonType", merchDTO.getLegalPersonType());
        retMap.put("certificateType", "1");
        retMap.put("certificateNo", merchDTO.getIdentityCard());
        if (settleType == 1)
        {
            try
            {
                retMap.put("payTypes", hfbankbuilderD0(merchDTO.getPayTypes()));
            }
            catch (JsonProcessingException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                retMap.put("payTypes", hfbankbuilderT1(merchDTO.getPayTypes()));
            }
            catch (JsonProcessingException e)
            {
                e.printStackTrace();
            }
        }
        
        retMap.put("merchantOperationType", "1");
        retMap.put("rmk", mchId + "发展的代理商");
        
        return retMap;
    }
    /**
     * 
     * [
            {
                "payTypeCode": "jdQpay",
                "rate": 1,
                "rateType": "1",
                "rmk": "京东快捷支付"
            },
            {
                "payTypeCode": "alipayH5",
                "rate": 1,
                "rateType": "1",
                "rmk": "支付宝服务窗"
            },
            {
                "payTypeCode": "alipayMicro",
                "rate": 1,
                "rateType": "1",
                "rmk": "支付宝付款码"
            },
            {
                "payTypeCode": "alipayQR",
                "rate": 1,
                "rateType": "1",
                "rmk": "支付宝扫码"
            },
            {
                "payTypeCode": "wxApp",
                "rate": 1,
                "rateType": "1",
                "rmk": "微信APP"
            },
            {
                "payTypeCode": "wxMicro",
                "rate": 1,
                "rateType": "1",
                "rmk": "微信付款码"
            },
            {
                "payTypeCode": "wxPub",
                "rate": 1,
                "rateType": "1",
                "rmk": "微信公众号"
            },
            {
                "payTypeCode": "wxPubQR",
                "rate": 1,
                "rateType": "1",
                "rmk": "微信扫码"
            }
        ]
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月24日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    private static String hfbankbuilderD0(List<QrcodeMerchFeeBean> list)
        throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        
        for (QrcodeMerchFeeBean bean : list)
        {
            
            if (bean.getSettleType() == 1)
            {
                // 支付宝
                if (bean.getPayType() == 1)
                {
                    if(bean.getScanType() == 1)
                    {
                        ObjectNode nodeAlipay = mapper.createObjectNode();
                        nodeAlipay.put("payTypeCode", "alipayMicro");
                        nodeAlipay.put("rateType", "1");
                        nodeAlipay.put("rmk", "支付宝付款码");
                        nodeAlipay.put("rate", mul(bean.getRate(), 100));
                        arrayNode.add(nodeAlipay);
                        
                    }else if (bean.getScanType() == 2){
                        ObjectNode nodeAlipay = mapper.createObjectNode();
                        nodeAlipay.put("payTypeCode", "alipayQR");
                        nodeAlipay.put("rateType", "1");
                        nodeAlipay.put("rmk", "支付宝扫码");
                        nodeAlipay.put("rate", mul(bean.getRate(), 100));
                        arrayNode.add(nodeAlipay);
                        
                    }else if (bean.getScanType() == 4){
                        ObjectNode nodeAlipay = mapper.createObjectNode();
                        nodeAlipay.put("payTypeCode", "alipayH5");
                        nodeAlipay.put("rateType", "1");
                        nodeAlipay.put("rmk", "支付宝服务窗");
                        nodeAlipay.put("rate", mul(bean.getRate(), 100));
                        arrayNode.add(nodeAlipay);
                    }
                }
                else if (bean.getPayType() == 2) // 微信
                {
                    if(bean.getScanType() == 1)
                    {
                        ObjectNode nodeWxMicro = mapper.createObjectNode();
                        nodeWxMicro.put("payTypeCode", "wxMicro");
                        nodeWxMicro.put("rateType", "1");
                        nodeWxMicro.put("rmk", "微信付款码");
                        nodeWxMicro.put("rate", mul(bean.getRate(), 100));
                        arrayNode.add(nodeWxMicro);
                        
                    }else if (bean.getScanType() == 2){
                        ObjectNode nodeWxPubQR = mapper.createObjectNode();
                        nodeWxPubQR.put("payTypeCode", "wxPubQR");
//                        nodeWeixin.put("notifyUrl", "http://www.baidu.com/pay/cloudplatform/api/notify/weixin");
                        nodeWxPubQR.put("rateType", "1");
                        nodeWxPubQR.put("rmk", "微信扫码");
                        nodeWxPubQR.put("rate", mul(bean.getRate(), 100));
                        arrayNode.add(nodeWxPubQR);
                        
                    }else if (bean.getScanType() == 4){
                        ObjectNode nodeWxPub = mapper.createObjectNode();
                        nodeWxPub.put("payTypeCode", "wxPub");
                        nodeWxPub.put("rateType", "1");
                        nodeWxPub.put("rmk", "微信公众号");
                        nodeWxPub.put("rate", mul(bean.getRate(), 100));
                        arrayNode.add(nodeWxPub);
                    }else if (bean.getScanType() == 8){
                        ObjectNode nodeWxApp = mapper.createObjectNode();
                        nodeWxApp.put("payTypeCode", "wxApp");
                        nodeWxApp.put("rateType", "1");
                        nodeWxApp.put("rmk", "微信APP");
                        nodeWxApp.put("rate", mul(bean.getRate(), 100));
                        arrayNode.add(nodeWxApp);
                    }
                }else if (bean.getPayType() == 5) // 京东快捷
                {
                        ObjectNode nodeWxMicro = mapper.createObjectNode();
                        nodeWxMicro.put("payTypeCode", "jdQpay");
                        nodeWxMicro.put("rateType", "1");
                        nodeWxMicro.put("rmk", "京东快捷支付");
                        nodeWxMicro.put("rate", mul(bean.getRate(), 100));
                        arrayNode.add(nodeWxMicro);
                }
                
            }
            
        }
//        arrayNode.add(nodeAlipay);
//        arrayNode.add(nodeWeixin);
        return mapper.writeValueAsString(arrayNode);
    }
    
    private static String hfbankbuilderT1(List<QrcodeMerchFeeBean> list)
        throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
//        ObjectNode nodeWeixin = mapper.createObjectNode();
//        nodeWeixin.put("payTypeCode", "wxPubQR");
//        nodeWeixin.put("notifyUrl", "http://www.baidu.com/pay/cloudplatform/api/notify/weixin");
//        nodeWeixin.put("rateType", "1");
//        nodeWeixin.put("rmk", "微信扫码");
//        ObjectNode nodeAlipay = mapper.createObjectNode();
//        
//        nodeAlipay.put("payTypeCode", "alipayQR");
//        nodeAlipay.put("notifyUrl", "http://www.baidu.com/pay/cloudplatform/api/notify/alipay");
//        nodeAlipay.put("rateType", "1");
//        nodeAlipay.put("rmk", "支付宝扫码");
        for (QrcodeMerchFeeBean bean : list)
        {
            
            if (bean.getSettleType() == 2)
            {
                
                if (bean.getSettleType() == 1)
                {
                    // 支付宝
                    if (bean.getPayType() == 1)
                    {
                        if(bean.getScanType() == 1)
                        {
                            ObjectNode nodeAlipay = mapper.createObjectNode();
                            nodeAlipay.put("payTypeCode", "alipayMicro");
                            nodeAlipay.put("rateType", "1");
                            nodeAlipay.put("rmk", "支付宝付款码");
                            nodeAlipay.put("rate", mul(bean.getRate(), 100));
                            arrayNode.add(nodeAlipay);
                            
                        }else if (bean.getScanType() == 2){
                            ObjectNode nodeAlipay = mapper.createObjectNode();
                            nodeAlipay.put("payTypeCode", "alipayQR");
                            nodeAlipay.put("rateType", "1");
                            nodeAlipay.put("rmk", "支付宝扫码");
                            nodeAlipay.put("rate", mul(bean.getRate(), 100));
                            arrayNode.add(nodeAlipay);
                            
                        }else if (bean.getScanType() == 4){
                            ObjectNode nodeAlipay = mapper.createObjectNode();
                            nodeAlipay.put("payTypeCode", "alipayH5");
                            nodeAlipay.put("rateType", "1");
                            nodeAlipay.put("rmk", "支付宝服务窗");
                            nodeAlipay.put("rate", mul(bean.getRate(), 100));
                            arrayNode.add(nodeAlipay);
                        }
                    }
                    else if (bean.getPayType() == 2) // 微信
                    {
                        if(bean.getScanType() == 1)
                        {
                            ObjectNode nodeWxMicro = mapper.createObjectNode();
                            nodeWxMicro.put("payTypeCode", "wxMicro");
                            nodeWxMicro.put("rateType", "1");
                            nodeWxMicro.put("rmk", "微信付款码");
                            nodeWxMicro.put("rate", mul(bean.getRate(), 100));
                            arrayNode.add(nodeWxMicro);
                            
                        }else if (bean.getScanType() == 2){
                            ObjectNode nodeWxPubQR = mapper.createObjectNode();
                            nodeWxPubQR.put("payTypeCode", "wxPubQR");
//                            nodeWeixin.put("notifyUrl", "http://www.baidu.com/pay/cloudplatform/api/notify/weixin");
                            nodeWxPubQR.put("rateType", "1");
                            nodeWxPubQR.put("rmk", "微信扫码");
                            nodeWxPubQR.put("rate", mul(bean.getRate(), 100));
                            arrayNode.add(nodeWxPubQR);
                            
                        }else if (bean.getScanType() == 4){
                            ObjectNode nodeWxPub = mapper.createObjectNode();
                            nodeWxPub.put("payTypeCode", "wxPub");
                            nodeWxPub.put("rateType", "1");
                            nodeWxPub.put("rmk", "微信公众号");
                            nodeWxPub.put("rate", mul(bean.getRate(), 100));
                            arrayNode.add(nodeWxPub);
                        }else if (bean.getScanType() == 8){
                            ObjectNode nodeWxApp = mapper.createObjectNode();
                            nodeWxApp.put("payTypeCode", "wxApp");
                            nodeWxApp.put("rateType", "1");
                            nodeWxApp.put("rmk", "微信APP");
                            nodeWxApp.put("rate", mul(bean.getRate(), 100));
                            arrayNode.add(nodeWxApp);
                        }
                    }else if (bean.getPayType() == 5) // 京东快捷
                    {
                            ObjectNode nodeWxMicro = mapper.createObjectNode();
                            nodeWxMicro.put("payTypeCode", "jdQpay");
                            nodeWxMicro.put("rateType", "1");
                            nodeWxMicro.put("rmk", "京东快捷支付");
                            nodeWxMicro.put("rate", mul(bean.getRate(), 100));
                            arrayNode.add(nodeWxMicro);
                    }
                    
                }
//                // 支付宝
//                if (bean.getPayType() == 1)
//                {
//                        nodeAlipay.put("rate", mul(bean.getRate(), 100));
//                    
//                    // 微信
//                }
//                else if (bean.getPayType() == 2)
//                {
//                    nodeWeixin.put("rate", mul(bean.getRate(), 100));
//                }
                
            }
            
        }
//        arrayNode.add(nodeAlipay);
//        arrayNode.add(nodeWeixin);
        return mapper.writeValueAsString(arrayNode);
    }
    
    /**
     * 
     * 乘法
     * 
     * @author 曾云龙
     * @version V001Z0001
     * @date 2017年7月25日
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public static String mul(BigDecimal v1, long v2)
    {
        return formater.format(v1.multiply(new BigDecimal(v2)));
    }
}
