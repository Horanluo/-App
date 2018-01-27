/*
 * 类文件名:  YufuMD5.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月14日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.utils.yufu;

import java.security.MessageDigest;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年9月14日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class YufuMD5
{
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
        "e", "f"};
    
    public static String MD5Encode(String data)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byteArrayToHexString(md.digest(data.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    private static String byteArrayToHexString(byte[] b)
    {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b)
        {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }
    
    private static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0)
        {
            n += 256;
        }
        int d1 = n >>> 4;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
