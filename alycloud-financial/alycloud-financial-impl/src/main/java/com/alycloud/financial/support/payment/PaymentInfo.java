package com.alycloud.financial.support.payment;

import java.math.BigDecimal;

import com.alycloud.modules.enums.PaymentTransPayType;
import com.alycloud.modules.enums.SysLiquidateType;
import com.alycloud.modules.enums.SysSettleType;

/**
 * 代付信息
 * @author Moyq5
 * @date 2017年10月24日
 */
public interface PaymentInfo {

	/**
	 * 单笔代付费(元)
	 * @author Moyq5
	 * @date 2017年10月24日
	 * @return
	 */
	BigDecimal getFee();
	
	/**
	 * 单笔最小代付金额
	 * @author Moyq5
	 * @date 2017年10月24日
	 * @return
	 */
	BigDecimal getMinAmount();

	/**
	 * 单笔最高代付金额(元)
	 * @author Moyq5
	 * @date 2017年10月24日
	 * @return
	 */
	BigDecimal getMaxAmount();

	/**
	 * 结算方式
	 * @author Moyq5
	 * @date 2017年10月24日
	 * @return
	 */
	SysSettleType getSettleType();

	/**
	 * 当前是否为代付时间
	 * @author Moyq5
	 * @date 2017年10月24日
	 * @return
	 */
	Boolean isPaymentTime();

	/**
	 * 出款方式
	 * @author Moyq5
	 * @date 2017年10月25日
	 * @return
	 */
	PaymentTransPayType getPayType();

	/**
	 * 清算方式
	 * @author Moyq5
	 * @date 2017年10月25日
	 * @return
	 */
	SysLiquidateType getLiquidateType();

	
}
