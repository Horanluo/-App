/*
 * 类文件名:  QrcodeMerchFeeBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models.qrcode;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商户二维码渠道费率实体
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月23日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeMerchFeeBean implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3013547595431247211L;
    
    @JsonIgnore
    private Integer id;
    /**
     * 机构编码
     */
    @JsonIgnore
    private String branchno;
    /**
     * 代理商号
     */
    @JsonIgnore
    private String agentno;
    /**
     * 商户号
     */
    @JsonIgnore
    private String merchno;
    
    /**
     * 支付方式（1-支付宝 2-微信 3-百度 4-QQ 5-京东）
     */
    private Integer payType; 
    
    /**
     * 商户费率信息
     */
    private BigDecimal rate;
    
    /**
     * 扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     */
    private Integer scanType;
    
    /**
     * 结算类型(1-T+0 2-T+1)
     */
    private Integer settleType;
    
}
