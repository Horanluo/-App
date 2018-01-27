package com.alycloud.account.service.upgrade;

import java.util.List;

import org.mortbay.log.Log;

import com.alycloud.account.feign.AgentInfoFeign;
import com.alycloud.account.feign.AgentRolerFeign;
import com.alycloud.account.feign.AgentUserFeign;
import com.alycloud.account.feign.MerchInfoFeign;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentRoler;
import com.alycloud.modules.entity.AgentUser;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.enums.AgentUserStatus;
import com.alycloud.modules.search.AgentInfo4Search;
import com.alycloud.modules.search.AgentRoler4Search;

/**
 * 创建代理商操作员
 * @author Moyq5
 * @date 2017年10月27日
 */
public class CreateAgentUser extends Decorator {

	private MerchInfoFeign merchInfoFeign;
	private AgentInfoFeign agentInfoFeign;
	private AgentUserFeign agentUserFeign;
	private AgentRolerFeign agentRolerFeign;
	
	public CreateAgentUser(Upgrader userUpgrader) {
		super(userUpgrader);
		merchInfoFeign = SpringContextUtils.getBean(MerchInfoFeign.class);
		agentInfoFeign = SpringContextUtils.getBean(AgentInfoFeign.class);
		agentUserFeign = SpringContextUtils.getBean(AgentUserFeign.class);
		agentRolerFeign = SpringContextUtils.getBean(AgentRolerFeign.class);
	}

	@Override
	public MerchUser upgrade() throws Exception {
		MerchUser user = super.upgrade();
		createAgentUser(user);
		return user;
	}
	
	private AgentUser createAgentUser(MerchUser merchUser) throws Exception {
		RequestBean<Integer> merchFeignData = new RequestBean<Integer>();
		merchFeignData.setData(merchUser.getMerchId());
		MerchInfo merch = merchInfoFeign.getById(merchFeignData).getData();
		
		AgentInfo4Search agent4s = new AgentInfo4Search();
		agent4s.setMobile(merch.getMobile());
		RequestBean<AgentInfo4Search> agentFeignData = new RequestBean<AgentInfo4Search>();
		agentFeignData.setData(agent4s);
		List<AgentInfo> agentList = agentInfoFeign.listByPage(agentFeignData).getData();
		if (agentList.size() != 1) {
			Log.debug("代理商[{}]信息有误: size->{}", merch.getMobile(), agentList.size());
			throw new Exception("商户信息有误");
		}
		AgentInfo newAgent = agentList.get(0);
		
		// 找要分配角色
		Integer roleId = getAgentRolerId(newAgent.getBranchno());
		
		// 创建代理商管理员
		AgentUser newUser = new AgentUser();
		newUser.setAddress(newAgent.getAddress());
		newUser.setAgentno(newAgent.getAgentno());
		newUser.setBranchno(newAgent.getBranchno());
		newUser.setEmail(newAgent.getEmail());
		//newUser.setErrorCount(errorCount);
		//newUser.setId(id);
		//newUser.setLastLogin(lastLogin);
		//newUser.setLoginErrorDate(loginErrorDate);
		newUser.setLoginName(newAgent.getMobile());
		newUser.setMobile(newAgent.getMobile());
		//newUser.setOpenId(openId);
		newUser.setPassword(merchUser.getPassword());
		newUser.setRolerId(roleId);
		newUser.setStatus(AgentUserStatus.NORMAL.ordinal());
		newUser.setTelephone(newAgent.getTelephone());
		newUser.setTrueName(newAgent.getLegalName());
		
		RequestBean<AgentUser> userData = new RequestBean<AgentUser>();
		userData.setData(newUser);
		newUser = agentUserFeign.add(userData).getData();
		return newUser;
	}

	/**
	 * 获取机构下的代理商管理员角色ID
	 * @author Moyq5
	 * @throws Exception 
	 * @date 2017年7月15日
	 */
	private Integer getAgentRolerId(String branchno) throws Exception {
		AgentRoler4Search roler4s = new AgentRoler4Search();
		roler4s.setBranchno(branchno);
		roler4s.setType(1);
		RequestBean<AgentRoler4Search> feignData = new RequestBean<AgentRoler4Search>();
		feignData.setData(roler4s);
		List<AgentRoler> rolerList = agentRolerFeign.listByPage(feignData).getData();
		if (rolerList.size() == 0) {
			throw new Exception("代理商角色里面没有管理员的角色");
		}
		return rolerList.get(0).getId();
	}

}
