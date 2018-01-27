/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

public class RsaKeyGenerater {
	/**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
    
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	
	private byte[] priKey;
	private byte[] pubKey;

	private String createSeed()
	{
		Random random = new Random(System.nanoTime());
		return System.nanoTime() + "_"
				+ random.nextInt(Integer.MAX_VALUE);
	}

	public void generater()
	{
		try
		{
			java.security.KeyPairGenerator keygen = java.security.KeyPairGenerator
					.getInstance(KEY_ALGORITHM);
			SecureRandom secrand = new SecureRandom();
			secrand.setSeed(this.createSeed().getBytes()); // 初始化随机产生器
			keygen.initialize(1024, secrand);
			KeyPair keys = keygen.genKeyPair();

			PublicKey pubkey = keys.getPublic();
			PrivateKey prikey = keys.getPrivate();

			pubKey = pubkey.getEncoded();
			priKey = prikey.getEncoded();

			// System.out.println("pubKey = " + new String(pubKey));
			// System.out.println("priKey = " + new String(priKey));
		} catch (java.lang.Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public byte[] getPriKey()
	{
		return priKey;
	}

	public byte[] getPubKey()
	{
		return pubKey;
	}
	
	public byte[] getBase64PriKey()
	{
		return Base64.encodeBase64(this.priKey);
	}

	public byte[] getBase64PubKey()
	{
		return Base64.encodeBase64(pubKey);
	}
	
	public RSAPublicKey getPublicKey() throws Exception{  
	    X509EncodedKeySpec spec = new X509EncodedKeySpec(this.pubKey);  
	    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	    return (RSAPublicKey) keyFactory.generatePublic(spec);  
	}  
	  
	public RSAPrivateKey getPrivateKey() throws Exception{  
	    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(this.priKey);  
	    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	    return (RSAPrivateKey) keyFactory.generatePrivate(spec);  
	}
}
