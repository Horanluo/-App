package com.alycloud.pay.out.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 扫码支付返回结果
 * @author Horanluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class OutTranResult {

	private String qrcodeUrl;
	private String respCode;
	private String message;
	private String sign;
}
