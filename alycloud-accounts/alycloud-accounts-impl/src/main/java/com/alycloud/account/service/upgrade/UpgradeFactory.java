package com.alycloud.account.service.upgrade;

import java.util.List;

import com.alycloud.account.feign.AgentInfoFeign;
import com.alycloud.account.feign.MerchInfoFeign;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.search.AgentInfo4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月29日
 */
public abstract class UpgradeFactory {

	public static Context getContext(MerchUser merchUser) throws Exception {
		Upgrader upgrader = new UserUpgrader(merchUser);
		boolean isAgentExists = isAgentExists(merchUser);
		if (merchUser.getUserRank() > 1 && !isAgentExists) {
			upgrader = new CreateAgentInfo(upgrader);
			upgrader = new CreateAgentUser(upgrader);
			upgrader = new CreateAgentRelate(upgrader);
			upgrader = new CreateAgentVirtualCard(upgrader);
			upgrader = new CreateQrcodeAgentFee(upgrader);
		} else if (isAgentExists) {
			upgrader = new CreateQrcodeAgentFee(upgrader);
		}
		upgrader = new CreateQrcodeMerchFee(upgrader);
		Context context = new Context(upgrader);
		return context;
	}
	
	private static boolean isAgentExists(MerchUser merchUser) throws Exception {
		MerchInfoFeign merchInfoFeign = SpringContextUtils.getBean(MerchInfoFeign.class);
		AgentInfoFeign agentInfoFeign = SpringContextUtils.getBean(AgentInfoFeign.class);
		RequestBean<Integer> merchFeignData = new RequestBean<Integer>();
		merchFeignData.setData(merchUser.getMerchId());
		MerchInfo merch = merchInfoFeign.getById(merchFeignData).getData();
		
		AgentInfo4Search agent4s = new AgentInfo4Search();
		agent4s.setMobile(merch.getMobile());
		RequestBean<AgentInfo4Search> agentFeignData = new RequestBean<AgentInfo4Search>();
		agentFeignData.setData(agent4s);
		List<AgentInfo> agentList = agentInfoFeign.listByPage(agentFeignData).getData();
		if (agentList.size() > 0) {
			return true;
		}
		return false;
	}
}
