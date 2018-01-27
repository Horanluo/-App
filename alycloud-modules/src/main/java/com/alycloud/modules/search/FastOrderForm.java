package com.alycloud.modules.search;

import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysSettleType;

/**
 * 快捷支付客户表单信息
 * @author Moyq5
 * @date 2017年8月4日
 */
public interface FastOrderForm {

	/**
	 * 渠道
	 * @see {@link SysChannelType}
	 */
	Integer getChannelType();
	/**
	 * 交易金额
	 */
	String getAmount();

	/**
	 * 付款卡名称
	 */
	String getBankName();

	/**
	 * 客户订单号（接口开放时，第四方订单号）
	 */
	String getTraceno();

	/**
	 * 付款人手机号
	 */
	String getMobile();

	/**
	 * 付款人姓名
	 */
	String getAccountName();

	/**
	 * 付款人身份证
	 */
	String getIdCardNo();

	/**
	 * 付款卡号
	 */
	String getAccountNo();

	/**
	 * 付款卡类型
	 */
	String getAccountType();

	/**
	 * 结算方式
	 * @see {@link SysSettleType}
	 */
	Integer getSettleType();

	/**
	 * 付款人备注信息
	 */
	String getPayerRemark();

}