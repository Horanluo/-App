package com.alycloud.pay.gateway.channel.util;

/**
 * 
 * <select id="channel" onchange="changeChannel(this)" name="channel">  
                    <option value="wxPub">微信公众账号支付</option>
                    <option value="wxPubQR">微信扫码支付</option>
                    <option value="wxMicro">微信付款码支付</option>
                    <option value="alipayQR">支付宝扫码支付</option>
                    <option value="alipayMicro">支付宝付款码支付</option>
                    <option value="jdMicro">京东付款码支付</option>
                    <option value="jdQR">京东扫码支付</option>
                </select><br/>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月19日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class HfbankServiceCode
{
    /**
     * 二维码交易类型
     */
    public static String TRADETYPE_QRCODE = "cs.pay.qrcode";
    
    /**
     * 收银台下单接口
     */
    public static String TRADETYPE_SUBMIT = "cs.pay.submit";
    
    /**
     * 交易查询接口
     */
    public static String TRADETYPE_ORDER_QUERY = "cs.trade.single.query";
    
    /**
     * 退款申请接口
     */
    public static String TRADETYPE_REFUND_APPLY = "cs.refund.apply";
    
    /**
     * 接口版本
     */
    public static String VERSION = "1.3";
}
