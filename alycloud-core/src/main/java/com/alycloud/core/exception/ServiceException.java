package com.alycloud.core.exception;

/**
 * 自定义服务异常
 * @author Administrator
 *@author Horanluo
 */
public class ServiceException extends Exception{
	private static final long serialVersionUID = 6142104414651234850L;
	
	private String errorCode;
	private String errorMessage;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public ServiceException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public ServiceException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}
