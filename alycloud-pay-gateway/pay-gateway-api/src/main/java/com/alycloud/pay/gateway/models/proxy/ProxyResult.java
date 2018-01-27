package com.alycloud.pay.gateway.models.proxy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class ProxyResult {

	private String respCode;
	private String message;
	private String sign;
	private String batchno;
	private String businessNumber;
	private String payBusinessNumber;
	private String subject;
	private String payAmount;
	private String Toaccountnumber;
	private String bankCardNumber;
	private String bankCardName;
	private String status;
	private String dealResult;
	private String remark;
	private String inputDate;
	private String stampDate;
	
	/**
	 *  批次号	batchNo
		业务流水号	businessNumber
		支付流水号	payBusinessNumber
		商品名称	subject
		代付金额	payAmount
		收方账号	Toaccountnumber
		银行卡号	bankCardNumber
		银行卡姓名	bankCardName
		状态	status
		处理结果描述	dealResult
		备注	remark
		提交日期	inputDate
		最后处理日期	stampDate
	 */
}
