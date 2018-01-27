package com.alycloud.pay.gateway.channel.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 商户可参考本类编写获取请求参数的方法，也可直接使用本类
 *
 */

public class RequestUtils {
	
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getRequestMapValue(Map paramMap){
		
		Map<String, String> retMap = new HashMap<String, String>(paramMap.size());
		
		for(Iterator iter = paramMap.entrySet().iterator();iter.hasNext();){ 
	        Map.Entry element = (Map.Entry)iter.next(); 
	        String strKey = (String)element.getKey(); 
	        String[] strObj = (String[])element.getValue(); 
	        if(strObj != null){
	        	retMap.put(strKey, (String)strObj[0]);
	        }
		}
		
		return retMap;
	}

}
