/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import java.security.SecureRandom;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.base.SpecificRecordBase;
import io.bestpay.framework.base.SpecificRecordUtils;

/**
 * 
 * <pre>
 * Aes加解密
 * </pre>
 * 
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年11月8日
 *
 */
public class AesDataEncrypt extends Base64DataEncrypt {

	/**
	 * 密钥
	 */
	private final byte[] encryptKey;

	public AesDataEncrypt(Class<?> recordClass, String encryptKey) {
		super(recordClass);
		this.encryptKey = DigestUtils.md5(encryptKey.getBytes());
	}

	@Override
	public byte[] encrypt(SpecificRecordBase specificRecordBase) throws CodeException {
		try {
			// 1 先转成base64编码
			// 2 再进行aes加密
			byte[] data = super.encrypt(specificRecordBase);
			if (LOG.isDebugEnabled()) {
				LOG.debug("encrypt text: {}", new String(data, DEFAULT_CHARSET));
			}
			Cipher cipher = this.getCipher(Cipher.ENCRYPT_MODE);
			byte[] encrypData = cipher.doFinal(data);
			// return Base64.encodeBase64(encrypData);
			String str = parseByte2HexStr(encrypData);
			return str.getBytes(DEFAULT_CHARSET);
		} catch (Exception e) {
			throw new RuntimeException("not support AES");
		}
	}

	private String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	private byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	@Override
	public SpecificRecordBase decrypt(String data) throws CodeException {
		try {
			// 1 先解密
			// 2 再进行base64转码
			Cipher cipher = this.getCipher(Cipher.DECRYPT_MODE);
			byte[] encryptData = parseHexStr2Byte(data);
			byte[] decryptBytes = cipher.doFinal(encryptData);
			if (LOG.isDebugEnabled()) {
				LOG.debug("decrypt text: {}", new String(decryptBytes, DEFAULT_CHARSET));
			}
			byte[] text = Base64.decodeBase64(decryptBytes);
			Map map = this.parse(new String(text, DEFAULT_CHARSET));
			SpecificRecordBase specificRecord = SpecificRecordUtils.newInstance(this.getRecordClass());
			SpecificRecordUtils.parse(specificRecord, map);
			return specificRecord;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Cipher getCipher(int mode) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" ); 
        secureRandom.setSeed(this.encryptKey);  
		kgen.init(128, secureRandom);

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(mode, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		return cipher;
	}
}
