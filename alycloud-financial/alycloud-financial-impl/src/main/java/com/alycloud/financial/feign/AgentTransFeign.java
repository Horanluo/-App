package com.alycloud.financial.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.AgentTrans;
import com.alycloud.modules.search.AgentTrans4Search;

/**
 * @author Moyq5
 * @date 2017年11月5日
 */
@FeignClient(name = "alycloud-financial")
public interface AgentTransFeign {
	
	@RequestMapping("/agentTrans/listByPage")
	public ResultBean<List<AgentTrans>> listByPage(RequestBean<AgentTrans4Search> reqBody);

	@RequestMapping("/agentTrans/count")
	public ResultBean<Integer> count(RequestBean<AgentTrans4Search> reqBody);


}
