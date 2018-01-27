/*
 * 类文件名:  YufuChannelMerchBiz.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 御付渠道商户业务
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月15日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YufuChannelMerchBiz
{
    private Long id;
    private Long yufuId;// 渠道商户Id（平台ID）
    private String payService;// 支付服务名WX：微信、QQ：手机QQ、ZFB：支付宝、CARD：银行卡，KJ：快捷支付，WXAPP：微信APP，微信和微信app只能选择其一，如果都需要只能申请两个商户
    private String scale;// 费率(‰)单位：千分之，例如千6费率填6
    private String debitCardRate; // 借记卡费率(‰)单位：千分之，例如千6费率填6
    private String creditCardRate; // 贷记卡费率(‰)单位：千分之，例如千6费率填6
    private String limitAmount; // 封顶手续金额, 快捷0表示手续费无封顶), 单位（分）
    private String d0Rate; // D0费率， 单位：千分之，例如千6费率填6，bat的要保持一致，d0Rate是在scale的基础上额外收取d0手续费
    private String d0MinAmount; // D0单笔最低交易金额，单位（分）,单笔最低交易金额，超过这个金额才D0, bat的要保持一致
    private String d0FeeBi; // D0每笔费用， 单位（分），bat的要保持一致
    private String tradeType;// 行业类型
    private String bottomFee; // 保底费用，单位（分），保留字段
}
