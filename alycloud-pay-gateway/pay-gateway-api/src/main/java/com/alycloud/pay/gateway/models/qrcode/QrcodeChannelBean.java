/*
 * 类文件名:  QrcodeChannelBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models.qrcode;

import com.alycloud.pay.gateway.utils.PayChannelEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二维码渠道信息
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月25日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeChannelBean
{
    private int id;
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 渠道商户名称
     */
    private String channelName;
    /**
     * 渠道状态(1-启用 2-禁用)
     */
    private int status;
    /**
     * 代理号
     */
    private String agentno;
    /**
     * 计算方式
     */
    private int calType;
    /**
     * RSA公钥
     */
    private String keyRsa;
    /**
     * MD5秘钥
     */
    private String keyMd5;
    /**
     * 请求地址
     */
    private String urlRequest;
    /**
     * 通知地址
     */
    private String urlNotify;
    /**
     * 返回地址
     */
    private String urlReturn;
    /**
     * 代付地址
     */
    private String urlPay;
    /**
     * 备注
     */
    private String remark;
    /**
     * 商户RSA私钥
     */
    private String merchRsaKey;
    
    /**
     * 支付渠道
     */
    private String channel;
}
