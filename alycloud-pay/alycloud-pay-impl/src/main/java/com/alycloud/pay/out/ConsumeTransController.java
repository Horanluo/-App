package com.alycloud.pay.out;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.HttpsClientUtil;
import com.alycloud.core.utils.JSONUtils;
import com.alycloud.core.utils.ReflectUtils;
import com.alycloud.core.utils.SignUtil;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.vo.ExternalQrcodeVO;
import com.alycloud.pay.feign.MerchInfoFeign;
import com.alycloud.pay.out.channel.SaoBeiService;
import com.alycloud.pay.out.model.OutQueryResult;
import com.alycloud.pay.out.model.OutTranResult;
import com.alycloud.pay.out.service.IQrcodeService;
import com.alycloud.pay.support.channel.api.ChannelApi;
import com.alycloud.pay.support.channel.api.ChannelApiException;
import com.alycloud.pay.support.channel.api.ChannelQrcodeOrderResult;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * restful接口
 * 
 * @author Horanluo
 * @version V001Z0001
 * @date 2018年01月12日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/gateway/api")
@Api(value = "商户对外接口")
@Slf4j
public class ConsumeTransController extends BaseController{

	@Autowired
	public MerchInfoFeign merchInfoFeign;
	@Autowired
	private IQrcodeService qrcodeService;
	@Autowired
	private SaoBeiService saoBeiService;
	
	/**
	 * 后台交易
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/backTransReq")
	public OutTranResult backTransRequest(@RequestBody ExternalQrcodeVO qrcodeVO){
		OutTranResult result = new OutTranResult();
		
        switch (qrcodeVO.getPayType()) {
		case 1: //获取支付宝二维码URL
			result = getPayQRCode(qrcodeVO);
			break;
		case 2: //获取微信二维码URL
			result = getPayQRCode(qrcodeVO);
			break;
		case 3: //百度扫码
	
			break;
		case 4: //QQ扫码
			
			break;
		case 5: //京东扫码
			
			break;
		default:
			break;
		}
        return result;
	}

	private OutTranResult getPayQRCode(ExternalQrcodeVO qrcodeVO) {
		OutTranResult tranResult = new OutTranResult();
		
		RequestBean<String> reqData = new RequestBean<String>();
		reqData.setData(qrcodeVO.getMerchno());
		MerchInfo merch = merchInfoFeign.getByMerchno(reqData).getData();
		
		Map<String, String> param = ReflectUtils.convertToMaps(qrcodeVO);
        if(!SignUtil.validSign(param, merch.getMerchKey()))
        {
        	tranResult.setRespCode("0");
        	tranResult.setMessage("签名不正确");
        	return tranResult;
        }
        log.info(merch.toString());
        
        ChannelApi api = saoBeiService;
        QrcodeOrder order;
        try {
			order = qrcodeService.buildOrder(qrcodeVO);
			ChannelQrcodeOrderResult apiResult = api.outQrcodePay(order,qrcodeVO);
			
			tranResult.setQrcodeUrl(apiResult.getQrcode());
			tranResult.setRespCode("1");
			
			Map<String, String> respResult = ReflectUtils.convertToMaps(tranResult);
			tranResult.setSign(SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(respResult))));
			return tranResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
        tranResult.setRespCode("0");
    	tranResult.setMessage("下单失败");
        return tranResult;
	}
	
	public static void main(String[] args) {
		ExternalQrcodeVO qrcodeVO = new ExternalQrcodeVO();
		qrcodeVO.setMerchno("200541100000461");
		qrcodeVO.setGoodsName("安致兰德");
		qrcodeVO.setTraceno("20180112155853");
		qrcodeVO.setAmount(new BigDecimal(1).setScale(2).doubleValue());
		qrcodeVO.setCallbackUrl("http://120.24.13.203:9001/services/pay/front/saobeiNotify");
		qrcodeVO.setNotifyUrl("http://120.24.13.203:9001/services/pay/gateway/api/notify");
		qrcodeVO.setPayType(2);
		qrcodeVO.setAttach("");
		
		Map<String, String> param = ReflectUtils.convertToMaps(qrcodeVO);
		System.out.println(SignUtil.genSign("B1E2D8C8E0A8901065B6AD1B7D2C7329", SignUtil.createLinkString(SignUtil.paraFilter(param))));
	}
	
	/**
	 * 订单查询
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryTran")
	public OutQueryResult queryTranRequest(@RequestBody ExternalQrcodeVO qrcodeVO){
		OutQueryResult result = new OutQueryResult();
		
		RequestBean<String> reqData = new RequestBean<String>();
		reqData.setData(qrcodeVO.getMerchno());
		MerchInfo merch = merchInfoFeign.getByMerchno(reqData).getData();
		
		Map<String, String> param = ReflectUtils.convertToMaps(qrcodeVO);
        if(!SignUtil.validSign(param, merch.getMerchKey()))
        {
        	result.setRespCode("0");
        	result.setMessage("签名不正确");
        	return result;
        }
        log.info(merch.toString());
        
		ChannelApi api = saoBeiService;
		try {
			result = api.queryOutQrcodePay(qrcodeVO);
			return result;
		} catch (ChannelApiException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 回调
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/notify")
	public void notify(@RequestBody String respMes){
		log.info("对外接口--扫呗支付响应结果:"+respMes);
		
		String postStr = "{}";
		Map<String, String> postMap = new HashMap<String, String>();
		JSONObject jsonResp = JSONObject.parseObject(respMes);
		
		String returnCode = jsonResp.getString("return_code");
		String returnMsg = jsonResp.getString("return_msg");
		String resultCode = jsonResp.getString("result_code");
		String merchantNo = jsonResp.getString("merchant_no");
		String terminalTrace = jsonResp.getString("terminal_trace");
		String payTime = jsonResp.getString("pay_time");
		String totalFee = jsonResp.getString("total_fee");
		String endTime = jsonResp.getString("end_time");
		String outTradeNo = jsonResp.getString("out_trade_no");
		String channelTradeNo = jsonResp.getString("channel_trade_no");
		log.info("对外接口--响应码:{},响应信息:{},业务返回结果:{},商户号:{},交易流水号:{},交易时间:{},交易金额:{},交易完成时间:{},利楚唯一订单号:{},通道订单号:{}",
				returnCode,returnMsg,resultCode,merchantNo,terminalTrace,payTime,totalFee,endTime,outTradeNo,channelTradeNo);
		
		QrcodeOrder order = new QrcodeOrder();
		order.setOrderno(terminalTrace);
		order = qrcodeService.getOrderInfo(order);
		log.info("对外接口--扫呗支付订单信息");
		
		RequestBean<String> reqData = new RequestBean<String>();
		reqData.setData(order.getMerchno());
		MerchInfo merch = merchInfoFeign.getByMerchno(reqData).getData();
		
		if("01".equals(returnCode)&&"01".equals(resultCode)){
			postMap.put("respCode", "1");
			postMap.put("message", "支付成功");
			postMap.put("merchno", merchantNo);
			postMap.put("traceno", terminalTrace);
			postMap.put("outChannelNo", outTradeNo);
			postMap.put("amount", totalFee);
			postMap.put("transTime", endTime);
		}else{
			postMap.put("respCode", "0");
			postMap.put("message", returnMsg);
		}
		
		String sign = SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(postMap)));
		postMap.put("sign", sign);
		try {
			postStr = JSONUtils.obj2json(postMap);
			HttpsClientUtil.sendRequest(order.getNotifyUrl(), postStr, "application/json");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("对外接口--扫呗交易通知异常", e);
		}
	}
}
