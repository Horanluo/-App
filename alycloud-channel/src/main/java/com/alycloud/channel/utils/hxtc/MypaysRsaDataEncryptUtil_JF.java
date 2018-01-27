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
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class MypaysRsaDataEncryptUtil_JF {
    //私钥-加密工具
    public static RsaDataEncrypt jf_rsaDataEncryptPri;
    //公钥-解密工具
    public static RsaDataEncrypt jf_rsaDataEncryptPub;
    
    static {
        String appKeyPassword = "123456";
        String pubAppKeyPassword = "123456";
        //客户私钥
        InputStream jf_storeIn = MypaysRsaDataEncryptUtil_JF.class.getResourceAsStream("/jf_client.pfx");
        //服务端公钥
        InputStream jf_cerIn = MypaysRsaDataEncryptUtil_JF.class.getResourceAsStream("/jf_server.cer");
        ByteArrayOutputStream jf_storeOut = new ByteArrayOutputStream();
        
        try {
            log.info("私钥:"+jf_storeIn+",,公钥:"+jf_cerIn);
            IOUtils.copy(jf_storeIn, jf_storeOut);
            KeyStore jf_ks = CaGenerator.getKeyStore(CaGenerator.KEYSTORE_PKCS12, new ByteArrayInputStream(Base64.decodeBase64(jf_storeOut.toByteArray())), pubAppKeyPassword.toCharArray());
            PrivateKey jf_privateKey = (PrivateKey) CaGenerator.getPrivateKey(jf_ks, appKeyPassword);
            ByteArrayOutputStream jf_out = new ByteArrayOutputStream();
            IOUtils.copy(jf_cerIn, jf_out);
            byte[] jf_cert = Base64.decodeBase64(jf_out.toByteArray());
            jf_rsaDataEncryptPri = new RsaSignBase64DataEncrypt(null, jf_privateKey, null);
            jf_rsaDataEncryptPub = new RsaSignBase64DataEncrypt(Map.class, CaGenerator.getCertificate(jf_cert));
        } catch (Exception e) {//公钥或私钥加载失败
            e.printStackTrace();
        }
    }
}
