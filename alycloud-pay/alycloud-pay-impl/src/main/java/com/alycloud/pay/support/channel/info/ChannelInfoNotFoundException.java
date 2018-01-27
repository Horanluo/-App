package com.alycloud.pay.support.channel.info;

/**
 * 找不到渠道接口
 * @author Moyq5
 * @date 2017年8月4日
 */
@SuppressWarnings("serial")
public class ChannelInfoNotFoundException extends Exception {

	public ChannelInfoNotFoundException() {
		super();
	}

	public ChannelInfoNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ChannelInfoNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChannelInfoNotFoundException(String message) {
		super(message);
	}

	public ChannelInfoNotFoundException(Throwable cause) {
		super(cause);
	}

}
