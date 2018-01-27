package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.ChannelMerchYufu;
import com.alycloud.modules.entity.ChannelMerchYufuBiz;
import com.alycloud.modules.search.ChannelMerchYufu4Search;


/**
 * 御付渠道商户信息
 * @author Moyq5
 * @date 2017年8月5日
 */
public interface ChannelMerchYufuMapper {

	public void add(ChannelMerchYufu channelMerchYufu, List<ChannelMerchYufuBiz> bizList);

	public int mod(ChannelMerchYufu channelMerchYufu);
	
	public List<ChannelMerchYufu> listByPage(ChannelMerchYufu4Search channelMerchYufu4s);

	public List<String> listNoApplyMerchno();

	public void delByMerchno(String merchno);

}
