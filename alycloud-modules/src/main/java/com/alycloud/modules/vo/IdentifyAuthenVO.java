package com.alycloud.modules.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <一句话功能简述>
 * 身份认证 请求 实体类
 * 
 * @author   罗根恒
 * @version  V001Z0001
 * @date     2017年10月24日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentifyAuthenVO {
    
	/**
     * 身份证姓名
     */
	@NotBlank(message="身份证姓名不能为空")
    private String idName;
	
	/**
     * 身份证号
     */
	@NotBlank(message="身份证号不能为空")
    private String idNo;
	
	/**
     * 身份证有效期
     */
	@NotBlank(message="身份证有效期不能为空")
    private String idValiTime;
	
	/**
     * 身份证正面照下载地址
     */
	@NotBlank(message="身份证正面照地址不能为空")
    private String frontUrl;
	
	/**
     * 身份证反面照下载地址
     */
	@NotBlank(message="身份证反面照地址不能为空")
    private String backUrl;
	
	private String sessionId;
}
