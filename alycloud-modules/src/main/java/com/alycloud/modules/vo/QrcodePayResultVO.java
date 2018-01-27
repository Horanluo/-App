package com.alycloud.modules.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 扫码支付-响应参数
 * @author Moyq5
 * @date 2017年10月30日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class QrcodePayResultVO {

	private String qrcodeUrl;// 二维码内容
	private String orderno;// 订单号
}
