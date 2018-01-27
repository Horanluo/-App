/*
 * 类文件名:  AbstractQrcodePay.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.core.qrcode;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.alycloud.pay.gateway.models.qrcode.QrcodeChannelBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 二维码支付的抽象类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月27日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Slf4j
public abstract class AbstractQrcodePay implements QrcodePay
{
    /**
     * 渠道信息
     */
    protected QrcodeChannelBean channel;
    /**
     * 渠道商户信息
     */
    protected QrcodeMerchBean merch;
    /**
     * 商户名称
     */
    protected String merchName;
    /**
     * 支付方式(1-支付宝 2-微信 4-百度钱包 8-QQ钱包 16-京东钱包)
     */
    protected int payType;
    /**
     * 日志对象
     */
//    protected Log logger;
    
    /*******************************************************************************
     *  T+0结算信息
     *******************************************************************************/
    /**
     * T+0结算卡号
     */
    protected String accountno;
    /**
     *  T+0结算户名
     */
    protected String accountName;
    /**
     * T+0结算联行号
     */
    protected String bankno;
    /**
     * T+0结算银行类别
     */
    protected String bankType;
    /**
     * T+0结算支行名称
     */
    protected String bankName;
    /**
     * T+0结算身份证号码
     */
    protected String certno;
    /**
     * T+0结算手机号码
     */
    protected String mobile;
    /**
     * 商户T+0手续费
     */
    protected BigDecimal merchFee;
    
    
    protected QrcodeOrderBean order;
    
    /**
     * 商户注册调用的构造方法
     * @param logger
     * @param channel
     */
    public AbstractQrcodePay(QrcodeChannelBean channel){
        this.channel = channel;
    }
    
    /**
     * 构造方法
     * 
     * @param channel
     *            渠道信息
     * @param merch
     *            渠道商户信息
     */
    protected AbstractQrcodePay(QrcodeChannelBean channel, QrcodeMerchBean merch) {
        this.channel = channel;
        this.merch = merch;
    }
    
    /**
     * 交易查询则调用该方法
     * 
     * @param channel
     *            渠道信息
     * @param merch
     *            渠道商户信息
     * @param payType
     *            支付方式
     */
    protected AbstractQrcodePay( QrcodeChannelBean channel, QrcodeMerchBean merch, int payType) {
        super();
        this.channel = channel;
        this.merch = merch;
        this.payType = payType;
    }

    /**
     * 如果渠道只支持微信支付，则调用该方法
     * 
     * @param channel
     *            渠道信息
     * @param merch
     *            渠道商户信息
     * @param merchName
     *            商户名称
     */
    protected AbstractQrcodePay(QrcodeChannelBean channel, QrcodeMerchBean merch, String merchName) {
        this.channel = channel;
        this.merch = merch;
        this.merchName = merchName;
    }

    /**
     * 如果渠道只支持T+1交易，则调用该方法
     * 
     * @param channel
     *            渠道信息
     * @param merch
     *            渠道商户信息
     * @param merchName
     *            商户名称
     * @param payType
     *            支付方式
     */
    protected AbstractQrcodePay(QrcodeChannelBean channel, QrcodeMerchBean merch, String merchName, int payType) {
        super();
        this.channel = channel;
        this.merch = merch;
        this.merchName = merchName;
        this.payType = payType;
    }

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
            BigDecimal merchFee) {
        this.accountno = accountno;
        this.accountName = accountName;
        this.bankno = bankno;
        this.bankType = bankType;
        this.bankName = bankName;
        this.certno = certno;
        this.mobile = mobile;
        this.merchFee = merchFee;
    }

    /**
     * 渠道响应信息
     * 
     * @param respCode
     *            响应码(00-成功 10-暂未支付 20-交易失败 30-渠道超时)
     * @param message
     *            响应内容
     * @return
     */
    protected Map<String, String> buildResponse(String respCode, String message) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("respCode", respCode);
        param.put("message", message);
        return param;
    }
    
    public void setQrcodeOrderBean(QrcodeOrderBean order)
    {
        this.order = order;
    }
}
