package com.alycloud.channel.support.config;

import org.springframework.stereotype.Component;

import com.alycloud.channel.esyto.EsytoApiFactory;
import com.alycloud.channel.linkpay.LinkpayFactory;
import com.alycloud.channel.payeco.PayecoFactory;
import com.alycloud.channel.pingan.PinganFactory;
import com.alycloud.channel.yufu.YufuApiFactory;
import com.alycloud.channel.yufu.merch.YufuMerchApiFactory;
import com.alycloud.channel.yufu.qrcode.YufuQrcodeApiFactory;

/**
 * 各渠道全局参数配置
 * @author Moyq5
 * @date 2017年10月17日
 */
@Component
public class ConfigInit {

	public ConfigInit() {
		PayecoFactory.config(new PayecoConfig(), new PayecoHttpClient());
		LinkpayFactory.config(new LinkpayConfig(), new LinkpayHttpClient());
		YufuApiFactory.config(new YufuFastPayConfig(), new YufuFastPayHttpClient());
		YufuMerchApiFactory.config(new YufuMerchConfig(), new YufuMerchHttpClient());
		PinganFactory.config(new PinganConfig(), new PinganHttpClient());
		YufuQrcodeApiFactory.config(new YufuQrcodePayConfig(), new YufuQrcodePayHttpClient());
		EsytoApiFactory.config(new EsytoConfig(), new EsytoHttpClient());
	}

}
