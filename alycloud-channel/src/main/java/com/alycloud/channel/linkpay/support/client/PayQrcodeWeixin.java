package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.PayQrcodeDataWithLimit;
import com.alycloud.channel.linkpay.bean.PayResult;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;

/**
 * 微信二维码接口
 * @author Moyq5
 * @date 2017年5月9日
 */
public class PayQrcodeWeixin extends AbstractClient<PayQrcodeDataWithLimit, PayResult> {

	public PayQrcodeWeixin() {
		super(Service.SMZF004, PayResult.class);
	}

}
