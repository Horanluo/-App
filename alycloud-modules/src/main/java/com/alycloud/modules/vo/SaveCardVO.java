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
public class SaveCardVO {
    
    /**
     * 持卡人
     */
    @NotBlank(message = "持卡人姓名不能为空")
    private String cardName;
    
    /**
     * 身份证件号
     */
    @NotBlank(message = "身份证件号不能为空")
    private String idNo;
    
    /**
     * 卡号
     */
    @NotBlank(message = "卡号不能为空")
    private String cardNo;
    
    /**
     * 银行预留手机号
     */
    @NotBlank(message = "预留手机号不能为空")
    private String phoneNo;
}
