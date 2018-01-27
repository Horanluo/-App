package com.alycloud.financial.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.QrcodeTrans;
import com.alycloud.modules.search.QrcodeTrans4Search;

/**
 * @author Moyq5
 * @date 2017年11月7日
 */
@FeignClient(name = "alycloud-financial")
public interface QrcodeTransFeign {
	
	@RequestMapping("/qrcodeTrans/listByPage")
	public ResultBean<List<QrcodeTrans>> listByPage(@RequestBody RequestBean<QrcodeTrans4Search> reqBody);

}
