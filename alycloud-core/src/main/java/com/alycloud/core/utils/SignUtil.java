package com.alycloud.core.utils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

/**
 * 商户可参考本类编写加密和验签的方法，也可直接使用本类
 *
 */
@Slf4j
public class SignUtil {

	public static final String TF_TIME_PARAM = "tf_timestamp";

	private static final String TF_SIGN_PARAM = "tf_sign";

	private static final String TF_DOG_SK = "dog_sk";
	
	public static Map<String, String> paraFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>();

		if (sArray == null || sArray.size() <= 0) {
			return result;
		}
		DecimalFormat formater = new DecimalFormat("###0.00");
		DecimalFormat formater2 = new DecimalFormat("###0.0000");
		for (String key : sArray.keySet()) {
			String finalValue = null;
			Object value = sArray.get(key);
			if(value instanceof BigDecimal){
				finalValue = formater2.format(value);
			}else if(value instanceof Double){
			    finalValue = formater.format(value);
			}else {
				finalValue =  String.valueOf(value);
			}
			if (value == null || value.equals("")
					|| key.equalsIgnoreCase("sign")) {
				continue;
			}
			result.put(key, finalValue);
		}

		return result;
	}

	public static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys,String.CASE_INSENSITIVE_ORDER);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	public static String genSign(String key,String str){
		return md5(str+"&key="+key).toUpperCase();
	}
	
    public static String md5(String plainText) {
        try {
            return Encodes.encodeHex(Digests.md5(new ByteArrayInputStream(plainText.getBytes("utf-8"))));
        } catch (Exception ex) {
            return "";   
        }
    }
    
    public static boolean validSign(Map<String, String> map,String key){
        if(map.get("amount") != null)
        {
            String amountResult = String.valueOf(map.get("amount"));
            DecimalFormat formater = new DecimalFormat("###0.00");
            map.put("amount",formater.format(new BigDecimal(amountResult)));
        }
    	String oldSign = map.get("sign");
    	String sign = genSign(key, createLinkString(paraFilter(map)));
    	log.info("oldSign is : {}, sign is : {}",oldSign,sign);
    	return sign.equalsIgnoreCase(oldSign);
    }
    
    /**
	 * 签名字符串
	 * 
	 * @param params
	 * @param key
	 * @param charset
	 * @return
	 */
	public static String proxySign(Map<String, String> params, String key, String charset) {
		// 1. tf_sign不加入签名
		params.remove(TF_SIGN_PARAM);
		// 2. 加入sk
		params.put(TF_DOG_SK, key);
		// 3. 对请求参数的值进行排序
		String keyString = createProxyLinkString(params);
		//System.out.println(keyString);
		// 4. 生成md5
		return generateMd5(keyString, charset);
	}
	
	/**
	 * 把数组所有元素排序，并参数值拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createProxyLinkString(Map<String, String> params) {
		Object[] keys = params.keySet().toArray();
		Arrays.sort(keys);
		StringBuffer sb = new StringBuffer();
		for (int i = keys.length - 1; i >= 0; i--) {
			sb.append(params.get(keys[i]));
		}
		return sb.toString();
	}
	
	/**
	 * 生成md5
	 *
	 * @param charset
	 * @return
	 */
	public static String generateMd5(String keyString, String charset) {
		String result = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(keyString.getBytes(charset));
			byte[] temp;
			temp = md5.digest("".getBytes(charset));
			for (int i = 0; i < temp.length; i++) {
				result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
			}
			result = result.toUpperCase();
			//System.out.println(result);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5签名过程中出现错误" + e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
		return result;
	}
	
	/**
	 * 校验签名
	 * 
	 * @param params
	 * @param sign
	 * @param key
	 * @param charset
	 * @return
	 */
	public static boolean proxyVerify(Map<String, String> params, String sign, String key, String charset) {
		// 1. tf_sign不加入签名
		params.remove(TF_SIGN_PARAM);
		// 2. 加入sk
		params.put(TF_DOG_SK, key);
		String keyString = createLinkString(params);
		System.out.println("verify | 拼装结果createLinkString:"+keyString);
		// 3. 生成md5
		String md5 = generateMd5(keyString, charset);
		System.out.println("verify | 生成generateMd5:"+md5);
		if (md5.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}
	
    public static void main(String[] args) {
	//	System.out.println(genSign("91be991a7491481ab43a89657a780b69", "amount=0.01&tradeType=cs.pay.submit&body=扫描订单&subject=扫描订单&outTradeNo=01&mchId=000030001000001&channel=wxPub&openId=1111111&version=1.0&currency=CNY"));
    	
    	
	}
}
