/**
 * 
 */
package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alycloud.modules.enums.PaymentTransPayStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代理虚拟账户提现流水
 * 
 * @author Moyq5
 * @date 2017年7月11日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentVirtualTrans implements Serializable {

	private Integer id;
	private String transDate; // 交易日期
	private String transTime; // 交易时间
	private String branchno; // 机构号
	private String branchName; // 机构名称
	private String agentno; // 代理商号
	private String agentName; // 代理商名称
	private String cardno; // 虚拟账户
	private Integer transType; // 交易类型(1-单笔充值 2-批量充值 3-提现)
	private BigDecimal amount; // 交易金额
	private String traceno; // 流水号
	private Integer status; // 交易状态(0-处理中 1-交易成功 2-交易失败 3-交易异常)
	private String loginName; // 操作员
	private BigDecimal amountBefore; // 交易前余额
	private BigDecimal amountAfter; // 交易后余额
	private BigDecimal transAmount; // 扣款金额
	private BigDecimal transFee; // 手续费
	private String typeCode; // 类型代码
	private String transRefno;// 商户订单号
	private String addTime; // 创建时间
	private String remark; // 备注
	/**
	 * @see {@link PaymentTransPayStatus}
	 */
	private Integer payStatus;
	private String payMsg; // 付款说明
	private Integer payType; // 提现方式(1-手机APP 2-接口 3-后台)
	private String batchno; // 批次号
	private String address; // 交易地址
	private String accountno; // 银行账户
	private String accountName; // 账户户名
	private String bankno; // 银行编号
	private String bankName; // 银行名称
	private String processTime; // 付款确认时间
	private Integer settleType; // 结算类型：0-T+0 1-T+1
	private String channelMerchno; // 渠道商户编号
	private Integer paymentType; // 付款类型:0-余额代付  1-垫资代付
}
