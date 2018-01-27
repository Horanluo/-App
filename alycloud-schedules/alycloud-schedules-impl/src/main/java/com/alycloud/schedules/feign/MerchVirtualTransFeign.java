package com.alycloud.schedules.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.modules.entity.MerchVirtualTrans;

@FeignClient(name = "alycloud-financial")
public interface MerchVirtualTransFeign {

	@RequestMapping("/MerchVirtualTrans/getMerchVirtualTrans")
	public List<MerchVirtualTrans> getMerchVirtualTrans(MerchVirtualTrans merchVirtualTrans);
	
	@RequestMapping("/MerchVirtualTrans/updatePayStatus")
	public Integer updatePayStatus(MerchVirtualTrans merchVirtualTrans);
	
	@RequestMapping("/MerchVirtualTrans/getBatchList")
	public List<MerchVirtualTrans> getBatchList(MerchVirtualTrans merchVirtualTrans);
	
	@RequestMapping("/MerchVirtualTrans/getInfoByBatchno")
	public List<MerchVirtualTrans> getInfoByBatchno(String batchno);
}
