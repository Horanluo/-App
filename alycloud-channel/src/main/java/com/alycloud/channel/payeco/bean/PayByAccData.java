package com.alycloud.channel.payeco.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.alycloud.channel.payeco.enums.NotifyFlag;

/**
 * 易联支付－无磁无密交易接口－请求参数
 * @author Moyq5
 * @date 2017年4月11日
 */
public class PayByAccData extends AbstractData {

	/**
	 * 商户行业编号<br>
	 * 未上送此字段时，系统将使用商户配置中对应的行业<br>
	 * 参考下面的【订单扩展信息说明】
	 */
	private String industryId;
	/**
	 * 商户订单金额，必填<br>
	 * 单位为元，格式：nnnnnn.nn
	 */
	private BigDecimal amount;
	/**
	 * 商户订单描述，必填<br>
	 * 字符最大128，中文最多40个；<br>
	 * 参与签名：采用UTF-8编码；<br>
	 * 提交参数：采用UTF-8的base64格式编码<br>
	 */
	private String orderDesc;
	/**
	 * 商户订单提交时间，必填<br>
	 * 格式：yyyymmddhhmmss
	 */
	private Date tradeTime;
	/**
	 * 交易超时时间<br>
	 * 格式：yyyymmddhhmmss<br>
	 * 超过订单超时时间未支付，订单作废；不提交该参数，采用系统的默认时间（从接收订单后超时时间为30分钟）
	 */
	private Date expTime;
	/**
	 * 异步通知URL，必填<br>
	 * 提交通讯前提交参数：做URLEncode处理
	 */
	private String notifyUrl;
	/**
	 * 商户保留信息<br>
	 * 通知结果时，原样返回给商户；<br>
	 * 字符最大128，中文最多40个；<br>
	 * 参与签名：采用UTF-8编码<br>
	 * 提交参数：采用UTF-8的base64格式编码
	 */
	private String extData;
	/**
	 * 订单扩展信息<br>
	 * 根据不同的行业，传送的信息不一样；<br>
	 * 商户根据自己所属行业进行填写；<br>
	 * 各行业的数据定义参考下面的【订单扩展信息说明】<br>
	 * 参与签名：采用UTF-8编码<br>
	 * 提交参数：采用UTF-8的base64格式编码
	 */
	private String miscData;
	/**
	 * 订单通知标志<br>
	 * 0：成功才通知（02状态），<br>
	 * 1：全部通知（02、03、04、05状态）<br>
	 * 不填默认为“1：全部通知”
	 */
	private NotifyFlag notifyFlag;
	/**
	 * 短信凭证号，必填<br>
	 */
	private String smId;
	/**
	 * 短信验证码，必填<br>
	 */
	private String smCode;
	
	@Override
	public String getTradeCode() {
		return "PayByAcc";
	}
	
	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public Date getExpTime() {
		return expTime;
	}
	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getExtData() {
		return extData;
	}
	public void setExtData(String extData) {
		this.extData = extData;
	}
	public String getMiscData() {
		return miscData;
	}
	public void setMiscData(String miscData) {
		this.miscData = miscData;
	}
	public NotifyFlag getNotifyFlag() {
		return notifyFlag;
	}
	public void setNotifyFlag(NotifyFlag notifyFlag) {
		this.notifyFlag = notifyFlag;
	}
	public String getSmId() {
		return smId;
	}
	public void setSmId(String smId) {
		this.smId = smId;
	}
	public String getSmCode() {
		return smCode;
	}
	public void setSmCode(String smCode) {
		this.smCode = smCode;
	}
	@Override
	public String toString() {
		return "PayByAccData [industryId=" + industryId + ", amount=" + amount + ", orderDesc=" + orderDesc
				+ ", tradeTime=" + tradeTime + ", expTime=" + expTime + ", notifyUrl=" + notifyUrl + ", extData="
				+ extData + ", miscData=" + miscData + ", notifyFlag=" + notifyFlag + ", smId=" + smId + ", smCode="
				+ smCode + ", toString()=" + super.toString() + "]";
	}
	
}
