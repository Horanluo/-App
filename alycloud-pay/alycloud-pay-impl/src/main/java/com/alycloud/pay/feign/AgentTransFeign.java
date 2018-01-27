package com.alycloud.pay.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;

/**
 * @author Moyq5
 * @date 2017年11月5日
 */
@FeignClient(name = "alycloud-financial")
public interface AgentTransFeign {
	
	@RequestMapping("/agentTrans/addByGradeOrderId")
	public ResultBean<?> addByGradeOrderId(RequestBean<Integer> reqBody);
	
	@RequestMapping("/agentTrans/addByQrcodeOrderno")
	public ResultBean<?> addByQrcodeOrderno(RequestBean<String> reqBody);

	@RequestMapping("/agentTrans/addByFastOrderno")
	public ResultBean<?> addByFastOrderno(RequestBean<String> reqBody);

}
