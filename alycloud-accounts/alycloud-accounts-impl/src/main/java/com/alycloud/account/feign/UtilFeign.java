package com.alycloud.account.feign;

import java.util.Map;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.vo.DownLoadImgVO;
import com.alycloud.modules.vo.SaveCardVO;

/**
 * @author Horanluo
 * @date 2017年11月20日
 */
@FeignClient(name = "alycloud-pay")
public interface UtilFeign {

	@RequestMapping("/util/sendSms")
	public SingleResult<String> sendSms(String mobile);
	@RequestMapping("/util/downLoadImg")
	public Map<Object,Object> downLoadImg(RequestBean<DownLoadImgVO> reqData);
	@RequestMapping("/util/certifyCardMsg")
	public JSONObject certifyCardMsg(SaveCardVO feignData);
	@RequestMapping("/util/getCardDetail")
	public String getCardDetail(String cardNo);
}
