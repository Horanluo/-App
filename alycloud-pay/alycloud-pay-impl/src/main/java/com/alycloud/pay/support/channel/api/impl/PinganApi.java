package com.alycloud.pay.support.channel.api.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alycloud.channel.pingan.PinganFactory;
import com.alycloud.channel.pingan.PinganType;
import com.alycloud.channel.pingan.bean.CommonResult;
import com.alycloud.channel.pingan.bean.PayOrderData;
import com.alycloud.channel.pingan.bean.PayOrderResult;
import com.alycloud.channel.pingan.bean.PayStatusData;
import com.alycloud.channel.pingan.bean.PayStatusResult;
import com.alycloud.channel.pingan.bean.Result;
import com.alycloud.channel.pingan.enums.OrderStatus;
import com.alycloud.channel.pingan.enums.PaymentTag;
import com.alycloud.channel.pingan.support.client.PayOrder;
import com.alycloud.channel.pingan.support.client.PayStatus;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.entity.Channel;
import com.alycloud.modules.entity.QrcodeMerch;
import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.search.Channel4Search;
import com.alycloud.modules.search.QrcodeMerch4Search;
import com.alycloud.pay.feign.ChannelFeign;
import com.alycloud.pay.feign.QrcodeMerchFeign;
import com.alycloud.pay.support.channel.api.ChannelApiAdapter;
import com.alycloud.pay.support.channel.api.ChannelApiException;
import com.alycloud.pay.support.channel.api.ChannelApiExecuteFailException;
import com.alycloud.pay.support.channel.api.ChannelOrderStatus;
import com.alycloud.pay.support.channel.api.ChannelOrderStatusResult;
import com.alycloud.pay.support.channel.api.ChannelQrcodeOrderResult;
import com.alycloud.pay.support.channel.api.ChannelWeixinAppOrderResult;

/**
 * 平安渠道接口
 * @author Moyq5
 * @date 2017年10月12日
 */
@Service
public class PinganApi extends ChannelApiAdapter {
	
	private ChannelFeign channelFeign;
	private QrcodeMerchFeign qrcodeMerchFeign;
	private QrcodeOrder order;
	private Channel channel;
	private QrcodeMerch qrcodeMerch;
	private PinganConfig pinganConfig;
	private PinganMerch pinganMerch;
	
//	public PinganApi(QrcodeOrder order) throws ChannelApiException {
//		this.order = order;
//		init();
//	}
	
//	private void init() throws ChannelApiException {
//		channelFeign = SpringContextUtils.getBean(ChannelFeign.class);
//		qrcodeMerchFeign = SpringContextUtils.getBean(QrcodeMerchFeign.class);
//		channel = getChannel();
//		qrcodeMerch = getQrcodeMerch();
//		pinganConfig = new PinganConfig(channel);
//		pinganMerch = new PinganMerch(qrcodeMerch);
//		
//	}
	
	@Override
	public ChannelOrderStatusResult queryQrcodePay(QrcodeOrder order) throws ChannelApiException {
		ChannelOrderStatusResult result = new ChannelOrderStatusResult();
		
		PayStatus client = (PayStatus) PinganFactory.getClient(PinganType.PAY_STATUS, pinganMerch);
		client.setConfig(pinganConfig);// 不指定则使用全局配置
		
		PayStatusData data = new PayStatusData();
		data.setCustomOrderNo(order.getOrderno());
		Result<PayStatusResult> apiResult = client.post(data);
		CommonResult comResult = apiResult.getCommon();
		if (comResult.getCode() == 0) {
			PayStatusResult apiDetail = apiResult.getDetail();
			result.setChannelOrderNo(apiDetail.getOrderNo());
			result.setTradeOrderNo(apiDetail.getTradeNo());
			if (apiDetail.getStatus() == OrderStatus.SUCCESS) {
				result.setStatus(ChannelOrderStatus.SUCCESS);
				result.setMsg("支付成功");
			} else if (apiDetail.getStatus() == OrderStatus.WAIT_PAY 
					|| apiDetail.getStatus() == OrderStatus.WAIT_PWD) {
				result.setStatus(ChannelOrderStatus.NEW);
				result.setMsg("尚未支付");
			} else if (apiDetail.getStatus() == OrderStatus.CANCELED) {
				result.setStatus(ChannelOrderStatus.FAIL);
				result.setMsg("取消支付");
			} else {
				result.setStatus(ChannelOrderStatus.NEW);
				result.setMsg(apiDetail.getRemark());
			}
		} else {
			result.setStatus(ChannelOrderStatus.ERROR);
			result.setMsg(comResult.getMsg());
		}
		return result;
	}

	@Override
	public ChannelQrcodeOrderResult scanPay() throws ChannelApiException {
		PayOrderResult apiDetail = pay(InnerScanType.SCAN);
		ChannelQrcodeOrderResult res = new ChannelQrcodeOrderResult();
		res.setChannelOrderNo(apiDetail.getOrderNo());
		res.setQrcode(order.getChannelUrl());
		res.setTradeOrderNo(apiDetail.getTradeNo());
		return res;
	}

	@Override
	public ChannelQrcodeOrderResult qrcodePay(QrcodeOrder order) throws ChannelApiException {
		PayOrderResult apiDetail = pay(InnerScanType.QRCODE);
		ChannelQrcodeOrderResult res = new ChannelQrcodeOrderResult();
		res.setChannelOrderNo(apiDetail.getOrderNo());
		res.setQrcode(apiDetail.getTradeQrcode());
		res.setTradeOrderNo(apiDetail.getTradeNo());
		return res;
	}

	@Override
	public ChannelQrcodeOrderResult officalPay() throws ChannelApiException {
		PayOrderResult apiDetail = pay(InnerScanType.OFFICAL);
		ChannelQrcodeOrderResult res = new ChannelQrcodeOrderResult();
		res.setChannelOrderNo(apiDetail.getOrderNo());
		res.setQrcode(apiDetail.getJsApiPayUrl());
		res.setTradeOrderNo(apiDetail.getTradeNo());
		return res;
	}

	@Override
	public ChannelWeixinAppOrderResult weixinAppPay() throws ChannelApiException {
		PayOrderResult apiDetail = pay(InnerScanType.APP);
		ChannelWeixinAppOrderResult res = new ChannelWeixinAppOrderResult();
		res.setAppId(order.getAppId());
		res.setNonceStr(apiDetail.getWxAppNonceStr());
		res.setPack(apiDetail.getWxAppPackage());
		res.setPartnerId(apiDetail.getWxAppPartnerId());
		res.setPrepayId(apiDetail.getWxAppPrepayId());
		res.setSign(apiDetail.getWxAppSign());
		res.setTimeStamp(apiDetail.getWxAppTimestamp());
		return res;
	}

	private Channel getChannel() throws ChannelApiException {
		Channel4Search channel4s = new Channel4Search();
		channel4s.setChannelCode(order.getChannelCode());
		RequestBean<Channel4Search> feignData = new RequestBean<Channel4Search>();
		feignData.setData(channel4s);
		List<Channel> channelList;
		try {
			channelList = channelFeign.listByPage(feignData).getData();
		} catch (Exception e) {
			throw new ChannelApiException(e);
		}
		return channelList.get(0);
	}
	
	private QrcodeMerch getQrcodeMerch() throws ChannelApiException {
		QrcodeMerch4Search merch4s = new QrcodeMerch4Search();
		merch4s.setPartnerId(order.getChannelMerchno());
		RequestBean<QrcodeMerch4Search> feignData = new RequestBean<QrcodeMerch4Search>();
		feignData.setData(merch4s);
		List<QrcodeMerch> merchList;
		try {
			merchList = qrcodeMerchFeign.listByPage(feignData).getData();
		} catch (Exception e) {
			throw new ChannelApiException(e);
		}
		return merchList.get(0);
	}
	
	private PayOrderResult pay(InnerScanType innerType) throws ChannelApiException {
		QrcodePayType payType = QrcodePayType.values()[order.getPayType()];
		int amount = order.getAmount().multiply(new BigDecimal("100")).setScale(0).intValue();
		
		PayOrder client = (PayOrder) PinganFactory.getClient(PinganType.PAY_ORDER, pinganMerch);
		client.setConfig(pinganConfig);// 不指定则使用全局配置
		
		PayOrderData data = new PayOrderData();
		//data.setAliExtendParams(aliExtendParams);
		//data.setAliGoodsDetail(aliGoodsDetail);
		if (innerType == InnerScanType.SCAN) {
			data.setAuthCode(order.getChannelUrl());
		}
		//data.setAuthCode("130094788018172172");
		data.setCustomOrderNo(order.getOrderno());
		//data.setDiscountAmount(discountAmount);
		//data.setIgnoreAmount(ignoreAmount);
		if (innerType == InnerScanType.OFFICAL) {
			data.setJumpUrl(channel.getPayReturnUrl());
		}
		data.setOrderName(null == order.getPayerRemark() ? order.getMerchName(): order.getPayerRemark());
		data.setOriginalAmount(new Long(amount).intValue());
		data.setNotifyUrl(channel.getPayNotifyUrl());
		if (payType == QrcodePayType.ALIPAY) {
			data.setPaymentName(PaymentTag.ALIPAY_PAZH.getText());
			data.setPaymentTag(PaymentTag.ALIPAY_PAZH);
		} else if (payType == QrcodePayType.WEIXIN) {
			data.setPaymentName(PaymentTag.WEIXIN.getText());
			data.setPaymentTag(PaymentTag.WEIXIN);
		} else {
			throw new ChannelApiException("不支持支付方式：" + payType);
		}
		data.setRemark(null == order.getPayerRemark() ? order.getMerchName(): order.getPayerRemark());
		//data.setTag(tag);
		//data.setTradeAccount(tradeAccount);
		data.setTradeAmount(new Long(amount).intValue());
		//data.setTradeNo(tradeNo);
		if (innerType == InnerScanType.APP) {
			data.setWxAppId(order.getAppId());
		}
		//data.setWxGoodsTag(wxGoodsTag);
		//data.setWxLimitPay(wxLimitPay);
		
		Result<PayOrderResult> apiResult = client.post(data);
		CommonResult comResult = apiResult.getCommon();
		if (comResult.getCode() != 0) {
			throw new ChannelApiExecuteFailException(comResult.getMsg());
		}
		
		return apiResult.getDetail();
	}

	public enum InnerScanType {
		QRCODE,SCAN,OFFICAL,APP
	}

}
