package com.alycloud.account.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.QrcodeAgentFee;

/**
 * 
 * @author Moyq5
 * @date 2017年10月29日
 */
@FeignClient(name = "alycloud-pay")
public interface QrcodeAgentFeeFeign {
	
	@RequestMapping("/qrcodeAgentFee/delByAgentno")
	public ResultBean<Object> delByAgentno(RequestBean<String> data);

	@RequestMapping("/qrcodeAgentFee/batchAdd")
	public ResultBean<Object> batchAdd(RequestBean<List<QrcodeAgentFee>> data);
	
}
