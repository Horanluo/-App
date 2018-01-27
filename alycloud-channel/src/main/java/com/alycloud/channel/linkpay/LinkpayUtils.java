package com.alycloud.channel.linkpay;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.linkpay.bean.CommonData;
import com.alycloud.channel.linkpay.bean.CommonResult;
import com.alycloud.channel.linkpay.bean.Result;
import com.alycloud.channel.linkpay.enums.Code;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.Config;
import com.alycloud.channel.linkpay.support.utils.Base64;
import com.alycloud.channel.linkpay.support.utils.JSON;
import com.alycloud.channel.linkpay.support.utils.RSA;

public abstract class LinkpayUtils {

	private static final String CHARSET = "UTF-8";
	private static Logger log = LoggerFactory.getLogger(LinkpayUtils.class);
	
	public static CommonData buildCommonData(Service service) {
		Config cfg = LinkpayFactory.getConfig();
		CommonData data = new CommonData();
		data.setDatetime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		data.setGroupId(cfg.getGroupId());
		data.setService(service);
		data.setSignType("RSA");
		return data;
	}
	
	public static String toQueryString(CommonData com, Object data) throws Exception {
		Config cfg = LinkpayFactory.getConfig();
		String signData = toQueryString(data);
		log.debug("加密内容：{}", signData);
		byte[] signBys = RSA.encryptByPublicKey(signData.getBytes(CHARSET), cfg.getPublicKey());
		String sign = Base64.encodeBytes(signBys);
		log.debug("加密结果：{}", sign);
		com.setSign(sign);
		
		return toQueryString(com);
	}
	
	public static <R> Result<R> toResultObject(String jsonData, Class<R> clazz) throws Exception {
		Result<R> result = new Result<R>();
		Config cfg = LinkpayFactory.getConfig();
		CommonResult comResult = JSON.toObject(jsonData, CommonResult.class);
		result.setCommonResult(comResult);
		if (comResult.getCode() == Code.C0000) {
			
			log.debug("解密内容：{}", comResult.getSign());
			byte[] decodedBys = Base64.decode(comResult.getSign());
			byte[] detailBys = RSA.decryptByPublicKey(decodedBys, cfg.getPublicKey());
			
			String detailStr = new String(detailBys);
			log.debug("解密结果：{}", detailStr);
			
			R detail = toObject_(detailStr, clazz);
			result.setDetailResult(detail);
			
		} else {
			log.warn("错误码[{}：{}]", comResult.getCode(), comResult.getMessage());
		}
		return result;
	}
	
	private static String toQueryString(Object obj) {
		log.debug("将对象组装成get参数格式字符串");
		log.debug("源对象：{}", obj.getClass().getName());
		List<String> names = getSortedFields(obj.getClass());
		StringBuffer sb = new StringBuffer();
		String value;
		Method method;
		for(String name : names) {
			try {
				method = obj.getClass().getMethod("get" + 
						name.substring(0, 1).toUpperCase() + name.substring(1));
				value = String.valueOf(method.invoke(obj)).trim();
				if (value.contentEquals("null")) {
					value = "";
				} /*else if (name.contentEquals("notifyUrl")) {
					value = URLEncoder.encode(value, CHARSET);
				}*/
				sb.append(name + "=" + value + "&");
			} catch(Exception e) {
				log.warn("数据转换失败：{}", e.getMessage());
			}
		}
		if (names.size() > 0) {
			sb.delete(sb.length()-1, sb.length());// 去掉最后的“&”
		}
		log.debug("组装结果：{}", sb.toString());
		return sb.toString();
	}
	
	private static List<String> getSortedFields(Class<?> clazz) {
		List<String> names = getFields(null, clazz);
		Collections.sort(names);
		return names;
	}
	
	private static List<String> getFields(List<String> names, Class<?> clazz) {
		if (null == names) {
			names = new ArrayList<String>();
		}
		if (clazz == Object.class) {
			return names;
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			names.add(field.getName());
		}
		return getFields(names, clazz.getSuperclass());
	}

	private static <R> R toObject_(String queryString, Class<R> clazz) {
		log.debug("将get参数格式字符串转换成对象");
		log.debug("源字符串：{}", queryString);
		log.debug("目标对象：{}", clazz.getName());
		R result = null;
		try {
			String[] paramInfos = queryString.split("\\&");
			String[] oneParam;
			StringBuffer sb = new StringBuffer("{");
			for (String paramInfo : paramInfos) {
				if (paramInfo.isEmpty()) {
					continue;
				}
				oneParam = paramInfo.split("\\=", 2);
				if (oneParam[1].isEmpty()) {
					continue;
				}
				sb.append("\"");
				sb.append(oneParam[0]);
				sb.append("\":\"");
				sb.append(oneParam[1].replaceAll("\"", "\\\\\""));
				sb.append("\",");
			}
			if (paramInfos.length > 0) {
				sb.delete(sb.length()-1, sb.length());// 去掉最后的“,”
			}
			sb.append("}");
			result = JSON.toObject(sb.toString(), clazz);
		} catch (Exception e) {
			log.error("数据转换异常：", e);
		}
		return result;
	}
}
