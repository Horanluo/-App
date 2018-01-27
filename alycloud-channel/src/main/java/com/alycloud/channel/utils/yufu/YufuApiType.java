/*
 * 类文件名:  YufuApiType.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月18日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.utils.yufu;

/**
 * 御付交易接口
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年9月18日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum YufuApiType
{
    /**
     * 支付接口：Pay
     */
    PAY("支付接口"),
    /**
     * 订单查询接口：OrderQuery
     */
    ORDER_QUERY("订单查询接口"),
    /**
     * 商户订单查询接口：MerchOrderQuery
     */
    MERCH_ORDER_QUERY("商户订单查询接口");
    
    private String value;
    
    private YufuApiType(String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
}
