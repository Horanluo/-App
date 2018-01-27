package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.PayQrcodeDataWithLimit;
import com.alycloud.channel.linkpay.bean.PayResult;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;

/**
 * 微信公众号支付接口（模式一）。<br>
 * 合作商通过调用此接口得到一个可以进行微信支付的URL，<br>
 * 合作商可以在程序里直接重定向到返回的URL完成微信支付插件的调用，<br>
 * 或者使用返回的URL生成二维码展示到前台，客户扫描后完成微信支付插件的调用。
 * @author Moyq5
 * @date 2017年5月9日
 */
public class PayWeixin extends AbstractClient<PayQrcodeDataWithLimit, PayResult> {

	public PayWeixin() {
		super(Service.SMZF014, PayResult.class);
	}

}
