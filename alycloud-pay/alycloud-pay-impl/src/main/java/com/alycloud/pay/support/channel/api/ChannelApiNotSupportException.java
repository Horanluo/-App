package com.alycloud.pay.support.channel.api;

/**
 * 渠道不支持接口
 * @author Moyq5
 * @date 2017年8月4日
 */
@SuppressWarnings("serial")
public class ChannelApiNotSupportException extends ChannelApiException {

	public ChannelApiNotSupportException() {
		super();
	}

	public ChannelApiNotSupportException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ChannelApiNotSupportException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChannelApiNotSupportException(String message) {
		super(message);
	}

	public ChannelApiNotSupportException(Throwable cause) {
		super(cause);
	}

}
