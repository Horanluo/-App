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
import java.util.Map;

import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.entity.MerchUserBank;
import com.alycloud.modules.search.MerchUser4Search;

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
public interface MerchUserMapper
{

    /**
     * 查询商户信息
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    MerchUser queryMerchUser(Map<String,String> userParams);
    
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
    int updateMerchUser(Map<String,Integer> errorParam);
    
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
	int addMerchUser(MerchUser merch);
	
	/**
     * 
     * 查询商户最大内部id
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月18日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	MerchUser getMaxMerchId();
	
	/**
     * 
     * 绑定银行卡
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月20日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	int addMerchUserBank(MerchUserBank mubb);

	/**
	 * 根据id获取用户
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param userId
	 * @return
	 */
	MerchUser getById(Integer userId);
	
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
	 * @param user4s
	 * @return
	 */
	Integer count(MerchUser4Search user4s);
	
	/**
	 * 修改用户密码
	 * @param pwd
	 * @return
	 * @author Horanluo
	 */
	int updateMerchUserInfo(MerchUser user);
	
	
	/**
	 * 按等级统计用户的商户（被邀请人）
	 * @author Moyq5
	 * @date 2017年11月17日
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> countGradeInviteeByUserId(Integer id);
	
	/**
	 * 统计用户的商户人数
	 * @author Moyq5
	 * @date 2017年11月17日
	 * @param id
	 * @return
	 */
	Integer countInviteeByUserId(Integer id);
	
	/**
	 * 更换登录账户  手机号
	 * @param mobile
	 * @return
	 * @author Horanluo
	 */
	int changeLoginName(Map<String,String> changeParam);
	
	/**
	 * 获取推荐人信息
	 * @param merchno
	 * @return
	 */
	MerchUser getReferInfo(String merchno);
	
	/**
	 * 获取推荐人信息
	 * @param merchno
	 * @return
	 */
	MerchUser getReferInfoByMerchUser(MerchUser merchUser);
}
