/*
 * 类文件名:  FastTransMapper.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月6日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.mapper;

import java.util.List;

import com.alycloud.modules.entity.MerchVirtualTrans;

/**
 * 商户提现流水
 * 
 * @author   Horanluo
 * @version  V001Z0001
 * @date     2018年01月22日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MerchVirtualTransMapper
{

	int createMerchVirtualTrans(MerchVirtualTrans mvtrans);
	
	MerchVirtualTrans getMerchVirtualTrans(String traceno);
	
	int modifyMerchVirtualTrans(MerchVirtualTrans mvtrans);
	
	List<MerchVirtualTrans> getMerchVirtualTransList(MerchVirtualTrans merchVirtualTrans);
	
	int createBatchMerchVirtualTrans(List<MerchVirtualTrans> list);
	
	List<MerchVirtualTrans> getBatchList(MerchVirtualTrans merchVirtualTrans);
	
	List<MerchVirtualTrans> getInfoByBatchno(String batchno);
}
