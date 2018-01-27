package com.alycloud.modules.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.search.FastOrderForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 快捷支付-请求参数
 * @author Moyq5
 * @date 2017年10月22日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastPayDataVO implements FastOrderForm {
	private String merchno;
	/**
	 * @see {@link SysChannelType}
	 */
	@NotNull(message="渠道类型不能为空")
	private Integer channelType;
	
	@NotEmpty(message="交易金额不能为空")
	private String amount;
	
	/**
	 * @see {@link SysSettleType}
	 */
	private Integer settleType;
	private String payerRemark;
	private String bankName;
	private String traceno;
	private String mobile;
	private String accountName;
	private String idCardNo;
	
	@NotEmpty(message="银行卡号不能为空")
	private String accountNo;
	
	private String accountType;
	
	private String channelOrderNo;
}

