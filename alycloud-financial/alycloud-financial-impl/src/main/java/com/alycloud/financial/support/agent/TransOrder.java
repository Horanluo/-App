package com.alycloud.financial.support.agent;

import java.math.BigDecimal;

/**
 * 
 * @author Moyq5
 * @date 2017年11月9日
 */
public interface TransOrder {

	String getMerchno();

	BigDecimal getTotalFee();

	String getOrderno();

	BigDecimal getBranchFee();

	Integer getPayType();

	int getScanType();

	int getSettleType();

	BigDecimal getTransAmount();

	BigDecimal getPaymentFee();

	String getBranchName();

	String getBranchno();

	BigDecimal getChannelFee();

	String getMerchName();

}
