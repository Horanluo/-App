package com.alycloud.pay.support.channel.api;

import org.springframework.stereotype.Component;

import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.vo.ExternalQrcodeVO;
import com.alycloud.pay.out.model.OutQueryResult;

@Component
public class ChannelApiAdapter implements ChannelApi {

	@Override
	public ChannelOrderStatusResult query() throws ChannelApiException {
		throw new ChannelApiNotSupportException("渠道不支持查询");
	}

	@Override
	public ChannelWeixinAppOrderResult weixinAppPay() throws ChannelApiException {
		throw new ChannelApiNotSupportException("渠道不支持微信APP支付");
	}

	@Override
	public ChannelQrcodeOrderResult scanPay() throws ChannelApiException {
		throw new ChannelApiNotSupportException("渠道不支持扫码支付");
	}

	@Override
	public ChannelQrcodeOrderResult qrcodePay(QrcodeOrder order) throws ChannelApiException {
		throw new ChannelApiNotSupportException("渠道不支持条码支付");
	}
	
	@Override
	public ChannelQrcodeOrderResult officalPay() throws ChannelApiException {
		throw new ChannelApiNotSupportException("渠道不支持公众号支付");
	}

	@Override
	public ChannelFastOrderResult fastPay() throws ChannelApiException {
		throw new ChannelApiNotSupportException("渠道不支持快捷支付");
	}
	
	public boolean isNeedOpenId() {
		return false;
	}

	@Override
	public ChannelOrderStatusResult queryQrcodePay(QrcodeOrder order) throws ChannelApiException {
		throw new ChannelApiNotSupportException("扫码渠道不支持查询");
	}

	@Override
	public ChannelQrcodeOrderResult outQrcodePay(QrcodeOrder order,ExternalQrcodeVO qrcodeVO) throws ChannelApiException {
		throw new ChannelApiNotSupportException("渠道不支持条码支付");
	}

	@Override
	public OutQueryResult queryOutQrcodePay(ExternalQrcodeVO qrcodeVO) throws ChannelApiException {
		throw new ChannelApiNotSupportException("扫码渠道不支持查询");
	}
}
