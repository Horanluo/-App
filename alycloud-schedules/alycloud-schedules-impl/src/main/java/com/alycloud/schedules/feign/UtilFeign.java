package com.alycloud.schedules.feign;

import java.util.Map;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.vo.DownLoadImgVO;

/**
 * 下载身份认证图片   工具类
 * @author Horanluo
 * @date 2017年11月28日
 */
@FeignClient(name = "alycloud-pay")
public interface UtilFeign {
	
	@RequestMapping("/util/loadImg")
	public Map<Object,Object> loadImg(RequestBean<DownLoadImgVO> reqData);
}
