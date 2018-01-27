/*
 * 类文件名:  PayServiceTypeSerialize.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月14日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu.jackson.converter;

import com.alycloud.modules.channel.yufu.enums.YufuPayServiceType;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月14日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PayServiceTypeSerialize extends StdConverter<YufuPayServiceType, String>{

    public String convert(YufuPayServiceType type)
    {
        return type.name();
    }
    
}