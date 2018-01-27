package com.alycloud.pay.support.channel.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.pay.support.channel.api.impl.HxtcApi;
import com.alycloud.pay.support.channel.api.impl.PayecoApi;
import com.alycloud.pay.support.channel.api.impl.PinganApi;
import com.alycloud.pay.support.channel.api.impl.SaoBeiQrcodeApi;
import com.alycloud.pay.support.channel.api.impl.YufuFastApi;
import com.alycloud.pay.support.channel.api.impl.YufuQrcodeApi;

/**
 * 渠道接口工厂类
 * @author Moyq5
 * @date 2017年8月2日
 */
@Component
public abstract class ChannelApiFactory {

	@Autowired
	private static SaoBeiQrcodeApi saoBeiQrcodeApi;
	
	public static ChannelApi getChannelApi(FastOrder order) throws ChannelApiNotFoundException {
		SysChannelType channelType = SysChannelType.values()[order.getChannelType()];
		if (channelType == SysChannelType.PAYECO) {
			return new PayecoApi(order.getOrderno());
		} else if (channelType == SysChannelType.YUFU) {
			return new YufuFastApi(order);
		} else if (channelType == SysChannelType.HXTC) {
			return new HxtcApi(order);
		}else if (channelType == SysChannelType.HXTC_JF) {
			return new HxtcApi(order);
		}
		throw new ChannelApiNotFoundException("找不到渠道["+ channelType +"]的交易接口");
	}
	
	public static ChannelApi getQrcodeChannelApi(String channelCode) throws ChannelApiNotFoundException, ChannelApiException {
		
		// 历史原因，通过channelCode去识别渠道类型
		SysChannelType channelType = SysChannelType.getByCode(channelCode);
		
		if (channelType == SysChannelType.YUFU_QRCODE) {
			return new YufuQrcodeApi();
		} else if (channelType == SysChannelType.PINGAN) {
			return new PinganApi();
		}else if (channelType == SysChannelType.SAO_BEI) {
			return saoBeiQrcodeApi;
		}// else if {...
		throw new ChannelApiNotFoundException("找不到渠道["+ channelType +"]的交易接口");
	}
}
