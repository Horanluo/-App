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
import com.alycloud.modules.entity.AgentVirtualTrans;
import com.alycloud.schedules.feign.AgentVirtualTransFeign;
import com.alycloud.schedules.feign.PaymentTransFeign;
import com.alycloud.schedules.feign.ProxyFeign;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProxyOrderQuery {

	@Autowired
	private ProxyFeign proxyFeign;
	@Autowired
	private AgentVirtualTransFeign agentVirtualTransFeign;
	@Autowired
	private PaymentTransFeign paymentTransFeign;
	
	@Scheduled(cron="0 0/1 0-23 * * ?")
	public void process() throws ServiceException {
		AgentVirtualTrans agentVirtualTrans = new AgentVirtualTrans();
		agentVirtualTrans.setPayStatus(1);
		List<AgentVirtualTrans> list = agentVirtualTransFeign.getAgentVirtualTrans(agentVirtualTrans);
		
		if(null==list||list.size()<1){
			return ;
		}
		
		for(AgentVirtualTrans avi:list){
			JSONObject proxyJsonResult = proxyFeign.agentSingleProxyQuery(avi);
			log.info("单笔代付响应结果:{},订单号:{}",proxyJsonResult,avi.getTraceno());
			
			log.info(proxyJsonResult.getString("data"));
			Map<String, Object> proxyDataResult = StrUtil.getValue(proxyJsonResult.getString("data"));
			log.info("代付data结果:{}",proxyDataResult);
			log.info("代付响应结果:{},状态:{}",proxyJsonResult.getString("result"),proxyDataResult.get("status"));
			
			if("success".equals(proxyJsonResult.getString("result"))&&"成功".equals(proxyDataResult.get("status"))){
				avi.setPayStatus(2);
				avi.setPayMsg("出款成功");
				avi.setProcessTime(DateFormat.DATE_TIME.format());
				avi.setTransRefno((String)proxyDataResult.get("businessrecordnumber"));
			}
			agentVirtualTransFeign.modifyAgentVirtualTrans(avi);
			paymentTransFeign.modifyRecord(avi);
		}
	}
}
