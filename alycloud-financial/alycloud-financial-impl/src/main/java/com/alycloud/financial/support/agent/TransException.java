package com.alycloud.financial.support.agent;

/**
 * 分润异常抽象类
 * @author Moyq5
 * @date 2017年11月6日
 */
@SuppressWarnings("serial")
public class TransException extends Exception {

	public TransException() {
		super();
	}

	public TransException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TransException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransException(String message) {
		super(message);
	}

	public TransException(Throwable cause) {
		super(cause);
	}

}
