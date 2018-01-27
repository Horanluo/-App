package com.alycloud.pay.support.channel.info.impl;

import java.math.BigDecimal;

import com.alycloud.channel.support.config.Config;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysLiquidateType;
import com.alycloud.modules.enums.SysT0RateType;
import com.alycloud.modules.search.FastChannelInfo;

public class YufuFastChannelInfo implements FastChannelInfo {

	@Override
	public SysChannelType getType() {
		return SysChannelType.YUFU;
	}

	@Override
	public SysT0RateType getT0RateType() {
		return SysT0RateType.INCREMENT;
	}
	
	@Override
	public BigDecimal getT1Rate() {
		return new BigDecimal(Config.getString("ylpay_quick_rate_t1"));
	}

	@Override
	public BigDecimal getT0Rate() {
		return new BigDecimal(Config.getString("ylpay_quick_rate_t0"));
	}

	@Override
	public BigDecimal getPaymentFee() {
		return new BigDecimal(Config.getString("ylpay_advance_rate"));
	}

	@Override
	public BigDecimal getMinFee() {
		return new BigDecimal(Config.getString("ylpay_quick_min_fee"));
	}

	@Override
	public SysLiquidateType getLiquidateType() {
		return SysLiquidateType.CHANNEL;
	}

}
