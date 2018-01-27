/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.compress.utils.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.mortbay.log.Log;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.base.SpecificRecordBase;
import io.bestpay.framework.exception.SignInValidException;
import io.bestpay.sdk.ca.CaGenerator;
import io.bestpay.sdk.util.ByteUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 	rsa方式加解密
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年11月14日 
 *
 */
@Slf4j
public class RsaDataEncrypt extends Base64DataEncrypt {

	static {
		// 初始化实现类
		Security.addProvider(new BouncyCastleProvider());
	}
	static final String KEY_ALGORITHM = CaGenerator.KEY_ALGORITHM;
	/** *//** 
     * 签名算法 
     */  
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	// static final int MAX_ENCRYPT_BLOCK = 245;
	// static final int MAX_DECRYPT_BLOCK = 256;
    static final int MAX_ENCRYPT_BLOCK = 117;
	static final int MAX_DECRYPT_BLOCK = 128;
	
	private final byte[] publicKey;
	private final PrivateKey privateKey;
	
	public RsaDataEncrypt(Class<?> recordClass, PrivateKey privateKey, byte[] publicKey) {
		super(recordClass);
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
	public RsaDataEncrypt(Class<?> recordClass, Certificate cert) {
		this(recordClass, null, cert.getPublicKey().getEncoded());
	}
	
	/**
	 * 用私钥对信息生成数字签名 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String sign(byte[] data) throws Exception {
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(this.privateKey.getEncoded());
		log.info("私钥:"+pkcs8KeySpec.toString());
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initSign(privateK);  
        signature.update(data);  
        return new String(Base64.encode(signature.sign()), DEFAULT_CHARSET);
	}
	
	/**
	 * 校验数字签名
	 * @param data
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public boolean verify(byte[] data, String sign) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(this.publicKey);  
		log.info("公钥:"+keySpec.toString());
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PublicKey publicK = keyFactory.generatePublic(keySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initVerify(publicK);  
        signature.update(data);  
        boolean result = signature.verify(Base64.decode(sign.getBytes(DEFAULT_CHARSET)));  
        if (!result) {
        	throw new SignInValidException();
        }
        return result;
	}

	@Override
	public byte[] encrypt(SpecificRecordBase specificRecordBase) throws CodeException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte[] data = this.serialize(specificRecordBase);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(this.privateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
			Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm(), BouncyCastleProvider.PROVIDER_NAME);
			cipher.init(Cipher.ENCRYPT_MODE, this.privateKey);
			
			int length = data.length;
			int offset = 0;
			int i = 0;
			while ((length - offset) > 0) {
				int end = (length - offset);
				if (length - offset > MAX_ENCRYPT_BLOCK) {
					end = MAX_ENCRYPT_BLOCK;
				}
				
				byte[] encdata = cipher.doFinal(data, offset, end);
				out.write(encdata, 0, encdata.length);
				offset = (MAX_ENCRYPT_BLOCK) * ++i;
			}
			byte[] encdata = out.toByteArray();
			// return Base64.encode(encdata);
			return ByteUtils.byte2HexStr(encdata).getBytes(DEFAULT_CHARSET);
		} catch (Exception e) {
			throw new RuntimeException("加密错误", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	public SpecificRecordBase decrypt(String encryptData) throws CodeException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			// byte[] data = Base64.decode(encryptData.getBytes());
			byte[] data = ByteUtils.hexStr2Bytes(encryptData);
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(this.publicKey);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
			
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm(), BouncyCastleProvider.PROVIDER_NAME);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			
			int length = data.length;
			int offset = 0;
			int i= 0;
			while ((length - offset) > 0) {
				int end = (length - offset);
				if (length - offset > MAX_DECRYPT_BLOCK) {
					end = MAX_DECRYPT_BLOCK;
				}
				
				byte[] encdata = cipher.doFinal(data, offset, end);
				out.write(encdata, 0, encdata.length);
				offset = (MAX_DECRYPT_BLOCK) * ++i;
			}
			return (SpecificRecordBase) this.deserialize(this.getRecordClass(), new String(out.toByteArray(), DEFAULT_CHARSET));
		} catch (Exception e) {
			throw new RuntimeException("解密错误", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

}
