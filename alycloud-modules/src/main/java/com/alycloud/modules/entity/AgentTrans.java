package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alycloud.modules.enums.AgentTransDivideType;
import com.alycloud.modules.enums.SysTransType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代理商分润信息
 * @author Moyq5
 * @date 2017年6月9日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentTrans implements Serializable {

	private Integer id;
	private String transDate;// 交易日期
	private String transTime;// 交易时间
	private String merchno;// 商户号
	private String merchName;// 商户名称
	private String agentno;// 代理商号
	private String agentName;// 代理商名称
	private String branchno;// 机构号
	private String branchName;// 机构名称
	private String typeMemo;// 分润类型名称
	private Integer type;// 分润类型
	private String refno;// 订单号
	private BigDecimal amount;// 交易金额
	private Integer divide;// 分成比例
	private BigDecimal totalFee;// 商户手续费
	private BigDecimal channelFee;// 渠道成本
	private BigDecimal costFee;// 代理商成本
	private BigDecimal totalAgentFee;// 代理商总的手续费
	private BigDecimal agentFee;// 代理商分润
	private Integer transStatus;// 交易状态
	private Integer liquidStatus;// 清算状态
	private String liquidDate;// 清算日期
	private BigDecimal payFee;// 提现费分成
	private String settleDate;// 清算日期
	private Integer agentLevel;// 代理商级别
	private BigDecimal t0AgentFee;// T+0新增手续费
	/**
	 * @see {@link AgentTransDivideType}
	 */
	private Integer divideType;// 分润类型
	/**
	 * @see {@link SysTransType}
	 */
	private Integer transType;// 交易类型
	
	//private BigDecimal totalAgentAmt;
}
