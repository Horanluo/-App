package com.alycloud.financial.support.payment;

/**
 * 代付异常抽象类
 * @author Moyq5
 * @date 2017年8月4日
 */
@SuppressWarnings("serial")
public class PaymentException extends Exception {

	public PaymentException() {
		super();
	}

	public PaymentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PaymentException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentException(String message) {
		super(message);
	}

	public PaymentException(Throwable cause) {
		super(cause);
	}

}
