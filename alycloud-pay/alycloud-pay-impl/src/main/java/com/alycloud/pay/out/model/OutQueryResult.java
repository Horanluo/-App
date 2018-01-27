package com.alycloud.pay.out.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 扫码查询订单返回结果
 * @author Horanluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class OutQueryResult {

	private String payStatus; //支付状态 01成功 ，02失败 ，03支付中
	//private String traceno;  //交易流水号
	private String respCode;
	private String message;
	private String sign;
}
