package com.alycloud.account.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.account.api.IGradeFeeService;
import com.alycloud.account.feign.ChannelFeign;
import com.alycloud.account.mapper.GradeFeeMapper;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.entity.Channel;
import com.alycloud.modules.entity.GradeFee;
import com.alycloud.modules.enums.ChannelStatus;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.search.Channel4Search;
import com.alycloud.modules.search.GradeFee4Search;

/**
 * 商户等级费率Service
 * @author Moyq5
 * @date 2017年10月29日
 */
@Service
public class GradeFeeService implements IGradeFeeService {

	@Autowired
	private GradeFeeMapper gradeFeeMapper;
	@Autowired
	private ChannelFeign channelFeign;

	@Override
	public List<GradeFee> list(GradeFee4Search fee4s) {
		return gradeFeeMapper.list(fee4s);
	}

	@Override
	public List<Map<String,Object>> listGroup4Channel(GradeFee4Search fee4s) {
		List<GradeFee> gradeFeeList = gradeFeeMapper.list(fee4s);
		
		Channel4Search channel4s = new Channel4Search();
		channel4s.setPageSize(1000);
		channel4s.setStatus(ChannelStatus.ON.ordinal());
		RequestBean<Channel4Search> channelData4s = new RequestBean<Channel4Search>();
		channelData4s.setData(channel4s);
		List<Channel> channelList = channelFeign.listByPage(channelData4s).getData();
		int channelType;
		Map<String, Map<String,Object>> map = new HashMap<String, Map<String,Object>>();
		for (GradeFee gradeFee: gradeFeeList) {
			for (Channel channel: channelList) {
				channelType = SysChannelType.getByCode(channel.getChannelCode()).ordinal();
				if ((gradeFee.getChannelType()&(1<<channelType)) != (1<<channelType)) {
					continue;
				}
				Map<String,Object> channelMap = map.get(channel.getChannelCode());
				if (null == channelMap) {
					Map<SysSettleType, Map<String,Object>> rateMap = new HashMap<SysSettleType, Map<String,Object>>();
					channelMap = new HashMap<String, Object>();
					if("YUFU".equals(channel.getChannelCode())){
						channelMap.put("channelName", "银联快捷大额A");
					}
					if("HXTC".equals(channel.getChannelCode())){
						channelMap.put("channelName", "银联快捷大额B");
					}
					if("HXTC_JF".equals(channel.getChannelCode())){
						channelMap.put("channelName", "银联快捷大额C(带积分)");
					}
					channelMap.put("rates", rateMap);
					map.put(channel.getChannelCode(), channelMap);
				}
				@SuppressWarnings("unchecked")
				Map<SysSettleType, Map<String,Object>> rateMap = ((Map<SysSettleType,Map<String,Object>>)channelMap.get("rates"));
				if ((gradeFee.getSettleType()&(1<<SysSettleType.T0.ordinal())) == (1<<SysSettleType.T0.ordinal())) {
					Map<String,Object> item = new HashMap<String, Object>();
					item.put("settleType", SysSettleType.T0.ordinal());
					item.put("fee", gradeFee.getFee());
					item.put("rate", gradeFee.getRate());
					item.put("amountMax", channel.getAmountMax());
					rateMap.put(SysSettleType.T0, item);
				} else if (null == rateMap.get(SysSettleType.T0)) {
					rateMap.put(SysSettleType.T0, new HashMap<String, Object>());
				}
				if ((gradeFee.getSettleType()&(1<<SysSettleType.T1.ordinal())) == (1<<SysSettleType.T1.ordinal())) {
					Map<String,Object> item = new HashMap<String, Object>();
					item.put("settleType", SysSettleType.T1.ordinal());
					item.put("fee", gradeFee.getFee());
					item.put("rate", gradeFee.getRate());
					item.put("amountMax", channel.getAmountMax());
					rateMap.put(SysSettleType.T1, item);
				} else if (null == rateMap.get(SysSettleType.T1)) {
					rateMap.put(SysSettleType.T1, new HashMap<String, Object>());
				}
			}
		}
		List<Map<String,Object>> destList = new ArrayList<Map<String,Object>>();
		for (Map.Entry<String, Map<String,Object>> entry: map.entrySet()) {
			Map<String,Object> channelItem = entry.getValue();
			@SuppressWarnings("unchecked")
			Map<SysSettleType,Map<String,Object>> rateMap = ((Map<SysSettleType,Map<String,Object>>)channelItem.get("rates"));
			List<Map<String,Object>> rateList = new ArrayList<Map<String,Object>>();
			for (Map.Entry<SysSettleType,Map<String,Object>> entryFee: rateMap.entrySet()) {
				rateList.add(entryFee.getValue());
			}
			channelItem.put("rates", rateList);
			destList.add(channelItem);
		}
		return destList;
	}

	@Override
	@ServiceLogAnnotation(moduleName="查询商户等级费率，计算提现手续费")
	public List<GradeFee> queryGradeInfo(String merchno) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("merchno", merchno);
		params.put("channelType", "4");
		return gradeFeeMapper.queryGradeInfo(params);
	}
}
