package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商户银行卡信息
 * @author Moyq5
 * @date 2017年10月23日
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class MerchUserBank implements Serializable {

	private Integer id;
	private String userId;// 平台userId(商户操作员ID)
	private String cardName;// 银行卡账户名
	private String headBankNo;// 总行联行号
	private String headBankName;// 支行名称
	private String branchBankNo;// 支行联行号
	private String cardNo;// 银行卡号
	private String cardType;// 银行卡类型
	private String expDate;// 银行卡有效期
	private String phone;// 预留手机号
	private String cvv2;//信用卡安全码
	private String bankNameCode; //银行名称代码
}
