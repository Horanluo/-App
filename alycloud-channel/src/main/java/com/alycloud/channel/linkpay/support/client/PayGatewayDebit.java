package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.PayGatewayData;
import com.alycloud.channel.linkpay.bean.PayResult;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;

/**
 * 网关支付接口（借记卡）
 * @author Moyq5
 * @date 2017年6月7日
 */
public class PayGatewayDebit extends AbstractClient<PayGatewayData, PayResult> {

	public PayGatewayDebit() {
		super(Service.WGZF001, PayResult.class);
	}

}
