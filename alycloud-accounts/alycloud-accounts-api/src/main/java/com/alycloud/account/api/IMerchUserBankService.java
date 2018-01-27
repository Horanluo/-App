/*
 * 类文件名:  IMerchProviderService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.account.api;

import java.util.List;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.MerchUserBank;
import com.alycloud.modules.vo.CardVO;

/**
 * 查询账户绑卡信息
 * 
 * @author   罗根恒
 * @version  V001Z0001
 * @date     2017年10月16日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IMerchUserBankService
{
    /**
     * 
     * 查询账户绑卡列表
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	List<MerchUserBank> queryUserBankList(String loginName);
	
	/**
     * 
     * 绑定银行卡
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月18日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	SingleResult<String> addMerchUserBank(RequestBean<CardVO> reqData);
	
	/**
     * 
     * 编辑银行卡
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月18日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	SingleResult<String> modifyMerchUserBank(RequestBean<CardVO> reqData);
}
