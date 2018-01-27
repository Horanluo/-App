/*
 * 类文件名:  YufuPayBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月18日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alycloud.modules.channel.yufu.enums.YufuPayType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 御付支付请求对象
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月18日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YufuPayBean
{
    /**
     * token
     */
    private String token;
    
    /**
     * 商户订单号
     */
    @JsonProperty("mer_order_no")
    private String merchOrderNo;
    /**
     * 订单备注
     */
    private String remark;
    /**
     * 订单金额(分)
     */
    @JsonProperty("mer_amount")
    private String amount;
    /**
     * 支付类型
     */
    @JsonProperty("pay_type")
    private int payType;
}
