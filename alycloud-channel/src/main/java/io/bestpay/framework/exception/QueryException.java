/**
 * Autogenerated by bestpay-maven-plugin
 * DO NOT EDIT DIRECTLY
 *
 * @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 *
 */

/**
 * @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */

package io.bestpay.framework.exception;

@SuppressWarnings("all")
@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(name="QueryException", namespace="io.bestpay.framework.exception")
@org.apache.avro.specific.AvroGenerated
public class QueryException extends io.bestpay.framework.base.CodeException {

	public QueryException() {
		super();
		this.init();
	}

	public QueryException(Object value, Throwable cause) {
		super(value, cause);
		this.init();
	}

	public QueryException(Object value) {
		super(value);
		this.init();
	}

	public QueryException(Throwable cause) {
		super(cause);
		this.init();
	}

	private void init() {
		this.setErrCode("000203");
		this.setErrMsg("查询错误");
	}
}