package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.PayQrcodeData;
import com.alycloud.channel.linkpay.bean.PayResult;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;

/**
 * 支付宝支付二维码接口
 * @author Moyq5
 * @date 2017年5月9日
 */
public class PayQrcodeAlipay extends AbstractClient<PayQrcodeData, PayResult> {

	public PayQrcodeAlipay() {
		super(Service.SMZF005, PayResult.class);
	}

}
