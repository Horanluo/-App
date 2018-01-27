/*
 * 类文件名:  FastTransMapper.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月6日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.mapper;

import com.alycloud.modules.entity.MerchVirtualCard;

/**
 * 快捷交易流水持久化层
 * 
 * @author   Horanluo
 * @version  V001Z0001
 * @date     2018年01月22日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MerchVirtualCardMapper
{

	MerchVirtualCard getMerchVirtualCard(String merchno);
	
	int modifyMerchVirtualCard(MerchVirtualCard card);
}
