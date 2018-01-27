/*
 * 类文件名:  UserVO.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年10月12日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.vo;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   罗根恒
 * @version  V001Z0001
 * @date     2017年10月19日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
public class ModifyMerchFeeVO
{
	/**
     * 通道
     */
    @NotBlank(message = "通道不能为空")
    private String channelCode;
    
    /**
     * 修改后的费率
     */
    private BigDecimal modifyRate;
}
