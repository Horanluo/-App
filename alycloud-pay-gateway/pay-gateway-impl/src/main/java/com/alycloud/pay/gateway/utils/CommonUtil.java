/*
 * 类文件名:  CommonUtil.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月16日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.utils;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.alycloud.pay.gateway.exception.YzyueException;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月16日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommonUtil
{
    /**
     * 验证手机号码的格式
     * 
     * @param mobile
     *            手机号码
     * @return
     * @throws Exception
     */
    public static boolean validMobile(String mobile) throws Exception {
        if (StrUtil.isEmpty(mobile)) {
            throw new YzyueException("01", "手机号码不能为空");
        }
        int length = mobile.length();
        if (length != 11) {
            throw new YzyueException("01", "手机号码的长度[" + length + "]有误");
        }
        return true;/*
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();*/
    }
    
    /**
     * 生成指定长度的随机串
     * @author Moyq5
     * @date 2017年2月8日
     * @param length
     * @return
     */
    public static String mkRandomStr(int length) {
        final char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<length; i++){
            sb.append(chars[new Long(Math.round(Math.random() * (chars.length-1))).intValue()]);
        }
        return sb.toString();
    }
    
    /**
     * 生成签名
     * @author Moyq5
     * @date 2017年3月6日
     */
    public static String sha1(String decrypt) throws DigestException {  
        //获取信息摘要 - 参数字典排序后字符串  
        try {  
            //指定sha1算法  
            MessageDigest digest = MessageDigest.getInstance("SHA-1");  
            digest.update(decrypt.getBytes());  
            //获取字节数组  
            byte messageDigest[] = digest.digest();  
            // Create Hex String  
            StringBuffer hexString = new StringBuffer();  
            // 字节数组转换为 十六进制 数  
            for (int i = 0; i < messageDigest.length; i++) {  
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
                if (shaHex.length() < 2) {  
                    hexString.append(0);  
                }  
                hexString.append(shaHex);  
            }  
            return hexString.toString();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            throw new DigestException("签名错误！");  
        }  
    } 
}
