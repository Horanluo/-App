package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.PayGatewayData;
import com.alycloud.channel.linkpay.bean.PayResult;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;

/**
 * 网关支付接口（贷记卡）
 * @author Moyq5
 * @date 2017年6月7日
 */
public class PayGatewayCredit extends AbstractClient<PayGatewayData, PayResult> {

	public PayGatewayCredit() {
		super(Service.WGZF002, PayResult.class);
	}

}
