/*
 * 类文件名:  OrderQueryDTO.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月3日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单查询条件
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月3日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryDTO implements Serializable
{
    private static final long serialVersionUID = -4292632460780785139L;
    
    /**
     * 商户号
     */
    private String merchno;
    
    /**
     * 商户密钥
     */
//    private String merchKey;
    
    /**
     * 流水号
     */
    private String traceno;
    
    /**
     * 订单号
     */
//    private String refno;
    
    /**
     * 订单类型,01:扫码，02快捷
     */
//    @JsonIgnore
//    private String payType = "01" ;
    
    /**
     * 1：支付订单  3：退款
     */
    private int queryType;
    
    private String version;
    
    /**
     * 签名
     */
    private String sign;
}
