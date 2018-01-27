/**
 * 
 */
package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代理商虚拟账户
 * 
 * @author Moyq5
 * @date 2017年3月12日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentWithdraw implements Serializable {

	private AgentVirtualCard agentVirtualCard; 
	private BigDecimal withdrawAmt; //提现金额
	private String traceno;
}
