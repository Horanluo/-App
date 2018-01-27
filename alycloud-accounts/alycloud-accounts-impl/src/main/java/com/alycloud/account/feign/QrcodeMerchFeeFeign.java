package com.alycloud.account.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.QrcodeMerchFee;

/**
 * 
 * @author Moyq5
 * @date 2017年10月29日
 */
@FeignClient(name = "alycloud-pay")
public interface QrcodeMerchFeeFeign {
	
	@RequestMapping("/qrcodeMerchFee/delByMerchno")
	public ResultBean<Object> delByMerchno(RequestBean<String> data);

	@RequestMapping("/qrcodeMerchFee/addList")
	public ResultBean<Object> addList(RequestBean<List<QrcodeMerchFee>> data);
	
}
