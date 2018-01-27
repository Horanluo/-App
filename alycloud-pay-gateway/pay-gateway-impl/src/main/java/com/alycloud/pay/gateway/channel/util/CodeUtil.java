/*
 * 类文件名:  CodeUtil.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.channel.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月27日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CodeUtil
{
    public static String strDecode(String str, String charset)
    {
        if (StringUtils.isBlank(str))
        {
            return "";
        }
        try
        {
            return URLDecoder.decode(str, charset);
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
    
    public static String strEncode(String str, String charset)
    {
        if (org.apache.commons.lang3.StringUtils.isBlank(str))
        {
            return "";
        }
        try
        {
            return URLEncoder.encode(str, charset);
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
}
