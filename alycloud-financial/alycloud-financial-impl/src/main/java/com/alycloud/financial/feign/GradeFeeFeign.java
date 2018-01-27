package com.alycloud.financial.feign;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.GradeFee;

/**
 * 
 * @author Moyq5
 * @date 2017年10月31日
 */
@FeignClient(name = "alycloud-accounts")
public interface GradeFeeFeign {
	
	@RequestMapping("/gradeFee/queryGradeInfo")
	public ResultBean<List<GradeFee>> queryGradeInfo(String merchno);

}
