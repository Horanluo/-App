package com.alycloud.channel.pingan.support.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Moyq5
 * @date 2017年6月15日
 */
public class AES {
	private static String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	private static String KEY_ALGORITHM = "AES";

	public static String decrypt(String data, String key) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("ASCII"), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(2, skeySpec);
		byte[] encrypted1 = hex2byte(data);
		byte[] original = cipher.doFinal(encrypted1);
		return new String(original);
	}

	public static String encrypt(String data, String key) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("ASCII"), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(1, skeySpec);
		byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
		return byte2hex(encrypted);
	}

	private static byte[] hex2byte(String strhex) {
		if (strhex == null)
			return null;

		int l = strhex.length();
		if (l % 2 == 1)
			return null;

		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; ++i)
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);

		return b;
	}

	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; ++n) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}

		return hs.toUpperCase();
	}

}