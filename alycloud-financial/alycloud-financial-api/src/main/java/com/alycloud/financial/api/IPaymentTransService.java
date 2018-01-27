package com.alycloud.financial.api;

import com.alycloud.modules.entity.PaymentTrans;

/**
 * 代付流水Service
 * @author Moyq5
 * @date 2017年10月24日
 */
public interface IPaymentTransService {

	String genRefno() throws Exception;

	PaymentTrans add(PaymentTrans payTrans);

}
