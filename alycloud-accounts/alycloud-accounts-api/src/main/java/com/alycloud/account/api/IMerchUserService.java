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
import java.util.Map;

import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.search.MerchUser4Search;
import com.alycloud.modules.vo.RegistUserVO;

/**
 * 商户账户接口服务
 * 
 * @author   罗根恒
 * @version  V001Z0001
 * @date     2017年10月16日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IMerchUserService
{
    /**
     * 
     * 查询商户信息，用于登录
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	MerchUser queryMerchUser(String loginName);
	
	/**
     * 
     * 更新登录错误次数
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	int updateErrorCount(Integer id,Integer errorCount,Integer status);
	
	/**
     * 
     * 新增用户
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	int addMerchUser(RequestBean<RegistUserVO> reqData,Integer merchId,MerchUser refer)throws ApiException;
	
	/**
	 * 查询用户
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param userId
	 * @return
	 */
	List<MerchUser> listByPage(MerchUser4Search user4s);
	
	/**
	 * 更新用户
	 * @author Moyq5
	 * @date 2017年10月31日
	 * @param user
	 */
	void mod(MerchUser user);

	/**
	 * 统计记录数
	 * @author Moyq5
	 * @date 2017年11月4日
	 * @param data
	 * @return
	 */
	Integer count(MerchUser4Search data);
	
	/**
	 * 修改用户登录密码
	 * 
	 * @author   罗根恒
	 * @version  V001Z0001
	 * @date     2017年11月16日
	 * @see  [相关类/方法]
	 * @since  [产品/模块版本]
	 */
	int modifyUserPwd(String loginName,String pwd)throws Exception;

	/**
	 * 更换手机号
	 * @author   罗根恒
	 * @version  V001Z0001
	 * @date     2017年11月16日
	 * @see  [相关类/方法]
	 * @since  [产品/模块版本]
	 */
	int modifyUserMobile(String oldMobile,String newMobile)throws Exception;
	
	/**
	 * 按等级统计用户的商户（被邀请人）
	 * @author Moyq5
	 * @date 2017年11月17日
	 * @param merchno
	 * @return
	 */
	List<Map<String, Object>> countGradeInviteeByMerchno(String merchno);
	

	/**
	 * 统计用户的商户人数
	 * @author Moyq5
	 * @date 2017年11月17日
	 * @param id
	 * @return
	 */
	Integer countInviteeByMerchno(String merchno);
}
