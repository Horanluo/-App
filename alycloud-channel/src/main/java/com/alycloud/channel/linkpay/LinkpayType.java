package com.alycloud.channel.linkpay;

import com.alycloud.channel.linkpay.support.AbstractClient;
import com.alycloud.channel.linkpay.support.client.AgentPayByBalance;
import com.alycloud.channel.linkpay.support.client.AgentPayByFund;
import com.alycloud.channel.linkpay.support.client.AgentPayQuery;
import com.alycloud.channel.linkpay.support.client.PayGatewayCredit;
import com.alycloud.channel.linkpay.support.client.PayGatewayDebit;
import com.alycloud.channel.linkpay.support.client.PayGatewayDebitAndCredit;
import com.alycloud.channel.linkpay.support.client.PayGatewayH5;
import com.alycloud.channel.linkpay.support.client.PayQrcodeAlipay;
import com.alycloud.channel.linkpay.support.client.PayQrcodeQq;
import com.alycloud.channel.linkpay.support.client.PayQrcodeWeixin;
import com.alycloud.channel.linkpay.support.client.PayQuery;
import com.alycloud.channel.linkpay.support.client.PayWeixin;
import com.alycloud.channel.linkpay.support.client.PayWeixinOpenId;

/**
 * 江苏电子支付－接口类型
 * @author Moyq5
 * @date 2017年5月9日
 */
public enum LinkpayType {

	/**
	 * 余额代付接口：AgentPayByBalance
	 */
	AGENT_PAY_BY_BALANCE(AgentPayByBalance.class),
	/**
	 * 垫资代付接口：AgentPayByFund
	 */
	AGENT_PAY_BY_FUND(AgentPayByFund.class),
	/**
	 * 代付结果查询接口：AgentPayQuery
	 */
	AGENT_PAY_QUERY(AgentPayQuery.class),
	/**
	 * 网关支付接口（贷记卡）：PayGatewayCredit
	 */
	GATEWAY_CREDIT(PayGatewayCredit.class),
	/**
	 * 网关支付接口（借记卡）：PayGatewayDebit
	 */
	GATEWAY_DEBIT(PayGatewayDebit.class),
	/**
	 * 网关支付接口（借贷综合）：PayGatewayDebitAndCredit
	 */
	GATEWAY_DEBIT_AND_CREDIT(PayGatewayDebitAndCredit.class),
	/**
	 * 网关支付接口（H5）：PayGatewayH5
	 */
	GATEWAY_H5(PayGatewayH5.class),
	/**
	 * 支付宝支付二维码接口：PayQrcodeAlipay
	 */
	QRCODE_ALIPAY(PayQrcodeAlipay.class), 
	/**
	 * QQ支付二维码接口：PayQrcodeQq
	 */
	QRCODE_QQ(PayQrcodeQq.class),
	/**
	 * 微支支付二维码接口：PayQrcodeWeixin
	 */
	QRCODE_WEIXIN(PayQrcodeWeixin.class),
	/**
	 * 微信公众号支付接口（模式一）：PayWeixin
	 */
	WEIXIN(PayWeixin.class),
	/**
	 * 微信公众号支付接口(模式二)：PayWeixinOpenId
	 */
	WEIXIN_OPENID(PayWeixinOpenId.class),
	/**
	 * 支付结果查询接口：PayQuery
	 */
	PAY_QUERY(PayQuery.class);
	
	private Class<? extends AbstractClient<?,?>> clientClass;
	
	LinkpayType(Class<? extends AbstractClient<?,?>> clientClass) {
		this.clientClass = clientClass;
	}

	public Class<? extends AbstractClient<?,?>> getClientClass() {
		return clientClass;
	}

}
