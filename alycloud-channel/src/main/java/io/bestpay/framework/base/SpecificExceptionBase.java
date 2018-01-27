/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.base;
/**
 * 
 * <pre>
 * 	基本异常类
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年9月17日 
 *
 */
@javax.xml.bind.annotation.XmlType(name="SpecificExceptionBase", namespace="io.bestpay.framework.base")
public abstract class SpecificExceptionBase extends org.apache.avro.specific.SpecificExceptionBase {

	public SpecificExceptionBase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SpecificExceptionBase(Object value, Throwable cause) {
		super(value, cause);
		// TODO Auto-generated constructor stub
	}

	public SpecificExceptionBase(Object value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	public SpecificExceptionBase(Throwable value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

}
