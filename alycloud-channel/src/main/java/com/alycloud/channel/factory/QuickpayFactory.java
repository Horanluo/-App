/*
 * 类文件名:  QuickpayFactory.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月7日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alycloud.channel.api.IQuickpayService;
import com.alycloud.core.enums.SysChannelType;

/**
 * 快捷支付工程类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月7日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class QuickpayFactory implements ApplicationContextAware
{
    private static Map<String, IQuickpayService> quickpayService;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        Map<String, IQuickpayService> map = applicationContext.getBeansOfType(IQuickpayService.class);
        quickpayService = new HashMap<>();
        map.forEach((key,value) -> quickpayService.put(value.getChannelCode(), value));
    }
    
    @SuppressWarnings("unchecked")
	public static <T extends IQuickpayService> T getQuickpayService(String key)
    {
        return (T) quickpayService.get(key);
    }
}
