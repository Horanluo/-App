package com.alycloud.modules.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.SysChannelType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 扫码支付-请求参数
 * @author Moyq5
 * @date 2017年10月17日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodePayDataVO {

	private String merchno;// 收款商户号，不填则为当前商户
	/**
	 * @see {@link SysChannelType}
	 */
	@NotNull(message="渠道类型不能为空")
	private Integer channelType;
	
	@NotEmpty(message="交易金额不能为空")
	private String amount;// 交易金额（元）
	
	/**
	 * @see {@link QrcodePayType}
	 */
	@NotNull(message="支付方式不能为空")
	private Integer payType;// 支付方式
	
	private Boolean isOffical;// 是否公众号模式
	
	private String notifyUrl;// 支付通知地址
}
