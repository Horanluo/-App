package com.alycloud.channel.yufu.qrcode.bean;

import com.alycloud.channel.yufu.qrcode.enums.ChannelFlag;
import com.alycloud.channel.yufu.qrcode.jackson.converter.ChannelFlagSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 预统一下单－请求参数
 * @author Moyq5
 * @date 2017年8月28日
 */
public class UnifiedOrderData extends AbstractOrderData {

	/**
	 * 支付渠道，长2，必填，<br>
	 */
	@JsonSerialize(converter = ChannelFlagSerialize.class)
	private ChannelFlag channelFlag;
	/**
	 * 异步通知url，长120，非必填，<br>
	 * 需要向申请白名单
	 */
	private String notifyUrl;
	/**
	 * 支付宝uerid，长28，非必填，<br>
	 * 买家的支付宝唯一用户号2088102146225135，
	 * 当channelFlag=01，且此参数不为空，则返回tradeNo，可在支付浏览器用js发起支付，
	 * 参考https://doc.open.alipay.com/docs/doc.htm?&docType=1&articleId=105672
	 */
	private String alipayUserId;
	public ChannelFlag getChannelFlag() {
		return channelFlag;
	}
	public void setChannelFlag(ChannelFlag channelFlag) {
		this.channelFlag = channelFlag;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getAlipayUserId() {
		return alipayUserId;
	}
	public void setAlipayUserId(String alipayUserId) {
		this.alipayUserId = alipayUserId;
	}
	@Override
	public String toString() {
		return "UnifiedOrderData [channelFlag=" + channelFlag + ", notifyUrl=" + notifyUrl + ", alipayUserId="
				+ alipayUserId + ", toString()=" + super.toString() + "]";
	}
	
}
