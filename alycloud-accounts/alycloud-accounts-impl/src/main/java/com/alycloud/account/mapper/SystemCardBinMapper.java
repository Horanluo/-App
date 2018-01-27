/*
 * 类文件名:  MerchUserMapper.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月22日
 * 功能版本:  V001Z0001
 */
package com.alycloud.account.mapper;

import java.util.List;

import com.alycloud.modules.entity.SystemCardBin;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月22日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SystemCardBinMapper
{

    /**
     * 查询卡bin信息
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	List<SystemCardBin> getCardBinInfo(String cardBin);
	
	/**
     * 查询卡bin信息
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	SystemCardBin getCardInfo(String cardNo);
}
