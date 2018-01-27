/*
 * 类文件名:  QrcodeQueryResponseBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月11日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alycloud.pay.gateway.models.ResponseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 订单查询响应对象
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月11日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeQueryResponseBean extends ResponseBean
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4678700326910740492L;
    
    @JsonIgnore
    private String refno;
    
    /**
     * 支付方式
     */
    @JsonIgnore
    private String payType;
    /**
     * 扫码方式
     */
    @JsonIgnore
    private String scanType;
    /**
     * 商户订单号
     */
    private String traceno;
    /**
     * 订单号
     */
    @JsonIgnore
    private String orderno;
    /**
     * 渠道订单号
     */
    @JsonIgnore
    private String channelOrderno;
    /**
     * 付款状态(0-待支付 1-未支付 2-已支付 3-支付失败)
     */
    private String payStatus;
    
    private String outTraceno;
}
