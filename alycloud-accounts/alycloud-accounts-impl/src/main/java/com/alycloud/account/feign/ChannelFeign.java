package com.alycloud.account.feign;

import java.util.List;
import java.util.Map;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alycloud.channel.yufu.merch.bean.MerchApplyResult;
import com.alycloud.channel.yufu.merch.bean.MerchQueryData;
import com.alycloud.channel.yufu.merch.bean.MerchQueryResult;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.Channel;
import com.alycloud.modules.search.Channel4Search;
import com.alycloud.modules.vo.ReportMerchFeeVO;

/**
 * 
 * @author Moyq5
 * @date 2017年10月18日
 */
@FeignClient(name = "alycloud-pay")
public interface ChannelFeign {
	
	@RequestMapping("/channel/listByPage")
	public ResultBean<List<Channel>> listByPage(RequestBean<Channel4Search> feignData);
	@RequestMapping("/yufuChannel/recordYUFUFee")
	public MerchApplyResult recordYUFUFee(RequestBean<ReportMerchFeeVO> feignData);
	@RequestMapping("/hxtcChannel/recordHXTCFee")
	public Map<String, Object> recordHXTCMerchFee(RequestBean<Map<String, String>> feignData);
	@RequestMapping("/yufuChannel/queryAuditResult")
	public MerchQueryResult queryAuditResult(MerchQueryData data);
}
