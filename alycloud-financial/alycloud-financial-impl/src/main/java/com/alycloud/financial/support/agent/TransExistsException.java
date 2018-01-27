package com.alycloud.financial.support.agent;

/**
 * 分润已存在异常
 * @author Moyq5
 * @date 2017年11月6日
 */
@SuppressWarnings("serial")
public class TransExistsException extends TransException {

	public TransExistsException() {
		super();
	}

	public TransExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TransExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransExistsException(String message) {
		super(message);
	}

	public TransExistsException(Throwable cause) {
		super(cause);
	}

}
