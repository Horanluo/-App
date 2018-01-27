package com.alycloud.pay.support.channel.api;

/**
 * 扫码支付接口结果
 * @author Moyq5
 * @date 2017年10月12日
 */
public class ChannelQrcodeOrderResult {

	private String channelOrderNo;// 渠道订单号
	private String tradeOrderNo;// 机构订单号（渠道上游订单号）
	private String qrcode;// 二维码内容（一般为URL地址，用于生成二维码供付款人扫码支付）
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
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	
}
