/*
 * 类文件名:  MerchDTO.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商户注册信息
 * 
 * @author   Horanluo
 * @version  V001Z0001
 * @date     2018年01月22日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchProxyDTO
{
	private String merchno;
	private String batchno;
	private String batchnum;
	private String batchamount;
	private String paydate;
	private String paydetail;
	private String backurl;
	private String extendparam;
	private String sign;
}
