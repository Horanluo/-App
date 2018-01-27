package com.alycloud.financial.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.search.FastOrder4Search;

/**
 * @author Moyq5
 * @date 2017年11月9日
 */
@FeignClient(name = "alycloud-pay")
public interface FastOrderFeign {
	
	@RequestMapping("/fastOrder/listByPage")
	public ResultBean<List<FastOrder>> listByPage(@RequestBody RequestBean<FastOrder4Search> reqBody);


}
