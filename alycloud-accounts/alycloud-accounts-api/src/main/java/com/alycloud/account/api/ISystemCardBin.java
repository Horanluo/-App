package com.alycloud.account.api;

import java.util.List;

import com.alycloud.modules.entity.SystemCardBin;

/**
 * 卡bin信息服务接口
 * 
 * @author   罗根恒
 * @version  V001Z0001
 * @date     2017年10月20日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISystemCardBin {

	/**
	 * 查询卡bin信息
	 */
	List<SystemCardBin> getCardBin(String cardBin);
}
