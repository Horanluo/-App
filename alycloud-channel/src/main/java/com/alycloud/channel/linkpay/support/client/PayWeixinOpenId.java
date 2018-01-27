package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.PayOpenIdData;
import com.alycloud.channel.linkpay.bean.PayOpenIdResult;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;

/**
 * 微信公众号支付接口(模式二)。<br>
 * 合作商通过调用此接口得到一个微信公众号支付所需的参数，合作商可以使用这些参数，调用微信支付插件。
 * @author Moyq5
 * @date 2017年5月9日
 */
public class PayWeixinOpenId extends AbstractClient<PayOpenIdData, PayOpenIdResult> {

	public PayWeixinOpenId() {
		super(Service.SMZF015, PayOpenIdResult.class);
	}

}
