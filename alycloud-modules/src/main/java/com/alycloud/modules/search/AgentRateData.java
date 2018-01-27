package com.alycloud.modules.search;

import java.math.BigDecimal;
import java.util.List;

/**
 * 代理商费率数据
 * @author Moyq5
 * @date 2017年7月14日
 */
public class AgentRateData {

	private BigDecimal fastT0;// 快捷
	private BigDecimal fastT1;
	private BigDecimal gatewayT0;// 网关
	private BigDecimal gatewayT1;
	private List<QrcodeRateData> qrcodeRates;// 二维码
	public BigDecimal getFastT0() {
		return fastT0;
	}
	public void setFastT0(BigDecimal fastT0) {
		this.fastT0 = fastT0;
	}
	public BigDecimal getFastT1() {
		return fastT1;
	}
	public void setFastT1(BigDecimal fastT1) {
		this.fastT1 = fastT1;
	}
	public BigDecimal getGatewayT0() {
		return gatewayT0;
	}
	public void setGatewayT0(BigDecimal gatewayT0) {
		this.gatewayT0 = gatewayT0;
	}
	public BigDecimal getGatewayT1() {
		return gatewayT1;
	}
	public void setGatewayT1(BigDecimal gatewayT1) {
		this.gatewayT1 = gatewayT1;
	}
	public List<QrcodeRateData> getQrcodeRates() {
		return qrcodeRates;
	}
	public void setQrcodeRates(List<QrcodeRateData> qrcodeRates) {
		this.qrcodeRates = qrcodeRates;
	}
}
