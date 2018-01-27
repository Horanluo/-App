package com.alycloud.modules.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商户基本信息
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月21日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class SmsCodeInfo implements Serializable{

	private static final long serialVersionUID = 3808250600311849780L;
	
	private String verifyCode;
}
