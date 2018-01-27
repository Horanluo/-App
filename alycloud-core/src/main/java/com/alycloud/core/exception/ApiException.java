package com.alycloud.core.exception;

/**
 * 开放接口异常类
 * @author Moyq5
 * @date 2017年9月27日
 */
@SuppressWarnings("serial")
public class ApiException extends Exception {

	private ApiCode code;
	public ApiException(ApiCode code) {
		super(code.getText());
		this.code = code;
	}
	public ApiCode getCode() {
		return code;
	}
}
