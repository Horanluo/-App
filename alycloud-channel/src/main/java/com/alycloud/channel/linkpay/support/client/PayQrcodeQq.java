package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.PayQrcodeDataWithLimit;
import com.alycloud.channel.linkpay.bean.PayResult;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;

/**
 * QQ支付二维码接口
 * @author Moyq5
 * @date 2017年6月5日
 */
public class PayQrcodeQq extends AbstractClient<PayQrcodeDataWithLimit, PayResult> {

	public PayQrcodeQq() {
		super(Service.SMZF016, PayResult.class);
	}

}
