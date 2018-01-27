package com.alycloud.modules.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <一句话功能简述>
 * <功能详细描述>
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
public class IdCardVerifyVO {

	/**
     * 实名验证方式
     * 0-简项验证； 
	 * 1-人像验证（可取得网格照用于人脸比对）； 
	 * 默认值为1
     */
	@NotBlank(message="实名验证方式不能为空")
    private String verifyType;
    
	/**
     * 身份证号
     */
    private String idNo;
    
    /**
     * 身份证姓名
     */
    private String idName;
    
    /**
     * 扩展信息
     */
    private String extensionInfo;
}
