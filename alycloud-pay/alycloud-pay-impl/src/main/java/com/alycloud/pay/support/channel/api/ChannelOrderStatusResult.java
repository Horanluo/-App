package com.alycloud.pay.support.channel.api;

/**
 * 订单状态信息
 * @author Moyq5
 * @date 2017年8月2日
 */
public class ChannelOrderStatusResult {

	/**
	 * 订单状态（根据渠道订单状态转换成平台通用订单状态）
	 */
	private ChannelOrderStatus status;
	/**
	 * 状态描述信息
	 */
	private String msg;
	/**
	 * 渠道单号
	 */
	private String channelOrderNo;
	/**
	 * 支付机构单号（渠道游渠道单号）
	 */
	private String tradeOrderNo;
	public ChannelOrderStatus getStatus() {
		return status;
	}
	public void setStatus(ChannelOrderStatus status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getChannelOrderNo() {
		return channelOrderNo;
	}
	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}
	public String getTradeOrderNo() {
		return tradeOrderNo;
	}
	public void setTradeOrderNo(String tradeOrderNo) {
		this.tradeOrderNo = tradeOrderNo;
	}
	
}
