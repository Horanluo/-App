package com.alycloud.schedules.proxy;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.exception.ServiceException;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.core.utils.JSONUtils;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.modules.entity.channel.ChannelBean;
import com.alycloud.modules.entity.channel.ResBean;
import com.alycloud.schedules.feign.MerchVirtualTransFeign;
import com.alycloud.schedules.feign.PaymentTransFeign;
import com.alycloud.schedules.feign.ProxyFeign;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OutBatchProxyOrderQuery {

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
		List<MerchVirtualTrans> list = merchVirtualTransFeign.getBatchList(merchVirtualTrans);
		
		if(null==list||list.size()<1){
			log.info("没有批量代付的待处理订单");
			return ;
		}
		
		for(MerchVirtualTrans mvt:list){
			JSONObject proxyJsonResult = proxyFeign.merchBatchProxyQuery(mvt);
			log.info("商户批量代付响应结果:{},批量代付批次号:{}",proxyJsonResult,mvt.getBatchno());
			
			if("error".equals(proxyJsonResult.getString("result"))){
				continue;
			}
			
			try {
				ResBean resbean = JSONUtils.json2pojo(proxyJsonResult.toString(), ResBean.class);
				String result = resbean.getResult();
				if(!"success".equals(result)){
					continue;
				}
				for(ChannelBean channel:resbean.getData()){
					log.info("批量代付返回data信息:{}",channel);
					if("success".equals(result)&&"成功".equals(channel.getStatus())){
						mvt.setPayStatus(2);
						mvt.setPayMsg("出款成功");
						mvt.setProcessTime(DateFormat.DATE_TIME.format());
						mvt.setTransRefno(channel.getPayBusinessNumber());
						
						merchVirtualTransFeign.updatePayStatus(mvt);
						paymentTransFeign.modifyMerchRecord(mvt);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
