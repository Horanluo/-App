package com.alycloud.pay.gateway.channel.util;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    
    public static void main(String[] args) {
	//	System.out.println(genSign("91be991a7491481ab43a89657a780b69", "amount=0.01&tradeType=cs.pay.submit&body=扫描订单&subject=扫描订单&outTradeNo=01&mchId=000030001000001&channel=wxPub&openId=1111111&version=1.0&currency=CNY"));
    	
    	
	}
}
