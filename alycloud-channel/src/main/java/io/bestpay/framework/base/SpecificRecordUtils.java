package io.bestpay.framework.base;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;

public class SpecificRecordUtils {

	/**
	 * 组装 SpecificRecordBase数据 
	 * 将map的对像数据设置至SpecificRecordBase
	 * @param specificRecord
	 * @param map
	 * @return
	 * @throws BaseException 
	 */
	public static <T extends SpecificRecordBase> T parse(T specificRecord, Map<String, Object> map) throws CodeException {
		Schema schema = specificRecord.getSchema();
		for (String key : map.keySet()) {
			Field field = schema.getField(key);
			if (null != field) {
				convert(specificRecord, field, map.get(key));
			}
		}
		return specificRecord;
	}
	
	/**
	 * 将一个的属性复制至另一个对像
	 * @param srcRecord
	 * @param destRecord
	 */
	public static void copy(SpecificRecordBase srcRecord, SpecificRecordBase destRecord) {
		List<Field> fields = destRecord.getSchema().getFields();
		for (Field f : fields) {
			Object value = srcRecord.get(f.name());
			if (null != value && !"".equals(value)) {
				destRecord.put(f.name(), value);
			}
		}
	}
	
	private static void convert(SpecificRecordBase sr, Field field, Object mapValue) throws CodeException {
		Object value = mapValue;
		if (null == value) {
			value = field.defaultVal();
		}
		if (null == value) {
			return;
		}
		
		Schema schema = field.schema();
		String fieldName = field.name();
		switch (schema.getType()) {
		case STRING:
			sr.put(fieldName, String.valueOf(value));
	    	break;
	    case INT:
	    	sr.put(fieldName, Integer.parseInt(String.valueOf(value)));
	    	break;
	    case LONG:
	    	sr.put(fieldName, Long.parseLong(String.valueOf(value)));
	    	break;
	    case FLOAT:
	    	sr.put(fieldName, Float.parseFloat(String.valueOf(value)));
	    	break;
	    case DOUBLE:
	    	sr.put(fieldName, Double.parseDouble(String.valueOf(value)));
	    	break;
	    case BOOLEAN: 
	    	sr.put(fieldName, Boolean.parseBoolean(String.valueOf(value)));
	    	break;
	    case ENUM:
	    	try {
				Class cls = Class.forName(field.schema().getFullName());
				Enum ex = Enum.valueOf(cls, String.valueOf(value));
				sr.put(fieldName, ex);
			} catch (Exception e) {
				// IGNORE
			}
			break;
	    case BYTES:
	    	try {
	    		byte[] data = null;
	    		if (value instanceof String) {
	    			data = String.valueOf(value).getBytes();
	    		} else if (value instanceof ByteBuffer) {
	    			data = ((ByteBuffer)value).array();
	    		} else {
	    			data = (byte[])value;
	    		}
				if (ByteBuffer.class.getName().equals(field.schema().getFullName())) {
					sr.put(fieldName, ByteBuffer.wrap(data));
				} else {
					sr.put(fieldName, data);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	break;
	    default: 
	    	sr.put(fieldName, value);
	    }
	}
	
	/**
	 * 实例化对像
	 * @param className
	 * @return
	 */
	public static SpecificRecordBase newInstance(Class<?> className) {
		try {
			return (SpecificRecordBase) className.newInstance();
		} catch (Exception e) {
			throw new ClassCastException(className.getName() + " to " + SpecificExceptionBase.class.getName());
		}
	}
	
	/**
	 * 拷贝
	 * @param className
	 * @param record
	 * @return
	 */
	public static SpecificRecordBase newInstanceAndCopy(Class<?> className, SpecificRecordBase record) {
		SpecificRecordBase rb = newInstance(className);
		copy(record, rb);
		return rb;
	}
	
	/**
	 * 将数据转化成map
	 * @param record
	 * @return
	 */
	public static Map<String, String> toMap(SpecificRecordBase record) {
		Map<String, String> map = new HashMap<String, String>();
		for (Field f : record.getSchema().getFields()) {
			Object value = record.get(f.name());
			if (null != value) {
				if (value instanceof ByteBuffer) {
					ByteBuffer bb = (ByteBuffer)value;
					map.put(f.name(), new String(bb.array()));
				} else if (value instanceof byte[]) {
					map.put(f.name(), new String((byte[])value));
				} else {
					map.put(f.name(), String.valueOf(value));
				}
			}
		}
		return map;
	}
}
