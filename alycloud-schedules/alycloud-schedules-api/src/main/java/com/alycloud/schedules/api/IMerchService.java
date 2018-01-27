/*
 * 类文件名:  IMerchProviderService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.schedules.api;

import com.alycloud.modules.entity.MerchInfo;

/**
 * 商户账户接口服务
 * 
 * @author   罗根恒
 * @version  V001Z0001
 * @date     2017年10月16日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IMerchService
{
	/**
	 * 根据商户号获取商户
	 * @author Moyq5
	 * @date 2017年10月27日
	 * @param merchno
	 * @return
	 */
	MerchInfo getByMerchno(String merchno);
}
