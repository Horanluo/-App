/*
 * 类文件名:  QrcodePay.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.core.qrcode;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 二维码支付
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月27日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface QrcodePay
{
    /**
     * 设置T+0的结算信息
     * 
     * @param accountno
     *            结算账号
     * @param accountName
     *            结算户名
     * @param bankno
     *            联行号
     * @param bankType
     *            银行类别
     * @param bankName
     *            银行支行信息
     * @param certno
     *            身份证号码
     * @param mobile
     *            手机号码
     * @param merchFee
     *            商户手续费
     */
    public void setSettleInfo(String accountno, String accountName, String bankno, String bankType, String bankName, String certno, String mobile,
            BigDecimal merchFee);

    /**
     * 二维码主扫
     * 
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param orderno
     *            订单号
     * @param amount
     *            交易金额
     * @param authCode
     *            授权号
     * @return
     * @throws Exception
     */
    public Map<String, String> activePay(int settleType, String orderno, long amount, String authCode) throws YzyueException, Exception;

    /**
     * 二维码被扫
     * 
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param orderno
     *            订单号
     * @param amount
     *            交易金额
     * @return
     * @throws Exception
     */
    public Map<String, String> passivePay(int settleType, String orderno, long amount) throws YzyueException, Exception;

    /**
     * 二维码公众号支付
     * 
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param orderno
     *            订单号
     * @param amount
     *            交易金额
     * @return
     * @throws Exception
     */
    public Map<String, String> officalPay(int settleType, String orderno, long amount) throws YzyueException, Exception;

    /**
     * 二维码公众号支付
     * 
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param orderno
     *            订单号
     * @param amount
     *            交易金额
     * @param openId
     *            用户的OPEN-ID
     * @return
     * @throws Exception
     */
    public Map<String, String> officalPay(int settleType, String orderno, long amount, String openId) throws YzyueException, Exception;

    /**
     * 二维码WAP支付
     * 
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param orderno
     *            订单号
     * @param amount
     *            交易金额
     * @return
     * @throws Exception
     */
    public Map<String, String> wapPay(int settleType, String orderno, long amount) throws YzyueException, Exception;

    /**
     * 二维码交易查询
     * 
     * @param transDate
     *            订单日期(格式yyyy-MM-dd)
     * @param orderno
     *            订单号
     * @return
     * @throws Exception
     */
    public Map<String, String> query(QrcodeOrderBean order,String transDate, String orderno) throws YzyueException, Exception;

    /**
     * 判断是否支持T+0交易
     * 
     * @return
     */
    public boolean supportT0();

    /**
     * 判断是否支持信用卡
     * 
     * @return
     */
    public boolean noCredit();
    
    /**
     * 判断是否需要OpenId
     * 
     * @return
     */
    public boolean needOpenId();

    public void setQrcodeOrderBean(QrcodeOrderBean order);

    /**
     * 执行退款交易
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @throws IOException 
     * @throws YzyueException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     * @date     2017年8月18日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public Map<String, String> refundOrder(QrcodeOrderBean order, String transDate, String orderno, long amountL) throws JsonParseException, JsonMappingException, YzyueException, IOException;
}
