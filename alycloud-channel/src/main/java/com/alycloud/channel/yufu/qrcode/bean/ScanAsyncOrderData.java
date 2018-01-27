package com.alycloud.channel.yufu.qrcode.bean;

/**
 * 扫码收款（异步同步返回）接口－请求参数
 * @author Moyq5
 * @date 2017年8月29日
 */
public class ScanAsyncOrderData extends AbstractOrderData {

	/**
	 * 授权码，长128，必填，<br>
	 * 扫码支付授权码，设备读取用户客户端中的条码或者二维码信息
	 */
	private String authCode;
	/**
	 * 异步通知url，长120，非必填，<br>
	 * 需要向申请白名单
	 */
	private String notifyUrl;
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	@Override
	public String toString() {
		return "ScanUnSyncOrderData [authCode=" + authCode + ", notifyUrl=" + notifyUrl + ", toString()="
				+ super.toString() + "]";
	}
	
}
