package com.alycloud.pay.support.channel.api.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alycloud.channel.support.config.Config;
import com.alycloud.channel.yufu.qrcode.YufuQrcodeApiFactory;
import com.alycloud.channel.yufu.qrcode.YufuQrcodeApiType;
import com.alycloud.channel.yufu.qrcode.bean.OfficalOrderData;
import com.alycloud.channel.yufu.qrcode.bean.OfficalOrderResult;
import com.alycloud.channel.yufu.qrcode.bean.OrderQueryData;
import com.alycloud.channel.yufu.qrcode.bean.OrderQueryResult;
import com.alycloud.channel.yufu.qrcode.bean.ScanSyncOrderData;
import com.alycloud.channel.yufu.qrcode.bean.ScanSyncOrderResult;
import com.alycloud.channel.yufu.qrcode.bean.UnifiedOrderData;
import com.alycloud.channel.yufu.qrcode.bean.UnifiedOrderResult;
import com.alycloud.channel.yufu.qrcode.bean.WeixinAppOrderData;
import com.alycloud.channel.yufu.qrcode.bean.WeixinAppOrderResult;
import com.alycloud.channel.yufu.qrcode.enums.ChannelFlag;
import com.alycloud.channel.yufu.qrcode.enums.RespCode;
import com.alycloud.channel.yufu.qrcode.enums.TransStatus;
import com.alycloud.channel.yufu.qrcode.support.client.OfficalOrder;
import com.alycloud.channel.yufu.qrcode.support.client.OrderQuery;
import com.alycloud.channel.yufu.qrcode.support.client.ScanSyncOrder;
import com.alycloud.channel.yufu.qrcode.support.client.UnifiedOrder;
import com.alycloud.channel.yufu.qrcode.support.client.WeixinAppOrder;
import com.alycloud.modules.entity.Channel;
import com.alycloud.modules.entity.QrcodeMerch;
import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.search.Channel4Search;
import com.alycloud.modules.search.QrcodeMerch4Search;
import com.alycloud.pay.mapper.ChannelMapper;
import com.alycloud.pay.mapper.QrcodeMerchMapper;
import com.alycloud.pay.support.channel.api.ChannelApiAdapter;
import com.alycloud.pay.support.channel.api.ChannelApiException;
import com.alycloud.pay.support.channel.api.ChannelApiExecuteFailException;
import com.alycloud.pay.support.channel.api.ChannelOrderStatus;
import com.alycloud.pay.support.channel.api.ChannelOrderStatusResult;
import com.alycloud.pay.support.channel.api.ChannelQrcodeOrderResult;
import com.alycloud.pay.support.channel.api.ChannelWeixinAppOrderResult;

/**
 * 御付渠道(二维码)接口
 * @author Moyq5
 * @date 2017年8月30日
 */
@Service
public class YufuQrcodeApi extends ChannelApiAdapter {

	private static final Logger log = LoggerFactory.getLogger(YufuQrcodeApi.class);
	private ChannelMapper channelMapper;
	private QrcodeMerchMapper qrcodeMerchMapper;
	private QrcodeOrder order;
//	public YufuQrcodeApi(QrcodeOrder order) {
//		this.order = order;
//		channelMapper = SpringContextUtils.getBean(ChannelMapper.class);
//		qrcodeMerchMapper = SpringContextUtils.getBean(QrcodeMerchMapper.class);
//	}
	
	@Override
	public ChannelOrderStatusResult queryQrcodePay(QrcodeOrder order) {
		ChannelOrderStatusResult result = new ChannelOrderStatusResult();
		
		OrderQuery client = (OrderQuery) YufuQrcodeApiFactory.getClient(YufuQrcodeApiType.ORDER_QUERY);
		OrderQueryData data = new OrderQueryData();
		data.setMerchNo(order.getChannelMerchno());// 渠道商户号
		//data.setOperatorId(operatorId);// 商户操作员编号
		data.setOrderNo(order.getOrderno());// 商户订单号
		//data.setOrgReqId(orgReqId);// 商户请求交易的流水号
		//data.setOrgTransId(orgTransId);// 平台方唯一交易请求流水号
		//data.setTermNo(termNo);// 终端编码
		OrderQueryResult apiResult = client.execute(data);
		
		// 渠道状态转换成对应平台状态
		if (apiResult.getRespCode() == RespCode.SUCCESS) {
			TransStatus status = apiResult.getTransStatus();
			log.info("御付订单[{}]付款状态：{}", 
					new Object[]{order.getOrderno(), status});
			if (status == TransStatus.SUCCESS) {
				result.setStatus(ChannelOrderStatus.SUCCESS);
				result.setMsg("支付成功");
			} else {
				result.setStatus(ChannelOrderStatus.FAIL);
				result.setMsg(status.getText());
			}
		} else {
			result.setStatus(ChannelOrderStatus.ERROR);
			result.setMsg(apiResult.getRespDesc());
		}
		
		return result;
	}

	@Override
	public ChannelWeixinAppOrderResult weixinAppPay() throws ChannelApiException {
		Channel channel = getChannel();
		
		WeixinAppOrder client = (WeixinAppOrder) YufuQrcodeApiFactory.getClient(YufuQrcodeApiType.WEIXIN_APP_ORDER);
		WeixinAppOrderData apiData = new WeixinAppOrderData();
		apiData.setAmount(order.getAmount().multiply(new BigDecimal("100")).setScale(0).toPlainString());// 订单金额，必填，单位为分
		apiData.setChannelFlag(ChannelFlag.WEIXIN);// 支付渠道微信APP，必填，固定值
		//data.setCurrency(currency);// 币种,默认CNY
		//data.setExtraDesc(extraDesc);// 附加信息
		//data.setGoodsName(goodsName);// 商品名称
		apiData.setMerchNo(order.getChannelMerchno());// 商户号
		apiData.setNotifyUrl(channel.getPayNotifyUrl());// 异步通知url，不能带参数，需要向申请白名单
		//data.setOperatorId(operatorId);// 商户操作员编号
		apiData.setOrderNo(order.getOrderno());// 商户订单号,同一商户号和终端号下唯一
		apiData.setReqId(order.getOrderno() + ("" + new Date().getTime()).substring(5));// 请求交易的流水号，同一商户号和终端号下唯一
		apiData.setReqTime(new Date());// 请求时间
		apiData.setSubAppId(order.getAppId());// 接入方微信appid（于微信开放平台分配），必填
		//data.setTermNo(termNo);// 终端号
		
		WeixinAppOrderResult apiResult = client.execute(apiData);
		if (apiResult.getRespCode() != RespCode.SUCCESS) {
			throw new ChannelApiExecuteFailException(apiResult.getRespDesc());
		}
		
		ChannelWeixinAppOrderResult res = new ChannelWeixinAppOrderResult();
		res.setAppId(apiResult.getAppId());
		res.setNonceStr(apiResult.getNonceStr());
		res.setPack(apiResult.getPack());
		res.setPartnerId(apiResult.getPartnerId());
		res.setPrepayId(apiResult.getPrepayId());
		res.setSign(apiResult.getSignStr());
		res.setTimeStamp(apiResult.getTimeStamp());
		return res;
	}

	@Override
	public ChannelQrcodeOrderResult scanPay() throws ChannelApiException {
		
		ScanSyncOrder client = (ScanSyncOrder) YufuQrcodeApiFactory.getClient(YufuQrcodeApiType.SCAN_SYNC_ORDER);
		ScanSyncOrderData data = new ScanSyncOrderData();
		data.setAmount(order.getAmount().multiply(new BigDecimal("100")).setScale(0).toPlainString());// 订单金额，单位为分
		data.setAuthCode(order.getChannelUrl());// 授权码，扫码支付授权码，设备读取用户客户端中的条码或者二维码信息
		//data.setCurrency(currency);// 币种,默认CNY
		data.setExtraDesc(null == order.getPayerRemark() ? order.getMerchno(): order.getPayerRemark());// 附加信息
		//data.setGoodsName(goodsName);// 商品名称
		data.setMerchNo(order.getChannelMerchno());// 渠道商户号
		//data.setOperatorId(operatorId);// 商户操作员编号
		data.setOrderNo(order.getOrderno());// 商户订单号,同一商户号和终端号下唯一
		data.setReqId(order.getOrderno() + ("" + new Date().getTime()).substring(5));// 请求交易的流水号，同一商户号和终端号下唯一
		data.setReqTime(new Date());// 请求时间
		data.setTermNo(order.getMerchno());// 终端编码
		ScanSyncOrderResult apiResult = client.execute(data);
		
		if (apiResult.getRespCode() != RespCode.SUCCESS) {
			throw new ChannelApiExecuteFailException(apiResult.getRespDesc());
		}
		
		ChannelQrcodeOrderResult res = new ChannelQrcodeOrderResult();
		res.setChannelOrderNo(apiResult.getTransId());
		res.setQrcode(order.getChannelUrl());
		//res.setTradeOrderNo(apiResult.getOutOrderNo());
		return res;
	}

	@Override
	public ChannelQrcodeOrderResult qrcodePay(QrcodeOrder order) throws ChannelApiException {
		QrcodePayType payType = QrcodePayType.values()[order.getPayType()];
		Channel channel = getChannel();
		
		// 统一下单接口
		UnifiedOrder client = (UnifiedOrder) YufuQrcodeApiFactory.getClient(YufuQrcodeApiType.UNIFIED_ORDER);
		UnifiedOrderData data = new UnifiedOrderData();
		//data.setAlipayUserId(alipayUserId);// 支付宝uerid
		data.setAmount(order.getAmount().multiply(new BigDecimal("100")).setScale(0).toPlainString());// 订单金额，单位为分
		if (payType == QrcodePayType.ALIPAY) {
			data.setChannelFlag(ChannelFlag.ALIPAY);
		} else if (payType == QrcodePayType.WEIXIN) {
			data.setChannelFlag(ChannelFlag.WEIXIN);
		} else if (payType == QrcodePayType.BAIDU) {
			data.setChannelFlag(ChannelFlag.BAIDU);
		} else if (payType == QrcodePayType.JD) {
			data.setChannelFlag(ChannelFlag.JD);
		} else if (payType == QrcodePayType.QQ) {
			data.setChannelFlag(ChannelFlag.QQ);
		} else {
			throw new ChannelApiException("不支持支付方式：" + payType);
		}
		//data.setCurrency(currency);// 币种,默认CNY
		data.setExtraDesc(null == order.getPayerRemark() ? order.getMerchno(): order.getPayerRemark());// 附加信息
		//data.setGoodsName(goodsName);// 商品名称
		data.setMerchNo(order.getChannelMerchno());// 商户号
		data.setNotifyUrl(channel.getPayNotifyUrl());// 异步通知url，需要向申请白名单
		//data.setOperatorId(operatorId);// 商户操作员编号
		data.setOrderNo(order.getOrderno());// 商户订单号,同一商户号和终端号下唯一
		data.setReqId(order.getOrderno() + ("" + new Date().getTime()).substring(5));// 请求交易的流水号，同一商户号和终端号下唯一
		data.setReqTime(new Date());// 请求时间
		data.setTermNo(order.getMerchno());// 终端编码
		
		UnifiedOrderResult apiResult = client.execute(data);
		if (apiResult.getRespCode() != RespCode.SUCCESS) {
			throw new ChannelApiExecuteFailException(apiResult.getRespDesc());
		}
		
		String qrcodeUrl = apiResult.getCodeUrl();
		try {
			qrcodeUrl = URLDecoder.decode(qrcodeUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("URLDecode失败", e);
		}
		
		ChannelQrcodeOrderResult res = new ChannelQrcodeOrderResult();
		res.setChannelOrderNo(apiResult.getTransId());
		res.setQrcode(qrcodeUrl);
		//res.setTradeOrderNo(apiResult.getTradeNo());
		return res;
		
	}

	@Override
	public ChannelQrcodeOrderResult officalPay() throws ChannelApiException {
		if (order.getPayType() != QrcodePayType.WEIXIN.ordinal()) {
			return qrcodePay(order);
		}
		Channel channel = getChannel();
		QrcodeMerch qrcodeMerch = getQrcodeMerch();
		
		OfficalOrder client = (OfficalOrder) YufuQrcodeApiFactory.getClient(YufuQrcodeApiType.OFFICAL_ORDER);
		
		OfficalOrderData data = new OfficalOrderData();
		data.setAmount(order.getAmount().multiply(new BigDecimal("100")).setScale(0).toPlainString());// 订单金额，单位为分
		//data.setCurrency(currency);// 币种,默认CNY
		data.setExtraDesc(null == order.getPayerRemark() ? order.getMerchno(): order.getPayerRemark());// 附加信息
		//data.setGoodsName(goodsName);// 商品名称
		data.setMerchNo(order.getChannelMerchno());// 商户号
		data.setNotifyUrl(channel.getPayNotifyUrl());// 异步通知url，不能带参数，需要向申请白名单
		//data.setOperatorId(operatorId);// 商户操作员编号
		data.setOrderNo(order.getOrderno());// 商户订单号,同一商户号和终端号下唯一
		data.setReqId(order.getOrderno() + ("" + new Date().getTime()).substring(5));// 请求交易的流水号，同一商户号和终端号下唯一
		data.setReqTime(new Date());// 请求时间
		data.setSubAppId(qrcodeMerch.getAppId());// 公众号APPID，必填
		data.setSubOpenId(order.getOpenId());// 公众号APPID的用户OPENID，必填
		OfficalOrderResult apiResult = client.execute(data);
		
		if (apiResult.getRespCode() != RespCode.SUCCESS) {
			throw new ChannelApiExecuteFailException(apiResult.getRespDesc());
		}
		String pack = apiResult.getPack();
		try {
			pack = URLEncoder.encode(apiResult.getPack(), "GBK");
		} catch (UnsupportedEncodingException e) {
			log.error("URLEncoder失败", e);
		}
		
		String qrcodeUrl = Config.getString("offical-pay-barcode");
		qrcodeUrl = String.format(qrcodeUrl, apiResult.getAppId(), apiResult.getNonceStr(), pack, apiResult.getPaySign(),
				apiResult.getTimeStamp(), apiResult.getSignType(), order.getMerchno());
		
		ChannelQrcodeOrderResult res = new ChannelQrcodeOrderResult();
		res.setChannelOrderNo(apiResult.getTransId());
		res.setQrcode(qrcodeUrl);
		//res.setTradeOrderNo(null);
		return res;
	}
	
	@Override
	public boolean isNeedOpenId() {
		return true;
	}
	
	private Channel getChannel() throws ChannelApiException {
		Channel4Search channel4s = new Channel4Search();
		channel4s.setChannelCode(order.getChannelCode());
		List<Channel> channelList;
		try {
			channelList = channelMapper.listByPage(channel4s);
		} catch (Exception e) {
			throw new ChannelApiException(e);
		}
		return channelList.get(0);
	}
	
	private QrcodeMerch getQrcodeMerch() throws ChannelApiException {
		QrcodeMerch4Search merch4s = new QrcodeMerch4Search();
		merch4s.setPartnerId(order.getChannelMerchno());
		List<QrcodeMerch> merchList;
		try {
			merchList = qrcodeMerchMapper.listByPage(merch4s);
		} catch (Exception e) {
			throw new ChannelApiException(e);
		}
		return merchList.get(0);
	}
}
