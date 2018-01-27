package com.alycloud.financial.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.PaymentTrans;

/**
 * 
 * @author Moyq5
 * @date 2017年10月24日
 */
@FeignClient(name = "alycloud-financial")
public interface PaymentTransFeign {
	
	@RequestMapping("/paymentTrans/genRefno")
	public ResultBean<String> genRefno();

	@RequestMapping("/paymentTrans/add")
	public ResultBean<PaymentTrans> add(RequestBean<PaymentTrans> reqData);

}
