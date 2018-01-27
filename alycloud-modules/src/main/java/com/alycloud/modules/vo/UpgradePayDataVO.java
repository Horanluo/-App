package com.alycloud.modules.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.alycloud.modules.enums.QrcodePayType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 升级支付-请求参数
 * @author Moyq5
 * @date 2017年10月30日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpgradePayDataVO {

	
	@NotEmpty(message="升级等级不能为空")
	private Integer gradeType;
	
	/**
	 * @see {@link QrcodePayType}
	 */
	@NotNull(message="支付方式不能为空")
	private Integer payType;// 支付方式
	
	@NotNull(message="渠道不能为空")
	private Integer channelType;// 渠道
}
