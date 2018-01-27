package com.alycloud.financial.support.payment.impl;

import com.alycloud.financial.support.payment.Payment;
import com.alycloud.financial.support.payment.PaymentApi;
import com.alycloud.financial.support.payment.PaymentInfo;

/**
 * @author Moyq5
 * @date 2017年10月25日
 */
public class ExamplePayment implements Payment {

	@Override
	public PaymentApi getPaymentApi() {
		return new ExamplePaymentApi();
	}

	@Override
	public PaymentInfo getPaymentInfo() {
		return new ExamplePaymentInfo();
	}

}
