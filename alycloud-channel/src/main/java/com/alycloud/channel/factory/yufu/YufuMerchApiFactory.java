/*
 * 类文件名:  YufuMerchApiFactory.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月14日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.factory.yufu;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alycloud.channel.api.IQuickpayService;
import com.alycloud.channel.support.yufu.IYufuClient;

/**
 * 御付商户入驻接口工具类，相关配置和接口调用从此类开始
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月14日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class YufuMerchApiFactory implements ApplicationContextAware
{
    private static Map<String, IYufuClient> client;
    
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        Map<String, IYufuClient> map = applicationContext.getBeansOfType(IYufuClient.class);
        client = new HashMap<>();
        map.forEach((key,value) -> client.put(value.getKey(), value));
    }
    
    public static <T extends IYufuClient> T getClient(String key)
    {
        return (T) client.get(key);
    }
}
