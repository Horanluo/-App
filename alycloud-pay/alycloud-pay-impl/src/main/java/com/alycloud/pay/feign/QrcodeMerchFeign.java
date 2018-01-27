package com.alycloud.pay.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.QrcodeMerch;
import com.alycloud.modules.search.QrcodeMerch4Search;
import com.alycloud.modules.search.QrcodeMerch4Search4Pay;

/**
 * 
 * @author Moyq5
 * @date 2017年10月18日
 */
@FeignClient(name = "alycloud-pay")
public interface QrcodeMerchFeign {
	
	@RequestMapping("/qrcodeMerch/list4Pay")
	public ResultBean<List<QrcodeMerch>> list4Pay(RequestBean<QrcodeMerch4Search4Pay> reqData);

	@RequestMapping("/qrcodeMerch/listByPage")
	public ResultBean<List<QrcodeMerch>>  listByPage(RequestBean<QrcodeMerch4Search> feignData);

}
