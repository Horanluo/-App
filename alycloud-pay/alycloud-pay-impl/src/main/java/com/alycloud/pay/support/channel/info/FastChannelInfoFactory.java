package com.alycloud.pay.support.channel.info;

import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.search.FastChannelInfo;
import com.alycloud.pay.support.channel.info.impl.HxtcFastChannelInfo;
import com.alycloud.pay.support.channel.info.impl.HxtcJFFastChannelInfo;
import com.alycloud.pay.support.channel.info.impl.PayecoFastChannelInfo;
import com.alycloud.pay.support.channel.info.impl.YufuFastChannelInfo;

/**
 * 快捷支付渠道信息工厂类
 * @author Moyq5
 * @date 2017年4月20日
 */
public abstract class FastChannelInfoFactory {

	public static FastChannelInfo getDefChannel() throws ChannelInfoNotFoundException {
		return getChannel(SysChannelType.YUFU) ;
	}

	public static FastChannelInfo getChannel(SysChannelType type) throws ChannelInfoNotFoundException {
		if (type == SysChannelType.PAYECO) {
			return new PayecoFastChannelInfo();
		} else if (type == SysChannelType.YUFU) {
			return new YufuFastChannelInfo();
		}else if (type == SysChannelType.HXTC) {
            return new HxtcFastChannelInfo();
        }else if (type == SysChannelType.HXTC_JF) {
            return new HxtcJFFastChannelInfo();
        }
		
		// else if {...
		throw new ChannelInfoNotFoundException("找不到渠道信息["+ type +"]");
	}
}
