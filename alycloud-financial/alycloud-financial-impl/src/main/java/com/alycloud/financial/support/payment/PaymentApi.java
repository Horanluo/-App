package com.alycloud.financial.support.payment;

/**
 * 代付接口
 * @author Moyq5
 * @date 2017年10月24日
 */
public interface PaymentApi {

	/**
	 * 代付
	 * @author Moyq5
	 * @date 2017年10月24日
	 */
	PaymentResult pay(PaymentData data);
}
