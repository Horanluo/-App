package com.alycloud.modules.search;

import java.math.BigDecimal;

import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysLiquidateType;
import com.alycloud.modules.enums.SysT0RateType;

/**
 * 快捷支付渠道配置信息
 * @author Moyq5
 * @date 2017年4月20日
 */
public interface FastChannelInfo {

	/**
	 * 渠道类型
	 */
	public SysChannelType getType();
	/**
	 * T0费率
	 */
	public BigDecimal getT0Rate();
	/**
	 * T1费率
	 */
	public BigDecimal getT1Rate();
	/**
	 * 单笔代付费（元）
	 */
	public BigDecimal getPaymentFee();
	
	/**
	 * 最低手续费（元）
	 */
	public BigDecimal getMinFee();
	
	/**
	 * T0费率类型
	 */
	public SysT0RateType getT0RateType();
	
	/**
	 * 清算类型
	 */
	public SysLiquidateType getLiquidateType();
}
