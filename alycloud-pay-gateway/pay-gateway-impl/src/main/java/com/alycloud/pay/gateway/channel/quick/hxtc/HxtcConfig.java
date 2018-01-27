package com.alycloud.pay.gateway.channel.quick.hxtc;

/*
 * 类文件名:  HxtcConfig.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月8日
 * 功能版本:  V001Z0001
 */
/**
 * 汇享天成参数配置
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年8月8日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface HxtcConfig
{
    //public static final String appId = "f6c61712b6854cfd903a63daf65371cd";
    public static final String appId = "c1c33cceb3f44b4ba2083b8a023c95d3";
    
    public static final String transType = "SHORTCUTPAY";
    
//    public static final String hpMerCode = "HXTCSHORTCUTPAY@13800138000";
    
    public static final String PAYMENT_TYPE = "2008";
    
    public static final String PRODUCT_TYPE = "100000";
    
    public static final String DEFAULT_VERSION = "1.0";
    
    public static final String DEFAULT_MEMO = "测试";
    static String REQUEST_URL = "http://api.mypays.cn/api/service.json";
    //static String REQUEST_URL = "http://api.mypays.cn/api/service.json";
}
