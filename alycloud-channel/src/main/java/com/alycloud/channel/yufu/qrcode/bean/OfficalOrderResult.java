package com.alycloud.channel.yufu.qrcode.bean;

/**
 * 微信公众号支付－响应参数
 * @author Moyq5
 * @date 2017年9月14日
 */
public class OfficalOrderResult extends AbstractResult {

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
	 * 服务商appid，长30，非必填，<br>
	 * 如：wx8888888888888888
	 */
	private String appId;
	/**
	 * 时间戳，长13，非必填，<br>
	 * 如：1414561699
	 */
	private String timeStamp;
	/**
	 * 随机字符串，长30，非必填，<br>
	 * 如：5K8264ILTKCH16CQ2502SI8ZNMTM67VS
	 */
	private String nonceStr;
	/**
	 * 订单详情扩展字符串，长200，非必填，<br>
	 * 如：prepay_id=123456789
	 */
	private String pack;
	/**
	 * 签名方式，长8，非必填，<br>
	 * 如：MD5
	 */
	private String signType;
	/**
	 * 签名（公众号支付时使用），长32，非必填，<br>
	 * 如：C380BEC2BFD727A4B6845133519F3AD6
	 */
	private String paySign;
	/**
	 * 签名（小程序支付时使用），长32，非必填，<br>
	 * 如：C380BEC2BFD727A4B6845133519F3AD6
	 */
	private String paySignForXiaoChengXu;
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	public String getPaySignForXiaoChengXu() {
		return paySignForXiaoChengXu;
	}
	public void setPaySignForXiaoChengXu(String paySignForXiaoChengXu) {
		this.paySignForXiaoChengXu = paySignForXiaoChengXu;
	}
	@Override
	public String toString() {
		return "OfficalOrderResult [transId=" + transId + ", reqId=" + reqId + ", orderNo=" + orderNo + ", appId="
				+ appId + ", timeStamp=" + timeStamp + ", nonceStr=" + nonceStr + ", pack=" + pack + ", signType="
				+ signType + ", paySign=" + paySign + ", paySignForXiaoChengXu=" + paySignForXiaoChengXu
				+ ", toString()=" + super.toString() + "]";
	}
	
}
