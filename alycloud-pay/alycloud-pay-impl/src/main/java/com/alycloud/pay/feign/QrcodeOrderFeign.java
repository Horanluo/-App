package com.alycloud.pay.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.search.QrcodeOrder4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月17日
 */
@FeignClient(name = "alycloud-pay")
public interface QrcodeOrderFeign {
	
	@RequestMapping("/qrcodeOrder/add")
	public ResultBean<QrcodeOrder> add(RequestBean<QrcodeOrder> requestData);

	@RequestMapping("/qrcodeOrder/listByPage")
	public ResultBean<List<QrcodeOrder>> listByPage(RequestBean<QrcodeOrder4Search> order4s);

	@RequestMapping("/qrcodeOrder/genRefno")
	public ResultBean<String> genRefno();

	@RequestMapping("/qrcodeOrder/mod")
	public ResultBean<String> mod(RequestBean<QrcodeOrder> order);
}
