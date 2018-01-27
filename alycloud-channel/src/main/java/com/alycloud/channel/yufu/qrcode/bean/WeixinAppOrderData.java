package com.alycloud.channel.yufu.qrcode.bean;

import com.alycloud.channel.yufu.qrcode.enums.ChannelFlag;
import com.alycloud.channel.yufu.qrcode.jackson.converter.ChannelFlagSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 微信APP支付－请求参数
 * @author Moyq5
 * @date 2017年9月27日
 */
public class WeixinAppOrderData extends AbstractOrderData {

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
	 * 接入方微信appid（于微信开放平台分配），长18，必填，<br>
	 * 接入方微信appid（于微信开放平台分配）
	 */
	@JsonProperty("subAppid")
	private String subAppId;
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
	public String getSubAppId() {
		return subAppId;
	}
	public void setSubAppId(String subAppId) {
		this.subAppId = subAppId;
	}
	@Override
	public String toString() {
		return "WeixinAppOrderData [channelFlag=" + channelFlag + ", notifyUrl=" + notifyUrl + ", subAppId=" + subAppId
				+ ", toString()=" + super.toString() + "]";
	}
	
}
