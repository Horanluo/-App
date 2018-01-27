package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.Channel;
import com.alycloud.modules.search.Channel4Search;

/**
 * 二维码渠道信息
 * @author Moyq5
 * @date 2017年9月28日
 */
public interface ChannelMapper {

	public List<Channel> listByPage(Channel4Search channel4s);
	
	/**
	 * 查询快捷通道信息
	 * @author   曾云龙
	 * @version  V001Z0001
	 * @date     2017年11月20日
	 * @see  [相关类/方法]
	 * @since  [产品/模块版本]
	 */
	public List<Channel> searchFastChannel();

}
