package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alycloud.modules.enums.PaymentTransBizType;
import com.alycloud.modules.enums.PaymentTransPayStatus;
import com.alycloud.modules.enums.PaymentTransPayType;
import com.alycloud.modules.enums.SysLiquidateType;
import com.alycloud.modules.enums.SysSettleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 代付（出款）流水
 * @author Moyq5
 * @date 2017年7月28日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTrans implements Serializable {
	private Long id;
	/**
	 * @see {@link PaymentTransBizType}
	 */
	private Integer bizType;// 业务类型
	private BigDecimal transAmount;// 交易金额（元）
	private BigDecimal transFee;// 手续费（元）
	private String refno;// 业务类型对应的原交易单号 
	private String traceno;// 客户单号（第四方流水号）
	private String idCardNo;// 收款人身份证号
	private String mobile;// 收款人手机号
	private String accountName;// 收款人银行卡账户名
	private String accountNo;// 收款人银行卡号
	private String bankType;// 收款人银行类型
	private String bankName;// 收款人银行名称（支行）
	private String bankNo;// 收款人银行联行号
	/**
	 * @see {@link SysSettleType}
	 */
	private Integer settleType;// 结算方式
	/**
	 * @see {@link SysLiquidateType}
	 */
	private Integer liquidateType;// 清算方式，即付款方类型
	/**
	 * @see {@link PaymentTransPayType}
	 */
	private Integer payType;// 出款方式
	/**
	 * @see {@link PaymentTransPayStatus}
	 */
	private Integer payStatus;// 出款状态
	private String payDescr;// 出款结果描述
	private String payOrderno;// 出款流水号（平台单号）
	private String payTime;// 出款日期时间
	private BigDecimal payAmount;// 出款金额（元）
	private Integer channelType;// 渠道类型
	private String channelMerchno;// 渠道商户号
	private String channelOrderno;// 渠道单号
	private Integer isPayAgain;// 是否已重出款
	private String addTime;// 交易时间
	private BigDecimal fee; //提现费
	private BigDecimal withdrawFee; //提现手续费
	private BigDecimal rate; //提现费率
	private String batchno;//代付批次号
	private String batchPayDetails;//批量代付明细
}
