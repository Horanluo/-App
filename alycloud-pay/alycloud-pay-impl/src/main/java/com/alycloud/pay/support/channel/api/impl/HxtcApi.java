package com.alycloud.pay.support.channel.api.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiajinsuo.epay.sdk.HttpUtils;
import org.xiajinsuo.epay.sdk.RRParams;
import org.xiajinsuo.epay.sdk.ResponseDataWrapper;
import com.alycloud.channel.utils.hxtc.HxtcConfig;
import com.alycloud.channel.utils.hxtc.HxtcUtils;
import com.alycloud.channel.utils.hxtc.MypaysRsaDataEncryptUtil;
import com.alycloud.channel.utils.hxtc.MypaysRsaDataEncryptUtil_JF;
import com.alycloud.core.enums.SysChannelType;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.entity.SystemCardBin;
import com.alycloud.modules.search.ChannelSubmerchInfo4Search;
import com.alycloud.pay.feign.ChannelSubmerchInfoFeign;
import com.alycloud.pay.feign.SystemCardBinFeign;
import com.alycloud.pay.support.channel.api.ChannelApiAdapter;
import com.alycloud.pay.support.channel.api.ChannelApiException;
import com.alycloud.pay.support.channel.api.ChannelApiExecuteFailException;
import com.alycloud.pay.support.channel.api.ChannelFastOrderResult;
import com.alycloud.pay.support.channel.api.ChannelOrderStatus;
import com.alycloud.pay.support.channel.api.ChannelOrderStatusResult;

/**
 * 汇享天成渠道（快捷）接口
 * @author Moyq5
 * @date 2017年10月8日
 */
public class HxtcApi extends ChannelApiAdapter {

	private static final Logger log = LoggerFactory.getLogger(HxtcApi.class);
	/**
	 * 商户单号
	 */
	private FastOrder order;
	public HxtcApi(FastOrder order) {
		this.order = order;
	}
	@Override
	public ChannelOrderStatusResult query() {
		ChannelOrderStatusResult statusResult = new ChannelOrderStatusResult();
		Map<String, String> businessReq = new HashMap<String, String>();
        businessReq.put("method", "pay_qry");
        businessReq.put("orig_tran_id", order.getOrderno());
        String channelType = SysChannelType.values()[order.getChannelType()].getCode();
        RRParams requestData = null;
        ResponseDataWrapper rdw = null;
        if("HXTC".equals(channelType)){
             requestData = RRParams.newBuilder().setAppId(HxtcConfig.appId).setClientTransId(generateTransId())
                .setTransTimestamp(System.currentTimeMillis()).setTransType(HxtcConfig.transType).build();
             
             rdw = HttpUtils.post(HxtcConfig.REQUEST_URL, requestData, businessReq,
                 MypaysRsaDataEncryptUtil.rsaDataEncryptPri, MypaysRsaDataEncryptUtil.rsaDataEncryptPub);
        }
        
        if("HXTC_JF".equals(channelType)){
            requestData =
                RRParams.newBuilder()
                    .setAppId(HxtcConfig.jf_appId)
                    .setClientTransId(generateTransId())
                    .setTransTimestamp(System.currentTimeMillis())
                    .setTransType(HxtcConfig.transType)
                    .build();
            rdw =
                HttpUtils.post(HxtcConfig.REQUEST_URL,
                    requestData,
                    businessReq,
                    MypaysRsaDataEncryptUtil_JF.jf_rsaDataEncryptPri,
                    MypaysRsaDataEncryptUtil_JF.jf_rsaDataEncryptPub);
        }
        
        if (rdw.getRespCode().equals("000000")) {
            @SuppressWarnings("unchecked")
			Map<String, String> responseData = rdw.getResponseData();
            log.debug("汇享天成订单[{}]付款状态：{}", order.getOrderno(), responseData);
            String respCode = responseData.get("resp_code");
            String respMsg = responseData.get("resp_msg");
			if (respCode.equals("PAY_SUCCESS")) {
				statusResult.setStatus(ChannelOrderStatus.SUCCESS);
				statusResult.setMsg("支付成功");
			} else if (respCode.equals("PAY_SUBMIT")) {
				statusResult.setStatus(ChannelOrderStatus.NEW);
				statusResult.setMsg("尚未支付");
			} else if (respCode.equals("PAY_FAILURE")) {
				statusResult.setStatus(ChannelOrderStatus.FAIL);
				statusResult.setMsg(respMsg);
			} else {
				statusResult.setStatus(ChannelOrderStatus.ERROR);
				statusResult.setMsg(respMsg);
			}
        } else {
        	log.debug("汇享天成订单[{}]查询结果：{}，{}", 
        			new Object[]{ order.getOrderno(),rdw.getRespCode(), rdw.getRespMsg() });
        	statusResult.setStatus(ChannelOrderStatus.ERROR);
			statusResult.setMsg("[" + rdw.getRespCode() + "]" + rdw.getRespMsg());
        } 
		
		return statusResult;
	}

	@Override
	public ChannelFastOrderResult fastPay() throws ChannelApiException {
		
		SystemCardBinFeign binFeign = SpringContextUtils.getBean(SystemCardBinFeign.class);
		RequestBean<String> feignData = new RequestBean<String>();
		feignData.setData(order.getBankCard());
		SystemCardBin bin = binFeign.getByCardno(feignData).getData();
		ChannelSubmerchInfoFeign submerchFeign = SpringContextUtils.getBean(ChannelSubmerchInfoFeign.class);
		
		ChannelSubmerchInfo4Search submerch4s = new ChannelSubmerchInfo4Search();
		submerch4s.setMerchno(order.getMerchno());
		submerch4s.setChannelCode(SysChannelType.values()[order.getChannelType()].getCode());
		submerch4s.setPayMethod("QUICKPAY");
		submerch4s.setPayType("QUICKPAY");
		RequestBean<ChannelSubmerchInfo4Search> submerchFeignData = new RequestBean<ChannelSubmerchInfo4Search>();
		submerchFeignData.setData(submerch4s);
		List<ChannelSubmerchInfo> submerchList = submerchFeign.listByPage(submerchFeignData).getData();
		ChannelSubmerchInfo submerch = submerchList.get(0);
		
		Map<String, String> businessReq;
		RRParams requestData = new RRParams();
		ResponseDataWrapper rdw = new ResponseDataWrapper();
		
		try {
			businessReq = HxtcUtils.responsePayBuilder(order, submerch, bin, order.getCallbackUrl(), order.getNotifyUrl());
		} catch (Exception e) {
			throw new ChannelApiException(e);
		}
		if("HXTC".equals(submerch4s.getChannelCode())){
	        requestData =
	            RRParams.newBuilder()
	                .setAppId(HxtcConfig.appId)
	                .setClientTransId(order.getOrderno())
	                .setTransTimestamp(System.currentTimeMillis())
	                .setTransType(HxtcConfig.transType)
	                .build();
	        rdw =
	            HttpUtils.post(HxtcConfig.REQUEST_URL,
	                requestData,
	                businessReq,
	                MypaysRsaDataEncryptUtil.rsaDataEncryptPri,
	                MypaysRsaDataEncryptUtil.rsaDataEncryptPub);
		}
		
		if("HXTC_JF".equals(submerch4s.getChannelCode())){
	        requestData =
	            RRParams.newBuilder()
	                .setAppId(HxtcConfig.jf_appId)
	                .setClientTransId(order.getOrderno())
	                .setTransTimestamp(System.currentTimeMillis())
	                .setTransType(HxtcConfig.transType)
	                .build();
	        rdw =
	            HttpUtils.post(HxtcConfig.REQUEST_URL,
	                requestData,
	                businessReq,
	                MypaysRsaDataEncryptUtil_JF.jf_rsaDataEncryptPri,
	                MypaysRsaDataEncryptUtil_JF.jf_rsaDataEncryptPub);
		}
		@SuppressWarnings("unchecked")
		Map<String, String> apiResult = rdw.getResponseData();
		ChannelFastOrderResult result = new ChannelFastOrderResult();
		
		log.info("汇享天成响应码:{},响应详情:{}",rdw.getRespCode(),rdw.getRespMsg());
		if (!rdw.getRespCode().equals("000000")) {
			result.setErrorCode(rdw.getRespCode());
			result.setErrorMes(rdw.getRespMsg());
			result.setSuccess(false);
			return result;
			//throw new ChannelApiExecuteFailException(rdw.getRespMsg());
		}
        
		result.setErrorCode(rdw.getRespCode());
		result.setErrorMes(rdw.getRespMsg());
		result.setSuccess(true);
		result.setChannelOrderNo(rdw.getServerTransId());
		result.setPayHtml(apiResult.get("page_content"));
		//result.setTradeOrderNo(tradeOrderNo);
		return result;
	}
    

	static String generateTransTime() {
		return DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
	}

	static String generateTransId() {
		String time = DateUtils.formatDate(new Date(), "yyyyMMdd");
		String nanoTime = System.nanoTime() + "";
		return String.format("%s%s", time, nanoTime.substring(nanoTime.length() - 6));
	}
	
}
