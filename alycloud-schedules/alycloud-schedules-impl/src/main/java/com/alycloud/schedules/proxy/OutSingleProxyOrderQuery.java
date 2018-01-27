package com.alycloud.schedules.proxy;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.exception.ServiceException;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.schedules.feign.MerchVirtualTransFeign;
import com.alycloud.schedules.feign.PaymentTransFeign;
import com.alycloud.schedules.feign.ProxyFeign;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OutSingleProxyOrderQuery {

	@Autowired
	private ProxyFeign proxyFeign;
	@Autowired
	private MerchVirtualTransFeign merchVirtualTransFeign;
	@Autowired
	private PaymentTransFeign paymentTransFeign;
	
	@Scheduled(cron="0 0/1 0-23 * * ?")
	public void process() throws ServiceException {
		MerchVirtualTrans merchVirtualTrans = new MerchVirtualTrans();
		merchVirtualTrans.setPayStatus(1);
		List<MerchVirtualTrans> list = merchVirtualTransFeign.getMerchVirtualTrans(merchVirtualTrans);
		
		if(null==list||list.size()<1){
			log.info("没有单笔代付的待处理订单");
			return ;
		}
		
		for(MerchVirtualTrans mvt:list){
			JSONObject proxyJsonResult = proxyFeign.merchSingleProxyQuery(mvt);
			log.info("商户单笔代付响应结果:{},订单号:{}",proxyJsonResult,mvt.getTraceno());
			
			log.info(proxyJsonResult.getString("data"));
			Map<String, Object> proxyDataResult = StrUtil.getValue(proxyJsonResult.getString("data"));
			log.info("商户代付data结果:{}",proxyDataResult);
			log.info("商户代付响应结果:{},状态:{}",proxyJsonResult.getString("result"),proxyDataResult.get("status"));
			
			if("success".equals(proxyJsonResult.getString("result"))&&"成功".equals(proxyDataResult.get("status"))){
				mvt.setPayStatus(2);
				mvt.setPayMsg("出款成功");
				mvt.setProcessTime(DateFormat.DATE_TIME.format());
				mvt.setTransRefno((String)proxyDataResult.get("businessrecordnumber"));
				
				merchVirtualTransFeign.updatePayStatus(mvt);
				paymentTransFeign.modifyMerchRecord(mvt);
			}
		}
	}
}
