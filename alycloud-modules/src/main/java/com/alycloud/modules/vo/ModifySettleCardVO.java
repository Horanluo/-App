package com.alycloud.modules.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更改结算卡
 * @author Horanluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifySettleCardVO {

	private String cardNo;
	private String smsCode;
	private String verifyCode;
}
