/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.http;

import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractDataSignable {

	protected String signMap(Map<String, String> data) {
		TreeMap<String, String> treeMap = new TreeMap<String, String>(data);
		StringBuffer sb = new StringBuffer();
		for (String key : treeMap.keySet()) {
			String value = treeMap.get(key);
			if (null == value || "".equals(value)) {
				continue;
			}
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(String.format("%s=%s", key, treeMap.get(key)));
		}
		return this.signString(sb.toString());
	}
	
	protected abstract String signString(String str);
}
