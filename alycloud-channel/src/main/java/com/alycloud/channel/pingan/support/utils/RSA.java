package com.alycloud.channel.pingan.support.utils;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSA {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
	
	public static void main(String[] args) {
		try {
			String si = sign("data=2C8F023A0AF9D4CB20C9B1133B9E4608C4233BF9B74C67C7694DE840CCE8C577876186E4452DA8FC57DBA9CE8BC0F3CEC876A636A29246BECF18C1FD8136C71D27169AD7961D214192F6491CA8C51599E334F2B9D1169C02C297DC23C7358B0621AE54D9ACD6BA3518010AE5CF6CD058818CB7E066CC35F4F1D5031081DF60CE36F82BC678599759F30679A2B8487D50CB76BE277C0336E32F2F5D77C4280BC34958C9B09A71F8A149933353D07001802FFBD89DC64B806A19E12FEED43EB9FF&open_id=73b24f53ffc64486eb40d606456fb04d&sign_type=RSA&timestamp=1499676291".getBytes("UTF-8"), 
					"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDoYLKYSup2POAgmf2iHwGhUQZSeyyxLvslDcFCRekg1D7w+Y8dybhBOyDvlTbuG15jt8vqqH1xlGTI66yz351uxrUL10UL6Uw+kx1091/BCiRo1AY9frOlIeTnVZy/S2LPWjEefI9o0nOBYJkoJZsaFffX6qXVFkCfAGNMohyuU0gNHw3SXXMBiJ2n2E7GfFf/peAmtTg4zFJO+CrikfSTrL0u5Y2dWQp1KQvkfCMrXaECM24fLc6xxwAdiARC6DjMK8F+ZxovEgb0VObpbZ5pnV2XrKEbiDXQ/DbeNTmELHN8k0S3NUY6ozpu1M3JDqZPhBIDgd/gcZknFngCRJ95AgMBAAECggEAXZYkF0WEq93UfgzGozZNl8RkAW/uDeXX65JglOpG+5u/RZmcU+jbthm0KAk2OCr5lrt8+qKk8stK08hmo4KZivWoEH7AJg3tUP46zNKb08jb5QQPB1Ex1H2UDL7kA/6+arfuNFMCBrtLHX3j8NFEZ/sU9/ZelzUBDYhAdaqMVoAbfOX7MIZMRIZRhrZCuQlZI0Wz1oGzOf4WB40HEvgRfkGkdC2C+co9tCK7PRcJCiAnSro175E8NLmYIs+0yUtztUUPSUcVmZ4NfQzM/tD7oyeHREkmBEM6NT62U2PAHyhdFJH23T5Rq80u2Qbzpqv6Fk9aNlBM5uN24umVU7C2oQKBgQD0Hyw7SKOGExbBOOyJFLpeISsiACokPz7rGwgQJfNfGrbnBLuo7VyxRAX9y01FQrsZL0ZqZ09dSnY6KqjmMzUQdtF1mteiF9wC6xHcIN9oXzDtDc0geB/hb3zLRIHs5yrCgNci8FjGL0OwBaNNz0vLa02C8Vm5P6rjlclyjK7ZtQKBgQDzrz0i+mCgklD6+cbEA3Z+0b75s5Lg9cu13eBntZ/+11d2Kf1JCrgrCiRcX1ggOG2ZHmPmi5cs93J6rd+HWInPWJ+j4GUYN5wPS8GxiQun42b3FloT5Zoe4Cj3TmonIHvhFQojYAniKPx3jb3mCLOUyHWjIc5r9zE6Tovth195NQKBgE7k9CqEozRlXuk7OFZk+IYLOiFW5EeqmO7qYYS2fxyxSYMHqI5Dh71SOo128pX7pvPQr3Ubxi5kLilGOCeNTQzxGWhkjmO4SkY3KiJ2DT1x5iH2X+CqccMtgKtAjKy/WLZbZSvJeSczhzCP4eL3p4sqNnanAVQ5G0VJ1zzJ8ogxAoGAA+4inUrOfih9995Jb2Xi5l65pstXphswwukmMmYCg5izh2tb826h08fhGEBNao+ebObJk7FSqd3/0ay2OzeZWWfDg2AeIUrcUH7XS+a68mU/huKsZz+/wZm572srWSAz/0hYloN5BVXF5KO7mVcwlki5ZP0pmCIvgBI+PYF+b7UCgYEA3WpyaYgDSVrE2pcF4+dvqFNIDGcFBGqCyeCaGx4E1WuP0R7y1SFM2miGnbl+te89ZLSzSudoeFq/qS8S3W+M+PuQJlmlFnQ+B/DaEJmKGiiNAqWT9cdohLeYNaJBvk55MdnRJl4dAfkf5PFIf64CWvdDEvhehuV6kub3i8KEvH8=");
			System.out.println(si);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String sign(byte[] data, String privateKey) throws Exception {

		byte[] keyBytes = BASE64.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);
		return byte2hex(signature.sign());
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
		return hs;
	}

}
