/*
 * 类文件名:  UserVO.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年10月12日
 * 功能版本:  V001Z0001
 */
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
 * @date     2017年10月17日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUserPwdVO
{
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    
    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String smsCode;
    
    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
    
    /**
     * 短信校验码
     */
    @NotBlank(message = "短信校验码不能为空")
    private String verifyCode;
}
