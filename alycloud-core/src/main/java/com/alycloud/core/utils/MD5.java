/*
 * 类文件名:  MD5.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

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
	private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
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
    
    /**
     * 自定义key获取MD5加密后的值
     * 
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5(String str, String encoding) throws Exception {
    	String key = "yishangyu_key";
    	str=str+key;
        MessageDigest alga = MessageDigest.getInstance("MD5");
        byte[] b = str.getBytes(encoding);
        alga.update(b);
        byte[] digesta = alga.digest();
        return StrUtil.byte2hex(digesta);
    }
    
    /**
     * 签名字符
     *
     * @param text
     *            要签名的字符
     * @param key
     *            密钥
     * @param input_charset
     *            编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String charset) throws Exception {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, charset));
    }
    
    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
    
    public static final String MD5Encrpytion(byte[] source) {
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(source);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
                str[(k++)] = hexDigits[(byte0 & 0xF)];
            }
            for (int m = 0; m < str.length; ++m) {
                if ((str[m] >= 'a') && (str[m] <= 'z')) {
                    str[m] = (char) (str[m] - ' ');
                }
            }
            //System.out.println("[demo.idsafe.api.util.MD5Utils] [source String]" + source);
            //System.out.println("[demo.idsafe.api.util.MD5Utils] [MD5    String]" + new String(str));
            return new String(str);
        } catch (Exception e) {
        }
        return null;
    }
    
    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String input_charset) {
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String input_charset) {
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
