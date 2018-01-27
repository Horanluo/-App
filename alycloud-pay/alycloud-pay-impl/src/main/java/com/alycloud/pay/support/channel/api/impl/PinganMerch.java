package com.alycloud.pay.support.channel.api.impl;

import com.alycloud.channel.pingan.support.Merch;
import com.alycloud.modules.entity.QrcodeMerch;

/**
 * 平安API商户配置参数
 * @author Moyq5
 * @date 2017年10月12日
 */
public class PinganMerch implements Merch {

	private QrcodeMerch merch;
	
	public PinganMerch(QrcodeMerch merch) {
		this.merch = merch;
	}
	
	@Override
	public String getOpenId() {
		return merch.getPartnerId();
	}

	@Override
	public String getOpenKey() {
		return merch.getMerchKey();
	}

	@Override
	public String getPublicKey() {
		return null;
	}

}
