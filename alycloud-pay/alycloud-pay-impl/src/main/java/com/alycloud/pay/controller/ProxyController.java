package com.alycloud.pay.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.utils.HttpsClientUtil;
import com.alycloud.core.utils.SignUtil;
import com.alycloud.modules.entity.AgentVirtualCard;
import com.alycloud.modules.entity.AgentVirtualTrans;
import com.alycloud.modules.entity.AgentWithdraw;
import com.alycloud.modules.entity.MerchVirtualTrans;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 工具类
 * @author Horanluo
 * @date 2018年1月19日
 */
@RestController
@RequestMapping("/proxy")
@Api(value = "代付类")
@Slf4j
public class ProxyController {

	@Value("${tran_service_id}")
    private String tranServiceId;
	@Value("${appid}")
    private String appid;
	@Value("${fromaccountnumber}")
    private String fromAccountNumber;
	@Value("${sign_sk}")
    private String sign_sk;
	@Value("${single_proxy_url}")
    private String singleProxyUrl;
	@Value("${query_service_id}")
    private String queryServiceId;
	@Value("${batch_query_service_id}")
    private String batchQueryServiceId;
	
	/**
	 * 单笔代付
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2018年01月19日
	 */
	@ApiOperation(notes = "调用 /singleProxy方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "单笔代付服务接口")
	@PostMapping(value = "/singleProxy")
	@SystemControllerLog(description = "单笔代付服务接口")
	public String singleProxy(@RequestBody AgentWithdraw agentWithdraw) throws Exception {
		AgentVirtualCard card = agentWithdraw.getAgentVirtualCard();
		BigDecimal transAmount = agentWithdraw.getWithdrawAmt();
		String withdrawTraceNo = agentWithdraw.getTraceno();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("service_id",tranServiceId);//tf56enterprise.enterprise.payForCustomer
		params.put("appid",appid);//1362001
		params.put("tf_timestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		params.put("businessnumber",withdrawTraceNo);//业务流水号
		params.put("subject","商户分润提现");
		params.put("transactionamount",transAmount.setScale(2).toPlainString());//校验金额
		params.put("bankcardnumber",card.getAccountno());//银行卡号
		params.put("bankcardname",card.getAccountName());//银行卡姓名
		params.put("bankname",card.getBankName());//银行名称
		params.put("bankcardtype","个人");//银行卡类型
		params.put("bankaccounttype","储蓄卡");//银行卡借贷类型
		params.put("fromaccountnumber",fromAccountNumber);//会员账号名 
		
		String sign = SignUtil.proxySign(params,sign_sk,"utf-8");
		params.put("tf_sign", sign);
		
		log.info("代付加签值:{}",sign);
		log.info("代付请求参数:{}",params);
		
		String result = HttpsClientUtil.sendHttpPost(singleProxyUrl,params);
		return result;
	}
	
	/**
	 * 代理商分润单笔代付查询服务接口
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2018年01月19日
	 */
	@ApiOperation(notes = "调用 /agentSingleProxyQuery方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "代理商分润单笔代付查询服务接口")
	@PostMapping(value = "/agentSingleProxyQuery")
	@SystemControllerLog(description = "代理商分润单笔代付查询服务接口")
	public JSONObject agentSingleProxyQuery(@RequestBody AgentVirtualTrans agentVirtualTrans) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("service_id",queryServiceId);//tf56enterprise.enterprise.payForCustomer
		params.put("appid",appid);//1362001
		params.put("tf_timestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		params.put("businessnumber",agentVirtualTrans.getTraceno());//分润提现业务流水号
		
		String sign = SignUtil.proxySign(params,sign_sk,"utf-8");
		params.put("tf_sign", sign);
		log.info("查询代理商代付订单加签值:{}",sign);
		log.info("查询代理商代付订单请求参数:{},代付订单号:{}",params,params.get("businessnumber"));
		
		String result = HttpsClientUtil.sendHttpPost(singleProxyUrl,params);
		JSONObject proxyJsonResult = JSONObject.parseObject(result);
		return proxyJsonResult;
	}
	
	/**
	 * 商户单笔代付查询服务接口
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2018年01月19日
	 */
	@ApiOperation(notes = "调用 /merchSingleProxyQuery方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "商户单笔代付查询服务接口")
	@PostMapping(value = "/merchSingleProxyQuery")
	@SystemControllerLog(description = "商户单笔代付查询服务接口")
	public JSONObject merchSingleProxyQuery(@RequestBody MerchVirtualTrans merchVirtualTrans) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("service_id",queryServiceId);//tf56enterprise.enterprise.payForCustomer
		params.put("appid",appid);//1362001
		params.put("tf_timestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		params.put("businessnumber",merchVirtualTrans.getTraceno());//分润提现业务流水号
		
		String sign = SignUtil.proxySign(params,sign_sk,"utf-8");
		params.put("tf_sign", sign);
		log.info("查询商户单笔代付订单加签值:{}",sign);
		log.info("查询商户单笔代付订单请求参数:{},商户单笔代付订单号:{}",params,params.get("businessnumber"));
		
		String result = HttpsClientUtil.sendHttpPost(singleProxyUrl,params);
		JSONObject proxyJsonResult = JSONObject.parseObject(result);
		return proxyJsonResult;
	}
	
	/**
	 * 商户批量代付查询服务接口
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2018年01月19日
	 */
	@ApiOperation(notes = "调用 /merchBatchProxyQuery方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "商户批量代付查询服务接口")
	@PostMapping(value = "/merchBatchProxyQuery")
	@SystemControllerLog(description = "商户批量代付查询服务接口")
	public JSONObject merchBatchProxyQuery(@RequestBody MerchVirtualTrans merchVirtualTrans) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("service_id",batchQueryServiceId);//tf56enterprise.enterprise.payForCustomer
		params.put("appid",appid);//1362001
		params.put("tf_timestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		params.put("batchno",merchVirtualTrans.getBatchno());//批量提现业务流水号
		
		String sign = SignUtil.proxySign(params,sign_sk,"utf-8");
		params.put("tf_sign", sign);
		log.info("查询商户批量代付订单加签值:{}",sign);
		log.info("查询商户批量代付订单请求参数:{},商户批量代付批次号:{}",params,params.get("batchno"));
		
		String result = HttpsClientUtil.sendHttpPost(singleProxyUrl,params);
		JSONObject proxyJsonResult = JSONObject.parseObject(result);
		return proxyJsonResult;
	}
}
