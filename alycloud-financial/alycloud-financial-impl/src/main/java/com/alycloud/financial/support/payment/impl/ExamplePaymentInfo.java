package com.alycloud.financial.support.payment.impl;

import java.math.BigDecimal;

import com.alycloud.financial.support.payment.PaymentInfo;
import com.alycloud.modules.enums.PaymentTransPayType;
import com.alycloud.modules.enums.SysLiquidateType;
import com.alycloud.modules.enums.SysSettleType;

/**
 * 
 * @author Moyq5
 * @date 2017年10月25日
 */
public class ExamplePaymentInfo implements PaymentInfo {

	@Override
	public BigDecimal getFee() {
		return new BigDecimal("1");
	}

	@Override
	public BigDecimal getMinAmount() {
		return new BigDecimal("10");
	}

	@Override
	public BigDecimal getMaxAmount() {
		return new BigDecimal("50000");
	}

	@Override
	public SysSettleType getSettleType() {
		return SysSettleType.T0;
	}

	@Override
	public Boolean isPaymentTime() {
		return true;
	}

	@Override
	public PaymentTransPayType getPayType() {
		return PaymentTransPayType.MANUAL;
	}

	@Override
	public SysLiquidateType getLiquidateType() {
		return SysLiquidateType.PLATFORM;
	}

}
