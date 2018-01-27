package com.alycloud.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alycloud.account.api.IMerchService;
import com.alycloud.account.api.IMerchUserService;
import com.alycloud.account.api.IRegistUserService;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.vo.RegistUserVO;

/**
 * 注册用户服务接口
 * @author Administrator
 */
@Service
@ConfigurationProperties(prefix="hfbank")
public class RegistUserServiceImpl implements IRegistUserService{

	@Autowired
	private IMerchUserService merchUserService;
	
	@Autowired
	private IMerchService merchService;
	@Override
	@Transactional
	public boolean RegistUser(RequestBean<RegistUserVO> reqData,MerchUser refer){
		//新增商户信息记录
		int merchId = merchService.addMerchInfo(reqData,refer);
		
		//新增用户表记录
  		int merchUserRes;
		try {
			merchUserRes = merchUserService.addMerchUser(reqData,merchId,refer);
			if(merchUserRes<=0||merchId<=0){
				return false;
			}
			//merchService.upgradeInviter(refer.getMerchId());
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return true;
	}
}
