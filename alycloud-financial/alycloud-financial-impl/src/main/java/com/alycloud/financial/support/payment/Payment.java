package com.alycloud.financial.support.payment;

/**
 * 代付渠道
 * @author Moyq5
 * @date 2017年10月24日
 */
public interface Payment {

	PaymentApi getPaymentApi();
	PaymentInfo getPaymentInfo();
}
