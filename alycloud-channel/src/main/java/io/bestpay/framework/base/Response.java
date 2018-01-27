package io.bestpay.framework.base;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 响应请求封装类
 * @author <a href="mailto:hadoop@139.com">wangqt</a>
 * @createAt 2016年9月18日
 * @param <T>
 */
@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(name="Response", namespace="io.bestpay.framework.base")
public class Response<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@javax.xml.bind.annotation.XmlElement(name="success")
	private boolean success;
	@javax.xml.bind.annotation.XmlElement(name="message")
	private String message;
	@javax.xml.bind.annotation.XmlElement(name="data")
	private T data;
	public Response(boolean success, String message, T data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}
	
	@com.fasterxml.jackson.annotation.JsonGetter("success")
  	@org.codehaus.jackson.annotate.JsonProperty("success")
	@com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
	@org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)
	@javax.xml.bind.annotation.XmlTransient
	public boolean isSuccess() {
		return success;
	}

	@com.fasterxml.jackson.annotation.JsonGetter("message")
  	@org.codehaus.jackson.annotate.JsonProperty("message")
	@com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
	@org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)
	@javax.xml.bind.annotation.XmlTransient
	public String getMessage() {
		return message;
	}

	@com.fasterxml.jackson.annotation.JsonGetter("data")
  	@org.codehaus.jackson.annotate.JsonProperty("data")
	@com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
	@org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)
	@javax.xml.bind.annotation.XmlTransient
	public T getData() {
		return data;
	}


	/**
	 * 创建成功的消息返回流
	 * @param data
	 * @return
	 */
	public static <T> Response<T> success(T data) {
		return new Response<T>(true, "success", data);
	}
	
	/**
	 * 创建失败的消息返回流
	 * @param message
	 * @param infomation
	 * @return
	 */
	public static <T> Response<T> failure(String message, T infomation) {
		return new Response<T>(false, message, infomation);
	}
	/**
	 * 创建失败的消息返回流
	 * @param message
	 * @return
	 */
	public static <T> Response<T> failure(String message) {
		return new Response<T>(false, message, null);
	}

	public Response() {
		super();
	}

	protected void setSuccess(boolean success) {
		this.success = success;
	}

	protected void setMessage(String message) {
		this.message = message;
	}

	protected void setData(T data) {
		this.data = data;
	}
}
