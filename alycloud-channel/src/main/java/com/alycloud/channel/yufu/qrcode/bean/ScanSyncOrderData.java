package com.alycloud.channel.yufu.qrcode.bean;

/**
 * 扫码收款（结果同步返回）接口－请求参数
 * @author Moyq5
 * @date 2017年8月29日
 */
public class ScanSyncOrderData extends AbstractOrderData {

	/**
	 * 授权码，长128，必填，<br>
	 * 扫码支付授权码，设备读取用户客户端中的条码或者二维码信息
	 */
	private String authCode;

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Override
	public String toString() {
		return "ScanSyncOrderData [authCode=" + authCode + ", toString()=" + super.toString() + "]";
	}
	
}
