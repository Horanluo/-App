package com.alycloud.modules.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 快捷支付-响应参数
 * @author Moyq5
 * @date 2017年11月8日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastPayResultVO {
	private String content;
	private String respCode;
	private String respMes;
	private boolean isSuccess;
}

