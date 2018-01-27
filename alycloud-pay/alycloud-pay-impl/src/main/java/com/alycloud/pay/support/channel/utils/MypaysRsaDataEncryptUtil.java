package com.alycloud.pay.support.channel.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.IOUtils;

import io.bestpay.sdk.ca.CaGenerator;
import io.bestpay.sdk.sign.RsaDataEncrypt;
import io.bestpay.sdk.sign.RsaSignBase64DataEncrypt;


/**
 * Created by Administrator on 2016/12/14.
 */
public class MypaysRsaDataEncryptUtil {
    //绉侀挜-鍔犲瘑宸ュ叿
    public static RsaDataEncrypt rsaDataEncryptPri;
    //鍏挜-瑙ｅ瘑宸ュ叿
    public static RsaDataEncrypt rsaDataEncryptPub;

    static {
        String appKeyPassword = "123456";
        String pubAppKeyPassword = "123456";
        //瀹㈡埛绉侀挜
        InputStream storeIn = MypaysRsaDataEncryptUtil.class.getResourceAsStream("/client.pfx");
        //鏈嶅姟绔叕閽?
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
        } catch (Exception e) {//鍏挜鎴栫閽ュ姞杞藉け璐?
            e.printStackTrace();
        }
    }
}
