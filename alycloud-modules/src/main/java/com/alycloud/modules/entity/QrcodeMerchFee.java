package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商户二维码费率
 * @author Moyq5
 * @date 2017年5月23日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrcodeMerchFee implements Serializable {
	
	private Integer id;
	private String branchno;// 机构编码
	private String agentno;// 代理商号
	private String merchno;// 商户号
	private Integer payType; // 支付方式，多选
	private Integer scanType;// 扫码类型，多选
	private Integer settleType;// 结算类型，多选
	private BigDecimal rate;// 商户费率信息
	private BigDecimal fee;// 单笔提现费（元）
}
