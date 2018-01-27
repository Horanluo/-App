package com.alycloud.financial.feign;

import java.math.BigDecimal;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.search.QrcodeAgentFee4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年11月7日
 */
@FeignClient(name = "alycloud-pay")
public interface QrcodeAgentFeeFeign {
	
	@RequestMapping("/qrcodeAgentFee/getRate")
	public ResultBean<BigDecimal> getRate(RequestBean<QrcodeAgentFee4Search> reqData);

}
