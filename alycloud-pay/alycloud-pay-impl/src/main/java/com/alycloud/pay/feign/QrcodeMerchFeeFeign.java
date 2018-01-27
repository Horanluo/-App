package com.alycloud.pay.feign;

import java.math.BigDecimal;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.search.QrcodeMerchFee4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月17日
 */
@FeignClient(name = "alycloud-pay")
public interface QrcodeMerchFeeFeign {
	
	@RequestMapping("/qrcodeMerchFee/getRate")
	public ResultBean<BigDecimal> getRate(RequestBean<QrcodeMerchFee4Search> reqData);

}
