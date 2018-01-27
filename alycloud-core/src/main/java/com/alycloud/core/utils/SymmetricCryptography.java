/*
 * 类文件名:  SymmetricCryptography.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年10月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 对称密钥算法加解密通用实现
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年10月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SymmetricCryptography
{
    /**
     * 密钥生成算法
     */
    private String keyAlgorithm;

    /**
     * 加解密算法/分组策略/填充方式
     */
    private String cipherAlgorithm;
    
    /**
     * 对称密钥算法 加解密密钥
     */
    private static String key = "yEJHYHdQuuhpR49y8uD3/A==";

    /**
     * 密钥长度
     */
    private int keySize;
    
    private static SymmetricCryptography instance = new SymmetricCryptography("AES", "AES/ECB/PKCS5Padding", 128);
    
    public static SymmetricCryptography newInstance()
    {
        return instance;
    }
    public SymmetricCryptography(String keyAlgorithm, String cipherAlgorithm, int keySize) {
        this.keyAlgorithm = keyAlgorithm;
        this.cipherAlgorithm = cipherAlgorithm;
        this.keySize = keySize;
    }

    /**
     * 生成一个密钥
     * @return 密钥的二进制形式
     */
    public byte[] initKey() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(this.keyAlgorithm);
        keyGenerator.init(this.keySize);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 转换密钥
     * @param key 密钥的二进制形式
     * @return Key 密钥对象
     */
    private Key toSecretKey(byte[] key) {
        return new SecretKeySpec(key, this.keyAlgorithm);
    }

    /**
     * 加密操作
     * @param data 需要加密的数据
     * @param key 密钥的二进制形式
     * @return 加密后的数据
     */
    public byte[] encrypt(byte[] data, byte[] key) throws Exception{
        Key secretKey = this.toSecretKey(key);
        Cipher cipher = cipher = Cipher.getInstance(this.cipherAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }
    
    /**
     * 
     * 数据加密
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年10月13日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static String encryptData(String data)
    {
        try
        {
            return Base64.encodeBase64String(instance.encrypt(data.getBytes(), Base64.decodeBase64(key)));
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * 数据解密
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年10月13日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static String decryptData(String encryptData)
    {
        try
        {
            return new String(instance.decrypt(Base64.decodeBase64(encryptData), Base64.decodeBase64(key)));
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解密操作
     * @param data 需要解密的数据
     * @param key 密钥的二进制形式
     * @return 解密后的数据
     */
    public byte[] decrypt(byte[] data, byte[] key) throws Exception{
        Key secretKey = this.toSecretKey(key);
        Cipher cipher = Cipher.getInstance(this.cipherAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) throws Exception{
        String data = "测试对称密钥算法加解密";
//        System.out.println("加密前数据：" + data);
//        // 其它对称密钥算法更改构造参数即可
//        // 例如SymmetricCryptography symmetricCryptography = new SymmetricCryptography("DES", "DES/ECB/PKCS5Padding", 56);
//        SymmetricCryptography symmetricCryptography =  SymmetricCryptography.newInstance();
////        String key =  Base64.encodeBase64String(symmetricCryptography.initKey());
//        System.out.println("对称密钥：" + key);
//        String encryptData = Base64.encodeBase64String(symmetricCryptography.encrypt(data.getBytes(), Base64.decodeBase64(key)));
//        System.out.println("加密后数据：" + encryptData);
        
      String encryptData = encryptData(data);
      System.out.println("加密后数据：" + encryptData);
        
        String decryptData = decryptData(encryptData);
        System.out.println("解密后数据：" + decryptData);
    }
}
