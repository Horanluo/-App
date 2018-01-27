package com.alycloud.financial.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.search.MerchInfo4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月23日
 */
@FeignClient(name = "alycloud-accounts")
public interface MerchInfoFeign {
	
	@RequestMapping("/merchInfo/getByMerchno")
	public ResultBean<MerchInfo> getByMerchno(RequestBean<String> requestData);
	
	@RequestMapping("/merchInfo/getById")
	public RequestBean<MerchInfo> getById(RequestBean<Integer> feignData);
	
	@RequestMapping("/merchInfo/listByPage")
	public ResultBean<List<MerchInfo>> listByPage(RequestBean<MerchInfo4Search> reqBody);


}
