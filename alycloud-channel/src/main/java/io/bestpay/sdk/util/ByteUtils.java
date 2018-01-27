package io.bestpay.sdk.util;

public class ByteUtils {
	/**
	 *字节转换成16进制
	 * @param b
	 * @return
	 */
	public static String byte2HexStr(byte[] b) {  
	    String hs = "";  
	    String stmp = "";  
	    for (int n = 0; n < b.length; n++) {  
	        stmp = (Integer.toHexString(b[n] & 0XFF));  
	        if (stmp.length() == 1)  
	            hs = hs + "0" + stmp;  
	        else  
	            hs = hs + stmp;  
	    }  
	    return hs.toUpperCase();  
	}
	
	private static byte uniteBytes(String src0, String src1) {  
	    byte b0 = Byte.decode("0x" + src0).byteValue();  
	    b0 = (byte) (b0 << 4);  
	    byte b1 = Byte.decode("0x" + src1).byteValue();  
	    byte ret = (byte) (b0 | b1);  
	    return ret;  
	}  
	  
	/** 
	 * bytes转换成十六进制字符串 
	 */  
	public static byte[] hexStr2Bytes(String src) {  
	    int m = 0, n = 0;  
	    int l = src.length() / 2;  
	    System.out.println(l);  
	    byte[] ret = new byte[l];  
	    for (int i = 0; i < l; i++) {  
	        m = i * 2 + 1;  
	        n = m + 1;  
	        ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));  
	    }  
	    return ret;  
	}
}
