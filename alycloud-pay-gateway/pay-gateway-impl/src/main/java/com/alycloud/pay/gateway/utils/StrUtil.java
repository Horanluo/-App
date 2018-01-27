/*
 * 类文件名:  StrUtil.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
//import org.jpos.iso.ISOUtil;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

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
public class StrUtil
{
    /**
     * 验证费率
     * @param obj
     * @param min
     * @param max
     * @return
     */
    public static boolean isRate(Object obj, double min, double max){
        if(obj == null){
            return false;
        }
        try{
            BigDecimal rate = new BigDecimal(obj.toString());
            BigDecimal minRate = BigDecimal.valueOf(min);
            BigDecimal maxRate = BigDecimal.valueOf(max);
            if(rate.compareTo(minRate) == -1){
                return false;
            }
            if(rate.compareTo(maxRate) == 1){
                return false;
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     * 判断对象是否为空(包括"")
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if(obj == null) return true;
        String str = obj.toString().trim();
        return "".equals(str) || "null".equalsIgnoreCase(str);
    }
    /**
     * 验证是否为数字
     */
    public static boolean isInt(Object obj){
        if(obj == null) return false;
        if(obj.toString().trim().equals("")) return false;
        if(obj.toString().matches("[0-9]{0," + obj.toString().length() + "}")) return true;
        return false;
    }
    
    public static String get(Object obj){
        if(obj == null) return "";
        return obj.toString().trim();
    }
    

    /**
     * char类型转byte类型
     * 
     * @param c
     * @return
     */
    private static byte char2byte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 字节数组转字符串类型
     * 
     * @param src
     * @return
     */
    public static String byte2hex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 字符串转字节数组
     * 
     * @param hexString
     * @return
     */
    public static byte[] hex2byte(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (char2byte(hexChars[pos]) << 4 | char2byte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 异或运算
     * 
     * @param a
     * @param b
     * @return
     */
    public static byte[] xor(byte[] a, byte[] b) {
        byte[] result = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = (byte) (a[i] ^ b[i]);
        }
        return result;
    }
    
    /**
     * 获取3DES加解密的标准密钥
     * 
     * @param key
     *            密钥
     * @return
     * @throws Exception
     */
    private static byte[] getStandardKey(byte[] key) throws Exception {
        if (key == null) {
            throw new Exception("密钥不能为空");
        }
        byte[] result = new byte[24];
        if (key.length == 8) {
            System.arraycopy(key, 0, result, 0, key.length);
            System.arraycopy(key, 0, result, key.length, key.length);
            System.arraycopy(key, 0, result, 2 * key.length, key.length);
            return result;
        } else if (key.length == 16) {
            System.arraycopy(key, 0, result, 0, key.length);
            System.arraycopy(key, 0, result, key.length, key.length / 2);
            return result;
        } else if (key.length == 24) {
            return key;
        } else {
            throw new Exception("密钥的长度[" + key.length + "]有误,期望值[8/16/24]");
        }
    }

    /**
     * DES加密(支持单倍长、双倍长和三倍长密钥)
     * 
     * @param key
     *            加密的密钥(长度只能是8/16/24字节)
     * @param src
     *            加密的数据(长度只能是8/16/24字节，且不大于密钥的长度)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByDESede(byte[] key, byte[] src)
            throws Exception {
        byte[] keybyte = getStandardKey(key);
        SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
        Cipher c1 = Cipher.getInstance("DESede/ECB/NoPadding");
        c1.init(Cipher.ENCRYPT_MODE, deskey);
        return c1.doFinal(src);
    }

    /**
     * DES解密(支持单倍长、双倍长和三倍长密钥)
     * 
     * @param key
     *            解密的密钥(长度只能是8/16/24字节)
     * @param src
     *            解密的数据(长度只能是8/16/24字节，且不大于密钥的长度)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByDESede(byte[] key, byte[] src)
            throws Exception {
        byte[] keybyte = getStandardKey(key);
        SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
        Cipher c1 = Cipher.getInstance("DESede/ECB/NoPadding");
        c1.init(Cipher.DECRYPT_MODE, deskey);
        return c1.doFinal(src);
    }

    /**
     * 将以元为单位的金额转成以分为单位的金额
     * 
     * @param amount
     * @return
     * @throws Exception 
     */
//    public static String formatAmount(String amount) throws Exception {
//        BigDecimal money = new BigDecimal(amount);
//        money = money.multiply(BigDecimal.valueOf(100));
//        String result = money.toBigInteger().toString();
//        return ISOUtil.padleft(result, 12, '0');
//    }
    
    /**
     * 格式化输出XML文件
     * @param unformattedXml
     * @return
     * @throws Exception
     */
    public static String parseXml(String unformattedXml) throws Exception {
        Writer out = null;
        try {
            Document document = parseXmlFile(unformattedXml);
            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if(out != null){
                out.close();
            }
        }
    }

    private static Document parseXmlFile(String in) throws Exception {
        DocumentBuilderFactory dbf = null;
        DocumentBuilder db = null;
        StringReader sr = null;
        InputSource is = null;
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            sr = new StringReader(in);
            is = new InputSource(sr);
            return db.parse(is);
        } catch (Exception e) {
            throw e;
        }finally{
            if(sr != null){
                sr.close();
            }
            is = null;
            sr = null;
            db = null;
            dbf = null;
        }
    }
    /**
     * 将字符串转成MAP
     * @param message
     * @return
     */
    public static Map<String, String> str2map(String message){
        Map<String, String> param = new HashMap<String, String>();
        String[] data = message.split("&");
        for (int i = 0; i < data.length; i++) {
            String str = data[i];
            int index = str.indexOf("=");
            String key = str.substring(0, index);
            String value = str.substring(index + 1);
            param.put(key, value);
        }
        return param;
    }
    
    /**
     * 将MAP转成字符串
     * @param param
     * @return
     * @throws Exception 
     */
    public static String map2str(Map<String, String> param, String encoding) throws Exception{
        List<String> keys = new ArrayList<String>(param.keySet());
        Collections.sort(keys);
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            sb.append(key).append("=");
            if (encoding != null) {
                String value = param.get(key);
                if(value != null){
                    sb.append(URLEncoder.encode(value, encoding));
                }
            } else {
                sb.append(param.get(key));
            }
            sb.append("&");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    /**
     * 接收威富通的通知内容
     * @param request
     * @return
     * @throws Exception 
     */
    public static String parseRequst(HttpServletRequest request, String encoding) throws Exception{
        String body = "";
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            is = request.getInputStream(); 
            isr = new InputStreamReader(is, encoding);
            BufferedReader br = new BufferedReader(isr);
            while(true){
                String info = br.readLine();
                if(info == null){
                    break;
                }
                if(body == null || "".equals(body)){
                    body = info;
                }else{
                    body += info;
                }
            } 
            return body;
        } catch (Exception e) {
            throw e;
        }finally{
            if(isr != null){
                isr.close();
            }
            if(is != null){
                is.close();
            }
        }    
    }

    public static Map<String, String> toMap(byte[] xmlBytes,String charset) throws Exception{
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
        source.setEncoding(charset);
        org.dom4j.Document doc = reader.read(source);
        Map<String, String> params = toMap(doc.getRootElement());
        return params;
    }
    
    /**
     * 转MAP
     * @author  
     * @param element
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Map<String, String> toMap(Element element){
        Map<String, String> rest = new HashMap<String, String>();
        List<Element> els = element.elements();
        for(Element el : els){
            rest.put(el.getName().toLowerCase(), el.getTextTrim());
        }
        return rest;
    }
}
