/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */

/**
 * @Copyright 2017 www.bestpay.io Inc. All rights reserved.
 */

package io.bestpay.framework.base;
@SuppressWarnings("all")
/** 异常类型 */
@org.apache.avro.specific.AvroGenerated
public enum ExceptionType {
  PLATFORM, SERVICE, CHANNEL  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"ExceptionType\",\"namespace\":\"io.bestpay.framework.base\",\"doc\":\"异常类型\",\"symbols\":[\"PLATFORM\",\"SERVICE\",\"CHANNEL\"],\"aliases\":[\"ExceptionType\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /**   */
  public static ExceptionType valueOf(int ordinal) {
	  ExceptionType[] values = ExceptionType.values();
	  for (ExceptionType s : values) {
		  if (s.ordinal() == ordinal) {
			  return s;
		  }
	  }
	  throw new org.apache.avro.AvroRuntimeException("Bad ordinal");
  }  
}
