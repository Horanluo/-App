package com.alycloud.modules.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 记录累计金额   累计收款   可提现
 * @author Administrator
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class TotalAmountInfo {
	
	private BigDecimal receiveAmount;  //累计收款
	private BigDecimal withdrawalAmount; //可提现金额
}
