package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 银行支行
 * @author Moyq5
 * @date 2017年9月11日
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class SystemBank implements Serializable {
	private Integer id;
	private String bankCode;// 支行联行号
	private String bankName;// 支行名称
	private String bankId;// 总行联行号
	private Integer bankType;
    private String clearBankCode;
    private String province;
    private String city;
    
}
