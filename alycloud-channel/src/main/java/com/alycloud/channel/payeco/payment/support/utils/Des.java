package com.alycloud.channel.payeco.payment.support.utils;

import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author Moyq5
 * @date 2017年4月25日
 */
public class Des {

	private static final String Algorithm = "DESede"; // 定义 加密算法,可用
														// DES,DESede,Blowfish
	private static final String ENCODING = "UTF-8";

	public static String encrypt(String keyByte, String src) {
		try {
			SecretKey deskey = new SecretKeySpec(Base64.decode(keyByte), Algorithm);
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] bts = c1.doFinal(src.getBytes(ENCODING));
			return Base64.encodeBytes(bts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String keyByte, String src) {
		try {
			SecretKey deskey = new SecretKeySpec(Base64.decode(keyByte), Algorithm);
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			byte[] bts = c1.doFinal(Base64.decode(src));
			return new String(bts, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getPubKey(String path) {
		String pubKey = null;
		try {
			InputStream streamCert = new java.io.FileInputStream(path);
			CertificateFactory factory = CertificateFactory.getInstance("X.509");
			Certificate cert = factory.generateCertificate(streamCert);
			pubKey = Base64.encodeBytes(cert.getPublicKey().getEncoded());
			streamCert.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pubKey;
	}

}