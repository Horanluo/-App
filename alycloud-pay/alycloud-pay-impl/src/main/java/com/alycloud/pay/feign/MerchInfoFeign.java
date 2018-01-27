package com.alycloud.pay.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.ReportMark;
import com.alycloud.modules.vo.ReportMerchFeeVO;

/**
 * 
 * @author Moyq5
 * @date 2017年10月17日
 */
@FeignClient(name = "alycloud-accounts")
public interface MerchInfoFeign {
	
	@RequestMapping("/merchInfo/getByMerchno")
	public ResultBean<MerchInfo> getByMerchno(RequestBean<String> reqData);

	@RequestMapping("/merchInfo/upgradeByGradeOrderId")
	public ResultBean<Object> upgradeByGradeOrderId(RequestBean<Integer> reqData);
	
	/**
	 * 商户进件
	 * 
	 * @author   曾云龙
	 * @version  V001Z0001
	 * @date     2017年11月21日
	 * @see  [相关类/方法]
	 * @since  [产品/模块版本]
	 */
	@RequestMapping("/fee/reportMerchFee")
    public ResultBean<ReportMark> reportMerchFee(RequestBean<ReportMerchFeeVO> reqBody);
}
