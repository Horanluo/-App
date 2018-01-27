/*
 * 类文件名:  HxtcStringUtils.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月29日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.hfbank;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月29日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HxtcStringUtils
{
    public static BigDecimal stringConvertToBigDecimal(String data)
    {
        if(StringUtils.isEmpty(data))
        {
            return new BigDecimal(0);
        }
        return new BigDecimal(data);
    }
}
