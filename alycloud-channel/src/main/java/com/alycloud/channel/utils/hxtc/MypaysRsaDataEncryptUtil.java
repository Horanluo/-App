/*
 * 类文件名:  MypaysRsaDataEncryptUtil.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.utils.hxtc;

import io.bestpay.sdk.ca.CaGenerator;
import io.bestpay.sdk.sign.RsaDataEncrypt;
import io.bestpay.sdk.sign.RsaSignBase64DataEncrypt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.IOUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MypaysRsaDataEncryptUtil {
    //私钥-加密工具
    public static RsaDataEncrypt rsaDataEncryptPri;
    //公钥-解密工具
    public static RsaDataEncrypt rsaDataEncryptPub;
    
    static {
        String appKeyPassword = "123456";
        String pubAppKeyPassword = "123456";
        //客户私钥
        InputStream storeIn = MypaysRsaDataEncryptUtil.class.getResourceAsStream("/client.pfx");
        //服务端公钥
        InputStream cerIn = MypaysRsaDataEncryptUtil.class.getResourceAsStream("/server.cer");
        ByteArrayOutputStream storeOut = new ByteArrayOutputStream();
        
        try {
            IOUtils.copy(storeIn, storeOut);
            KeyStore ks = CaGenerator.getKeyStore(CaGenerator.KEYSTORE_PKCS12, new ByteArrayInputStream(Base64.decodeBase64(storeOut.toByteArray())), pubAppKeyPassword.toCharArray());
            PrivateKey privateKey = (PrivateKey) CaGenerator.getPrivateKey(ks, appKeyPassword);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOUtils.copy(cerIn, out);
            byte[] cert = Base64.decodeBase64(out.toByteArray());
            rsaDataEncryptPri = new RsaSignBase64DataEncrypt(null, privateKey, null);
            rsaDataEncryptPub = new RsaSignBase64DataEncrypt(Map.class, CaGenerator.getCertificate(cert));
        } catch (Exception e) {//公钥或私钥加载失败
            e.printStackTrace();
        }
    }
}
