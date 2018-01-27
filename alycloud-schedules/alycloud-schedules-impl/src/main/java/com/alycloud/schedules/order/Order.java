//package com.alycloud.schedules.order;
//
//import java.util.List;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.alycloud.core.exception.ServiceException;
//import com.alycloud.core.modules.ResultBean;
//import com.alycloud.modules.entity.ChannelSubmerchInfo;
//import com.alycloud.schedules.yufu.task.YufuCheckTask;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class Order {
//
//	/**
//	 * 定时更新 御付通道审核结果
//	 * @author 罗根恒
//	 * @date 2017年11月2日
//	 * @param yufu
//	 * @throws Exception
//	 */
//	@Scheduled(cron="0 0/1 0-23 * * ?")
//	public void process() throws ServiceException {
//		ChannelSubmerchInfo csmi = new ChannelSubmerchInfo();
//		csmi.setChannelCode("YUFU");
//		csmi.setAduitStatus("0");
//		
//		ResultBean<List<ChannelSubmerchInfo>> result = channelSubMerchInfoFeign.list(csmi);
//		log.info("找到{}个未审核的御付（快捷）商户", result.getData().size());
//		int num = 0;
//		for (ChannelSubmerchInfo csi: result.getData()) {
//			num++;
//			log.info("[" + num + "/" + result.getData().size() + "]开始检查御付（快捷）商户[{}]状态", csi.getId());
//			if(!checkUpdateState(csi)){
//				continue;
//			}
//		}
//	}
//}
