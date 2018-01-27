/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class RsaSignaturer {
	/**
	 * 对数字进行签名
	 * @param priKey
	 * @param text
	 * @return
	 */
	public static byte[] sign(byte[] priKey, String text) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(priKey);
			KeyFactory keyf = KeyFactory
					.getInstance(RsaKeyGenerater.KEY_ALGORITHM);
			PrivateKey prikey = keyf.generatePrivate(priPKCS8);

			// 用私钥对信息生成数字签名
			java.security.Signature signet = java.security.Signature
					.getInstance(RsaKeyGenerater.SIGNATURE_ALGORITHM);
			signet.initSign(prikey);
			signet.update(text.getBytes());
			byte[] signed = Base64.encodeBase64(signet.sign());
			return signed;
		} catch (java.lang.Exception e) {
			throw new RuntimeException("签名失败", e);
		}
	}

	/**
	 * 
	 * @param data
	 * @param publicKey
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, byte[] publicKey, String sign)
			throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
		KeyFactory keyFactory = KeyFactory
				.getInstance(RsaKeyGenerater.KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature
				.getInstance(RsaKeyGenerater.SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64.decodeBase64(sign.getBytes()));
	}
}
