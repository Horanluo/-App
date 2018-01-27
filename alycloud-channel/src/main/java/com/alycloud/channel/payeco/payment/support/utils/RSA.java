package com.alycloud.channel.payeco.payment.support.utils;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

import javax.crypto.Cipher;

/**
 * 
 * @author Moyq5
 * @date 2017年4月25日
 */
public class RSA {

	private static final String ENCODING = "UTF-8";

	public static String signMd5(RSAPrivateKey pbk, String data) {
		return sign(pbk, data, "MD5withRSA");
	}

	public static String signSha(RSAPrivateKey pbk, String data) {
		return sign(pbk, data, "SHA256withRSA");
	}
	
	private static String sign(RSAPrivateKey pbk, String data, String algorithm) {
		try {
			Signature signet = Signature.getInstance(algorithm);
			signet.initSign(pbk);
			signet.update(data.getBytes(ENCODING));
			byte[] signed = signet.sign();
			return Base64.encodeBytes(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt64(String data, String pubKey) {
		try {
			byte[] encDate = encrypt(data, pubKey);
			return Base64.encodeBytes(encDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] encrypt(String data, String pubKey) {
		try {
			KeyFactory rsaKeyFac = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(pubKey));
			RSAPublicKey pbk = (RSAPublicKey) rsaKeyFac.generatePublic(keySpec);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, pbk);
			return cipher.doFinal(data.getBytes(ENCODING));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String signMsg, String pfxPath, String pfxPass) {
		try {
			RSAPrivateKey pbk = getPrivateKey(pfxPath, pfxPass);
			return decrypt(pbk, signMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decrypt(RSAPrivateKey pbk, String signMsg) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
			cipher.init(Cipher.DECRYPT_MODE, pbk);
			byte[] btSrc = cipher.doFinal(Base64.decode(signMsg));
			return new String(btSrc, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static boolean verifyMd5(String data, String pubKey, String sign) {
		return verify(data, pubKey, sign, "MD5withRSA");
	}

	public static boolean verifySha(String data, String pubKey, String sign) {
		return verify(data, pubKey, sign, "SHA256withRSA");
	}
	
	private static boolean verify(String data, String pubKey, String sign, String algorithm) {
		try {
			byte[] btsData = Base64.decode(data);
			byte[] btsKey = Base64.decode(pubKey);
			KeyFactory rsaKeyFac = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(btsKey);
			RSAPublicKey pbk = (RSAPublicKey) rsaKeyFac.generatePublic(keySpec);
			Signature signetcheck = java.security.Signature.getInstance(algorithm);
			signetcheck.initVerify(pbk);
			signetcheck.update(sign.getBytes(ENCODING));
			return signetcheck.verify(btsData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static RSAPrivateKey getPrivateKey(String keyPath, String passwd) throws Exception {
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(keyPath);

			char[] nPassword = null;
			if ((passwd == null) || passwd.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = passwd.toCharArray();
			}
			ks.load(fis, nPassword);
			fis.close();

			Enumeration<String> enumq = ks.aliases();
			String keyAlias = null;
			if (enumq.hasMoreElements()) {
				keyAlias = enumq.nextElement();
			}
			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			return (RSAPrivateKey) prikey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static RSAPublicKey getPublicKey(String keyPath, String passwd) throws Exception {

		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");

			FileInputStream fis = new FileInputStream(keyPath);

			char[] nPassword = null;
			if ((passwd == null) || passwd.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = passwd.toCharArray();
			}
			ks.load(fis, nPassword);
			fis.close();

			Enumeration<String> enumq = ks.aliases();
			String keyAlias = null;
			if (enumq.hasMoreElements()) {
				keyAlias = enumq.nextElement();
			}
			Certificate cert = ks.getCertificate(keyAlias);
			PublicKey pubkey = cert.getPublicKey();
			return (RSAPublicKey) pubkey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
