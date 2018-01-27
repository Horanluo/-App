/*
 * 类文件名:  YufuMerchApiUtils.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.utils.yufu;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum YufuMerchApiType
{
    /**
     * 商户入驻接口：MerchApply
     */
    MERCH_APPLY("商户入驻接口"),
    /**
     * 商户审核结果查询接口：MerchQuery
     */
    MERCH_QUERY("商户审核查询接口");
    
    private String value;
    
    private YufuMerchApiType (String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
    
//    private Class<? extends AbstractClient<?,?>> clientClass;
    
//    YufuMerchApiType(Class<? extends AbstractClient<?,?>> clientClass) {
//        this.clientClass = clientClass;
//    }
//
//    public Class<? extends AbstractClient<?,?>> getClientClass() {
//        return clientClass;
//    }
}
