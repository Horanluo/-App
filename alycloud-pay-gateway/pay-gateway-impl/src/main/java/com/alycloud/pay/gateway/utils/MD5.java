/*
 * 类文件名:  MD5.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月23日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MD5
{
    /**
     * 获取MD5加密后的值
     * 
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5ofStr(String str, String encoding) throws Exception {
        MessageDigest alga = MessageDigest.getInstance("MD5");
        byte[] b = str.getBytes(encoding);
        alga.update(b);
        byte[] digesta = alga.digest();
        return StrUtil.byte2hex(digesta);
    }
}
