/*
 * 类文件名:  YufuMerchPayServiceBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alycloud.modules.channel.yufu.enums.YufuPayServiceType;
import com.alycloud.modules.channel.yufu.jackson.converter.PayServiceTypeSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 商户入驻入接口－请求参数－支付服务信息
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YufuMerchPayServiceBean
{
    /**
     * 支付服务名，必填
     */
    @JsonSerialize(converter = PayServiceTypeSerialize.class)
    private YufuPayServiceType payService;
    
    /**
     * 费率(‰)，单位：千分之，例如千6费率填6
     */
    private String scale;

    /**
     * 借记卡费率(‰)，单位：千分之，例如千6费率填6
     */
    private String debitCardRate;
    
    /**
     * 贷记卡费率(‰)，单位：千分之，例如千6费率填6
     */
    private String creditCardRate;
    
    /**
     * 封顶手续金额，快捷0表示手续费无封顶，单位（分）
     */
    @JsonProperty("limitAmont")
    private String limitAmount;

    /**
     * D0费率，d0Rate，d0MinAmount，d0FeeBi这三个参数如果传了，需要一起传递<br>
     * 单位：千分之，例如千6费率填6，bat的要保持一致，d0Rate是在scale的基础上额外收取d0手续费
     */
    private String d0Rate;

    /**
     * D0单笔最低交易金额，单位（分）,单笔最低交易金额，超过这个金额才D0, bat的要保持一致
     */
    private String d0MinAmount;

    /**
     * D0每笔费用，单位（分），bat的要保持一致
     */
    private String d0FeeBi;

    /**
     * 保底费用，单位（分），保留字段
     */
    private String bottomFee;

    /**
     * 行业类型，必填，快捷可默认填写5210
     */
    private String tradeType;
}
