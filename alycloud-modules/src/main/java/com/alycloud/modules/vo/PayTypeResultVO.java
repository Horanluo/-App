package com.alycloud.modules.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取支付渠道-响应参数
 * @author Moyq5
 * @date 2017年10月20日
 */
@JsonInclude(value=Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayTypeResultVO {

	private Integer channelType;// 渠道类型
	private String channelName; // 渠道名称
	private Integer t0Status;// T0状态
	private Integer t1Status;// T1状态
	private String amountMin;// 单笔最小金额(元)
	private String amountMax;// 单笔最大金额(元)
	private String amountDay;// 当天最高交易金额(元)
	private String t0Rate;// t0费率
	private String t1Rate;// t1费率
	private String t0Fee;// t0代付费(元)
	private String t1Fee;// t1代付费(元)
	private String remark;// 说明提示
	private Integer status;
}
