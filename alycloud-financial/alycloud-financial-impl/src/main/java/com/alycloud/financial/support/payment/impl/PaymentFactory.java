package com.alycloud.financial.support.payment.impl;

import com.alycloud.financial.support.payment.Payment;
import com.alycloud.financial.support.payment.PaymentNotFoundException;

/**
 * 代付工厂类
 * @author Moyq5
 * @date 2017年10月24日
 */
public abstract class PaymentFactory {

	public static Payment getPayment() throws PaymentNotFoundException {
		return new ExamplePayment();
	}
}
