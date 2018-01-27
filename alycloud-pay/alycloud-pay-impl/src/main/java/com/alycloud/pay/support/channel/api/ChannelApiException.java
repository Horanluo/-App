package com.alycloud.pay.support.channel.api;

/**
 * 渠道接口异常
 * @author Moyq5
 * @date 2017年8月4日
 */
@SuppressWarnings("serial")
public class ChannelApiException extends Exception {

	public ChannelApiException() {
		super();
	}

	public ChannelApiException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ChannelApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChannelApiException(String message) {
		super(message);
	}

	public ChannelApiException(Throwable cause) {
		super(cause);
	}

}
