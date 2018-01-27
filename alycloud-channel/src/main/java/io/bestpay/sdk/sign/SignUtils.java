/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.TreeMap;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.bestpay.framework.base.SpecificRecordBase;

public class SignUtils {
	public static final String DEFAULT_CHARSET = "UTF-8";
	private static Logger LOG = LoggerFactory.getLogger(SignUtils.class);
	
	public static byte[] getSortString(SpecificRecordBase specificRecord) {
		TreeMap<String, String> mapper = new TreeMap<String, String>();
		Schema schema = specificRecord.getSchema();
		List<Field> fields = schema.getFields();
		for (Field f : fields) {
			Object value = specificRecord.get(f.name());
			if (null != value) {
				mapper.put(f.name(), getString(f, value));
			}
			// mapper.put(f.name(), value)
		}
		StringBuffer sb = new StringBuffer();
		for (String key : mapper.keySet()) {
			
			Object value = mapper.get(key);
			if (null == value || "".equals(value)) {
				continue;
			}
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(String.format("%s=%s", key, mapper.get(key)));
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("encrypt string {}", sb.toString());
		}
		try {
			return sb.toString().getBytes(DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected static String getString(Field field, Object value) {
		return String.valueOf(value);
	}
	
}
