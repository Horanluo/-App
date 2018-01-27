/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.base.SpecificRecordBase;
import io.bestpay.framework.base.SpecificRecordUtils;

public abstract class AbstractDataEncryptable implements DataEncryptable<SpecificRecordBase> {
	public static final String DEFAULT_CHARSET = SignUtils.DEFAULT_CHARSET;
	protected Logger LOG = LoggerFactory.getLogger(getClass());
	
	/**
	 * 序列化为数据
	 * @param specificRecord
	 * @return
	 */
	protected byte[] serialize(SpecificRecordBase specificRecord) {
		GenericDatumWriter<SpecificRecordBase> dw = null;
		ByteArrayOutputStream outs = null;
		Encoder encoder = null;
		try {
			dw = new GenericDatumWriter<SpecificRecordBase>(specificRecord.getSchema());
			outs = new ByteArrayOutputStream();
			encoder = EncoderFactory.get().jsonEncoder(specificRecord.getSchema(), outs);
			dw.write(specificRecord, encoder);
			encoder.flush();
			return outs.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(outs);
		}
	}
	
	/**
	 * 反序列化
	 * @param className
	 * @param data
	 * @return
	 */
	protected SpecificRecordBase deserialize(Class<?> className, String data) {
		GenericDatumReader<SpecificRecordBase> userDatumReader = null;
		Decoder decoder = null;
	    try {
	    	SpecificRecordBase entity = SpecificRecordUtils.newInstance(className);
	    	userDatumReader = new GenericDatumReader<SpecificRecordBase>(entity.getSchema());
	    	decoder = DecoderFactory.get().jsonDecoder(entity.getSchema(), new ByteArrayInputStream(data.getBytes(DEFAULT_CHARSET)));
	    	userDatumReader.read(entity, decoder);
	    	return entity;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
		}
	}
	
	/**
	 * 获取加密字符串
	 * @param specificRecord
	 * @return
	 */
	private final byte[] getSortString(SpecificRecordBase specificRecord) {
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
	
	protected Map<String, String> parse(String data) {
		try {
			String str = data;
			String[] arr = str.split("&");
			Map<String, String> map = new HashMap<String, String>();
			for (String s : arr) {
				String[] subarr = s.split("=");
				String key = subarr[0];
				String value = subarr[1];
				map.put(key, value);
			}
			return Collections.unmodifiableMap(map);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected <T> T parse(Class<T> className, String data) throws CodeException {
		Map map = this.parse(data);
		SpecificRecordBase specificRecord = SpecificRecordUtils.newInstance(className);
		return (T)SpecificRecordUtils.parse(specificRecord, map);
	}
	
	/**
	 * 将字段转成字符串
	 * @param field
	 * @param value
	 * @return
	 */
	protected String getString(Field field, Object value) {
		return String.valueOf(value);
	}
}
