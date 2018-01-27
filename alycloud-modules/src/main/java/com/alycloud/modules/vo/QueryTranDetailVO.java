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
 * @date     2017年10月19日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryTranDetailVO
{
    
    /**
     * 交易起始时间
     */
	@NotBlank(message ="{tran.startDate.notBlank}")
    private String startDate;
    
    /**
     * 交易起始时间
     */
    private String endDate;
    
    /**
     * 订单号
     */
    private String orderNo;
    
    /**
     * 支付方式
     */
    private String payType;
    
    /**
     * 交易状态
     */
    private String tranStatus;
}
