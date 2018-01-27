package com.alycloud.channel.yufu.qrcode.bean;

/**
 * 预统一下单－响应参数
 * @author Moyq5
 * @date 2017年8月28日
 */
public class UnifiedOrderResult extends AbstractResult {

	/**
	 * 平台方唯一交易请求流水号，长20，非必填，<br>
	 */
	private String transId;
	/**
	 * 请求交易的流水号，长32，非必填，<br>
	 * 请求交易的流水号（回传）
	 */
	private String reqId;
	/**
	 * 商户订单号，长30，非必填，<br>
	 * 商户订单号（回传）
	 */
	private String orderNo;
	/**
	 * 二维码链接，长64，非必填，<br>
	 * 渠道返回的二维码链接，可将该参数值生成二维码展示出来进行扫码支付，
	 * 返回码为0000时不为空
	 */
	private String codeUrl;
	/**
	 * 支付宝交易号，长64，非必填，<br>
	 * 如：2015042321001004720200028594
	 */
	private String tradeNo;
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCodeUrl() {
		return codeUrl;
	}
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	@Override
	public String toString() {
		return "UnifiedOrderResult [transId=" + transId + ", reqId=" + reqId + ", orderNo=" + orderNo + ", codeUrl="
				+ codeUrl + ", tradeNo=" + tradeNo + ", toString()=" + super.toString() + "]";
	}
}
