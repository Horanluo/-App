package com.alycloud.pay.support.channel.api;

/**
 * 找不到渠道接口
 * @author Moyq5
 * @date 2017年8月4日
 */
@SuppressWarnings("serial")
public class ChannelApiNotFoundException extends ChannelApiException {

	public ChannelApiNotFoundException() {
		super();
	}

	public ChannelApiNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ChannelApiNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChannelApiNotFoundException(String message) {
		super(message);
	}

	public ChannelApiNotFoundException(Throwable cause) {
		super(cause);
	}

}
