package com.alycloud.pay.support.channel.api;

/**
 * 渠道接口执行失败
 * @author Moyq5
 * @date 2017年8月4日
 */
@SuppressWarnings("serial")
public class ChannelApiExecuteFailException extends ChannelApiException {

	public ChannelApiExecuteFailException() {
		super();
	}

	public ChannelApiExecuteFailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ChannelApiExecuteFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChannelApiExecuteFailException(String message) {
		super(message);
	}

	public ChannelApiExecuteFailException(Throwable cause) {
		super(cause);
	}

}
