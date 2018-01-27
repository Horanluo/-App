package com.alycloud.pay.feign;

import java.math.BigDecimal;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.search.QrcodeBranchFee4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月18日
 */
@FeignClient(name = "alycloud-pay")
public interface QrcodeBranchFeeFeign {
	
	@RequestMapping("/qrcodeBranchFee/getRate")
	public RequestBean<BigDecimal> getRate(RequestBean<QrcodeBranchFee4Search> reqData);

}
