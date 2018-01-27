package com.alycloud.schedules.yufu.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alycloud.channel.yufu.merch.bean.MerchQueryData;
import com.alycloud.channel.yufu.merch.bean.MerchQueryResult;
import com.alycloud.channel.yufu.merch.enums.MerchState;
import com.alycloud.core.exception.ServiceException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.schedules.feign.ChannelFeign;
import com.alycloud.schedules.feign.ChannelSubMerchInfoFeign;
import com.alycloud.schedules.feign.MerchInfoFeign;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class YufuCheckTask {

	@Autowired
	private ChannelSubMerchInfoFeign channelSubMerchInfoFeign;
	@Autowired
	private MerchInfoFeign merchInfoFeign;
	@Autowired
	private ChannelFeign channelFeign;
	/**
	 * 定时更新 御付通道审核结果
	 * @author 罗根恒
	 * @date 2017年11月2日
	 * @param yufu
	 * @throws Exception
	 */
	@Scheduled(cron="0 0/1 0-23 * * ?")
	public void process() throws ServiceException {
		ChannelSubmerchInfo csmi = new ChannelSubmerchInfo();
		csmi.setChannelCode("YUFU");
		csmi.setAduitStatus("0");
		
		ResultBean<List<ChannelSubmerchInfo>> result = channelSubMerchInfoFeign.list(csmi);
		log.info("找到{}个未审核的御付（快捷）商户", result.getData().size());
		int num = 0;
		for (ChannelSubmerchInfo csi: result.getData()) {
			num++;
			log.info("[" + num + "/" + result.getData().size() + "]开始检查御付（快捷）商户[{}]状态", csi.getId());
			if(!checkUpdateState(csi)){
				continue;
			}
		}
	}
	
	/**
	 * 获取御付接口查询并更新商户审核状态
	 * @author Horanluo
	 * @date 2017年11月3日
	 * @param Horanluo
	 * @throws Exception
	 */
	private boolean checkUpdateState(ChannelSubmerchInfo csmi) throws ServiceException {
		// 通过御付接口查询状态
		MerchQueryData data = new MerchQueryData();
		RequestBean<String> feignData = new RequestBean<String>();
		feignData.setMerchno(csmi.getMerchno());
		ResultBean<MerchInfo> merchResult = merchInfoFeign.getByMerchno(feignData);
		String phoneNo = merchResult.getData().getMobile();
		data.setPhone(phoneNo);
		data.setSerialNo("" + new Date().getTime() + (int)(Math.random() * 999));
		MerchQueryResult result = channelFeign.queryAuditResult(data);
		if (!result.getResultCode().equals("0000")||(!result.getState().equals(MerchState.PASS))) {
			log.debug("御付（快捷）商户状态查询失败：[{}]{}", result.getResultCode(), result.getResultMessage());
			throw new ServiceException("S0001","上报御付渠道失败");
		}
		
		ChannelSubmerchInfo channelSubmerchInfo = csmi;
		channelSubmerchInfo.setAduitStatus("1");
		channelSubmerchInfo.setChannelSubmerchno(result.getMerchId());
		channelSubmerchInfo.setRemark(result.getState() == MerchState.PASS ? "审核通过": result.getRemark());
		channelSubmerchInfo.setYufuOneCodeUrl(result.getOneCodeUrl());
		channelSubmerchInfo.setYufuKjKey(result.getKjKey());
		channelSubmerchInfo.setYufuTermId(result.getTermId());
		return channelSubMerchInfoFeign.updateRecord(channelSubmerchInfo)>0 ? true:false;
	}
}
