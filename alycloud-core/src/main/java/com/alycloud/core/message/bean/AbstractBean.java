package com.alycloud.core.message.bean;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import javax.swing.AbstractAction;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * <b>JavaBean�ı�ʶ��</b>
 * <p>
 * ��ʶ��,������{@link AbstractAction}��{@link AbstractService}�����á�
 * </p>
 * 
 * @author ���
 * @date 2015-2-16
 */
public abstract class AbstractBean {

	/**
	 * �׳��쳣��ʱ���¼�쳣��Ϣ
	 */
	protected Log logger = LogFactory.getLog(AbstractBean.class);

	/**
	 * <p>
	 * ����JSON�ĸ�ʽ���JavaBean�����ݡ���ʽΪ:<br/>
	 * {�ֶ���1:�ֶ�ֵ1,�ֶ���1:�ֶ�ֵ1,�ֶ���2:�ֶ�ֵ2,����}
	 * </p>
	 * <p>
	 * <font color='red'>�����JavaBean�����ں�˽���JSON��ʽת���Ļ�,����ʹ��<br/>
	 * Eclipse��toString()�Զ����ɹ��߸��Ǳ�������</font>
	 * </p>
	 * 
	 * @see Object#toString()
	 * 
	 * @return JSON��ʽ��JavaBean�ַ���
	 */
	@Override
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer info = new StringBuffer();
		info.append("{");
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			if (i > 0) {
				info.append(", ");
			}
			info.append(dumpField(field.getName(), getFieldValue(this, field)));
		}
		info.append("}");
		return info.toString();
	}

	/**
	 * <b>ͨ��������ƻ�ȡ�ֶε�ֵ</b>
	 * 
	 * @param clazz
	 *            JavaBean����
	 * @param field
	 *            �ֶ�
	 * @return �ֶ�ֵ
	 */
	private String getFieldValue(AbstractBean clazz, Field field) {
		try {
			Object obj = field.get(clazz);
			if(obj instanceof String) {
				return (String)obj;
			}
			if(obj instanceof Integer) {
				return Integer.toString((Integer)obj);
			}
			if(obj instanceof BigDecimal) {
				return ((BigDecimal)obj).toString();
			}
			return (String)obj ;
		} catch (Exception e) {
			logger.error("��ȡJavaBean[" + clazz.getClass().getName() + "]������["
					+ field.getName() + "]ʧ��", e);
			return null;
		}
	}

	/**
	 * <b>��ӡ�ֶ������ֶ�ֵ</b>
	 * <p>
	 * ����JSON�ֶεĸ�ʽ����ֶ������ֶ�ֵ,ע���������㣺
	 * </p>
	 * <li>JSON�ֶ�Ҫ��������ƺͱ���ֵ����ʹ��˫�������á�</li><br/>
	 * <li>ҳ����Ϊ�˱���˫���ŵ�Ƕ�����⣬���ڴ�ʹ����\����ת�塣</li>
	 * <p>
	 * ����: �����ֶ���Ϊname���ֶ�ֵΪadmin��������Ӧ����� name:admin��<br>
	 * ���Ǹ����������㣬������ĸ�ʽΪ:<br/>
	 * \"name\":\"admin\"
	 * </P>
	 * 
	 * @param name
	 *            �ֶ���
	 * @param value
	 *            �ֶ�ֵ
	 * @return �ֶ���:�ֶ�ֵ
	 */
	private String dumpField(String name, Object value) {
		StringBuffer info = new StringBuffer();
		info.append("\\\"");
		info.append(name);
		info.append("\\\":\\\"");
		info.append(value);
		info.append("\\\"");
		return info.toString();
	}

}
