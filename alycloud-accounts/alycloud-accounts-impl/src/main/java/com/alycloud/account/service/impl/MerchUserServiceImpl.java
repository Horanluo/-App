/*
 * 类文件名:  MerchProviderServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import com.alycloud.account.api.IMerchService;
import com.alycloud.account.api.IMerchUserService;
import com.alycloud.account.mapper.MerchUserMapper;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.MD5;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.enums.MerchUserRank;
import com.alycloud.modules.search.MerchUser4Search;
import com.alycloud.modules.vo.RegistUserVO;

/**
 * 服务实现类
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月21日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@ConfigurationProperties(prefix="hfbank")
public class MerchUserServiceImpl implements IMerchUserService {
	
	@Autowired
	private MerchUserMapper merchUserMapper;
	@Autowired
	private IMerchService merchInfoService;
	
	@Override
	@ServiceLogAnnotation(moduleName="App项目账户登录")
	public MerchUser queryMerchUser(String loginName) {
		Map<String,String> userParams = new HashMap<String,String>();
		userParams.put("loginName", loginName);
		return merchUserMapper.queryMerchUser(userParams);
	}

	@Override
	@ServiceLogAnnotation(moduleName="更新登录错误次数")
	public int updateErrorCount(Integer id,Integer errorCount,Integer status) {
		Map<String,Integer> errorParam = new HashMap<String,Integer>();
		errorParam.put("id", id);
		errorParam.put("status", status);
		errorParam.put("errorCount", errorCount);
		return merchUserMapper.updateMerchUser(errorParam);
	}

	@Override
	@ServiceLogAnnotation(moduleName="新增用户信息")
	public int addMerchUser(RequestBean<RegistUserVO> reqData,Integer merchId,MerchUser refer) throws ApiException{
		
  		try {
  			MerchUser merchUser = new MerchUser();
  	  		merchUser.setUserRoler(0);
  	  		merchUser.setMerchId(merchId);
  	  		merchUser.setLoginName(reqData.getData().getMobile());
  	  		merchUser.setTrueName("");
			merchUser.setPassword(MD5.getMD5ofStr(reqData.getData().getPassword(),"GBK"));
			merchUser.setTelephone("");
	  		merchUser.setMobile(reqData.getData().getMobile());
	  		merchUser.setEmail("");
	  		merchUser.setAddress("");
	  		merchUser.setStatus(1);
	  		merchUser.setLastLogin("");
	  		merchUser.setLoginErrorDate("");
	  		merchUser.setErrorCount(0);
	  		merchUser.setBranchno("2000440305");
	  		merchUser.setOpenId("");
	  		merchUser.setReferName(refer.getId().toString());
	  		merchUser.setUserRank(MerchUserRank.STAFF_MEMBER.getValue());
	  		return merchUserMapper.addMerchUser(merchUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException(ApiCode.C9999);
		}
	}

	@ServiceLogAnnotation(moduleName="查询用户")
	@Override
	public List<MerchUser> listByPage(MerchUser4Search user4s) {
		return merchUserMapper.listByPage(user4s);
	}

	@ServiceLogAnnotation(moduleName="更新用户")
	@Override
	public void mod(MerchUser user) {
		merchUserMapper.mod(user);
	}

	@ServiceLogAnnotation(moduleName="统计记录数")
	@Override
	public Integer count(MerchUser4Search user4s) {
		return merchUserMapper.count(user4s);
	}

	@Override
	@ServiceLogAnnotation(moduleName="修改用户密码")
	public int modifyUserPwd(String loginName,String pwd) throws Exception {
		MerchUser modifyUser = queryMerchUser(loginName);
		modifyUser.setPassword(MD5.getMD5ofStr(pwd,"GBK"));
		return merchUserMapper.updateMerchUserInfo(modifyUser);
	}


	@Override
	public List<Map<String, Object>> countGradeInviteeByMerchno(String merchno) {
		MerchInfo merch = merchInfoService.getByMerchno(merchno);
		MerchUser4Search user4s = new MerchUser4Search();
		user4s.setMerchId(merch.getId());
		MerchUser merchUser = merchUserMapper.listByPage(user4s).get(0);
		return merchUserMapper.countGradeInviteeByUserId(merchUser.getId());
	}

	@Override
	public Integer countInviteeByMerchno(String merchno) {
		MerchInfo merch = merchInfoService.getByMerchno(merchno);
		MerchUser4Search user4s = new MerchUser4Search();
		user4s.setMerchId(merch.getId());
		MerchUser merchUser = merchUserMapper.listByPage(user4s).get(0);
		return merchUserMapper.countInviteeByUserId(merchUser.getId());
	}

	@Override
	@ServiceLogAnnotation(moduleName="修改用户手机号")
	public int modifyUserMobile(String oldMobile,String newMobile) throws Exception {
		Map<String,String> changeParam = new HashMap<String,String>();
		changeParam.put("oldMobile", oldMobile);
		changeParam.put("newMobile", newMobile);
		return merchUserMapper.changeLoginName(changeParam);
	}
}
