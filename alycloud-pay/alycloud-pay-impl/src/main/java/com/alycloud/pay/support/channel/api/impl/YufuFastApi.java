package com.alycloud.pay.support.channel.api.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alycloud.channel.yufu.YufuApiFactory;
import com.alycloud.channel.yufu.YufuApiType;
import com.alycloud.channel.yufu.bean.MerchOrderQueryData;
import com.alycloud.channel.yufu.bean.MerchOrderQueryResult;
import com.alycloud.channel.yufu.bean.PayData;
import com.alycloud.channel.yufu.bean.PayResult;
import com.alycloud.channel.yufu.enums.PayType;
import com.alycloud.channel.yufu.merch.enums.MerchState;
import com.alycloud.channel.yufu.support.client.MerchOrderQuery;
import com.alycloud.channel.yufu.support.client.Pay;
import com.alycloud.core.enums.SysChannelType;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.search.ChannelSubmerchInfo4Search;
import com.alycloud.pay.feign.ChannelSubmerchInfoFeign;
import com.alycloud.pay.support.channel.api.ChannelApiAdapter;
import com.alycloud.pay.support.channel.api.ChannelApiException;
import com.alycloud.pay.support.channel.api.ChannelApiExecuteFailException;
import com.alycloud.pay.support.channel.api.ChannelFastOrderResult;
import com.alycloud.pay.support.channel.api.ChannelOrderStatus;
import com.alycloud.pay.support.channel.api.ChannelOrderStatusResult;

/**
 * 御付渠道（快捷）接口
 * @author Moyq5
 * @date 2017年8月2日
 */
public class YufuFastApi extends ChannelApiAdapter {

	private static final Logger log = LoggerFactory.getLogger(YufuFastApi.class);
	private FastOrder order;
	public YufuFastApi(FastOrder order) {
		this.order = order;
	}
	@Override
	public ChannelOrderStatusResult query() {
		ChannelOrderStatusResult result = new ChannelOrderStatusResult();
		
		ChannelSubmerchInfo yufu = null;
		try {
			yufu = getChannelMerchYufu();
		} catch (Exception e) {
			result.setStatus(ChannelOrderStatus.ERROR);
			result.setMsg(e.getMessage());
			return result;
		}
		
		MerchOrderQuery client = (MerchOrderQuery) YufuApiFactory.getClient(YufuApiType.MERCH_ORDER_QUERY);
		MerchOrderQueryData data = new MerchOrderQueryData();
		data.setMerchNo(yufu.getChannelSubmerchno());
		data.setMerchOrderNo(order.getOrderno());
		data.setSerialNo(order.getOrderno() + new Date().getTime());
		data.setPageNum("1");
		data.setIsOrderList(false);
		MerchOrderQueryResult apiResult = client.execute(data);
		
		if (apiResult.getRespCode().equals("0000")) {
			log.info("御付订单[{}]付款状态：{}/{}", 
					new Object[]{order.getOrderno(), apiResult.getFinishAmount(), apiResult.getAmount()});
			if (new BigDecimal(apiResult.getFinishAmount()).compareTo(new BigDecimal(apiResult.getAmount())) == 0) {
				result.setStatus(ChannelOrderStatus.SUCCESS);
				result.setMsg("支付成功");
			} else {
				result.setStatus(ChannelOrderStatus.FAIL);
				result.setMsg(apiResult.getRespMsg());
			}
		} else {
			result.setStatus(ChannelOrderStatus.ERROR);
			result.setMsg(apiResult.getRespMsg());
		}
		return result;
	}
	
	@Override
	public ChannelFastOrderResult fastPay() throws ChannelApiException {
		ChannelSubmerchInfo yufu = getChannelMerchYufu();
		String token = yufu.getYufuOneCodeUrl().split("token\\=")[1];// 一码付地址中的token信息
		
		Pay pay = (Pay) YufuApiFactory.getClient(YufuApiType.PAY);
		PayData payData = new PayData();
		payData.setAmount(order.getAmount().multiply(new BigDecimal("100")).setScale(0).toPlainString());// 元转分
		payData.setMerchOrderNo(order.getOrderno());
		payData.setPayType(PayType.FAST);
		payData.setRemark(order.getPayerRemark());
		payData.setToken(token);
		
		PayResult payResult = pay.execute(payData);
		ChannelFastOrderResult result = new ChannelFastOrderResult();
		if (!payResult.getRespCode().equals("0000")) {
			result.setErrorCode(payResult.getRespCode());
			result.setErrorMes(payResult.getRespMsg());
			result.setSuccess(false);
			//throw new ChannelApiExecuteFailException(payResult.getRespMsg());
			return result;
		} 
		
		String payHtml = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>"
                + "<meta http-equiv='refresh' content='0; URL="+payResult.getPayUrl()+"'>"
                + "</head>"
                + "</html>";
		log.info("御付支付信息:"+payHtml);
		//result.setChannelOrderNo(channelOrderNo);
		result.setErrorCode(payResult.getRespCode());
		result.setErrorMes(payResult.getRespMsg());
		result.setSuccess(true);
		result.setPayHtml(payHtml);
		//result.setTradeOrderNo(tradeOrderNo);
		return result;
	}
	
	private ChannelSubmerchInfo getChannelMerchYufu() throws ChannelApiException {
		String merchno = order.getMerchno();
		ChannelSubmerchInfoFeign submerchFeign = SpringContextUtils.getBean(ChannelSubmerchInfoFeign.class);
		
		ChannelSubmerchInfo4Search submerch4s = new ChannelSubmerchInfo4Search();
		submerch4s.setMerchno(merchno);
		submerch4s.setChannelCode(SysChannelType.values()[order.getChannelType()].getCode());
		submerch4s.setPayMethod("QUICKPAY");
		submerch4s.setPayType("QUICKPAY");
		submerch4s.setAduitStatus(MerchState.PASS.ordinal()+"");
		
		RequestBean<ChannelSubmerchInfo4Search> submerchFeignData = new RequestBean<ChannelSubmerchInfo4Search>();
		submerchFeignData.setData(submerch4s);
		List<ChannelSubmerchInfo> submerchList = submerchFeign.listByPage(submerchFeignData).getData();
		
		if (submerchList.size() == 0) {
			log.error("商户[{}]找不到对应的御付渠道商户信息", merchno);
			throw new ChannelApiException("找不到对应的御付渠道商户信息:" + merchno);
		}
		if (submerchList.size() > 1) {
			log.error("商户[{}]找不止一条对应的御付渠道商户信息", merchno);
			throw new ChannelApiException("渠道商户信息有误：size " + submerchList.size());
		}
		return submerchList.get(0);
	}

}
