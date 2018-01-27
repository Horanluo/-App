package com.alycloud.channel.payeco.bean;

import java.util.Date;

import com.alycloud.channel.payeco.jackson.converter.StringToDateBy_yyyyMMddHHmmss;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 易联支付－－短信验证码发送接口－响应参数
 * @author Moyq5
 * @date 2017年4月11日
 */
public class SendSmCodeResultBody extends AbstractResultBody {
	/**
	 * 短信凭证号，必填<br>
	 */
	private String smId;
	/**
	 * 交易提交时间，必填<br>
	 * 格式：yyyymmddhhmmss
	 */
	@JsonDeserialize(converter=StringToDateBy_yyyyMMddHHmmss.class)
	private Date tradeTime;
	/**
	 * 已发送次数，必填<br>
	 */
	private Integer complated;
	/**
	 * 剩余发送次数<br>
	 * 该凭证号还可以再次发送的短信次数。无值为不限制
	 */
	private Integer remain;
	/**
	 * 短信码有效时间，必填<br>
	 * 单位：分钟
	 */
	private Integer expTime;
	public String getSmId() {
		return smId;
	}
	public void setSmId(String smId) {
		this.smId = smId;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public Integer getComplated() {
		return complated;
	}
	public void setComplated(Integer complated) {
		this.complated = complated;
	}
	public Integer getRemain() {
		return remain;
	}
	public void setRemain(Integer remain) {
		this.remain = remain;
	}
	public Integer getExpTime() {
		return expTime;
	}
	public void setExpTime(Integer expTime) {
		this.expTime = expTime;
	}
	@Override
	public String toString() {
		return "SendSmCodeResultBody [smId=" + smId + ", tradeTime=" + tradeTime + ", complated=" + complated
				+ ", remain=" + remain + ", expTime=" + expTime + ", toString()=" + super.toString() + "]";
	}
	
}