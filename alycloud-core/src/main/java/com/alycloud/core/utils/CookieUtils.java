/*
 * 类文件名:  CookieUtils.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年10月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.utils;

import javax.servlet.http.Cookie;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年10月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CookieUtils
{
    public static Cookie addCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        //不设置在浏览器打开过程中一直有效
//      cookie.setMaxAge(3600); // 设置生命周期为一个小时
        cookie.setDomain("esyto.com"); // 设置域名
        cookie.setPath("/"); // 设置路径为根路径
        return cookie;
    }
    
    public static Cookie addCookie(String key, String value, int validTime) {
        Cookie cookie = new Cookie(key, value);
        //不设置在浏览器打开过程中一直有效
      cookie.setMaxAge(validTime); // 设置生命周期为一个小时
        cookie.setDomain("esyto.com"); // 设置域名
        cookie.setPath("/"); // 设置路径为根路径
        return cookie;
    }
}
