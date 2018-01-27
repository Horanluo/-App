package com.alycloud.account.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.vo.QrcodePayDataVO;
import com.alycloud.modules.vo.QrcodePayResultVO;

/**
 * @author Moyq5
 * @date 2017年10月30日
 */
@FeignClient(name = "alycloud-pay")
public interface QrcodeOrderFeign {
	
	@RequestMapping("/qrcodeOrder/qrcodePay")
	public ResultBean<QrcodePayResultVO> qrcodePay(RequestBean<QrcodePayDataVO> reqBody);
}
