package com.alycloud.pay.gateway.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.utils.HttpsClientUtil;
import com.alycloud.core.utils.JSONUtils;
import com.alycloud.core.utils.ReflectUtils;
import com.alycloud.core.utils.SignUtil;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.pay.gateway.api.IProxyService;
import com.alycloud.pay.gateway.dto.BatchProxyDTO;
import com.alycloud.pay.gateway.dto.ProxyDTO;
import com.alycloud.pay.gateway.dto.ProxyQueryDTO;
import com.alycloud.pay.gateway.mapper.MerchMapper;
import com.alycloud.pay.gateway.models.proxy.ProxyResult;

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
public class ConsumeTransController {
	@Autowired
    private MerchMapper merchMapper;
	@Value("${proxy_minAmount}")
    private String proxyMinAmount;
	@Value("${proxy_maxAmount}")
    private String proxyMaxAmount;
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
	@Value("${withdrawFee}")
    private String withdrawFee;
    @Autowired
    private IProxyService proxyService;
    @Value("${batch_query_service_id}")
    private String batchQueryServiceId;
    
	@RequestMapping(value = "/singleProxyReq")
	public ProxyResult backTransRequest(@RequestBody ProxyDTO proxyDto){
		ProxyResult result = new ProxyResult();
		MerchVirtualCard mvcard = proxyService.getMerchVirtualCard(proxyDto.getMerchno());
		MerchInfo merch = merchMapper.getByMerchno(proxyDto.getMerchno());
		
		Map<String, String> param = ReflectUtils.convertToMaps(proxyDto);
        if(!SignUtil.validSign(param, merch.getMerchKey()))
        {
        	result.setRespCode("0");
        	result.setMessage("签名不正确");
        	return result;
        }
        
		BigDecimal availAmount = mvcard.getAvailAmount();
		log.info("可提现金额(元):{}", availAmount);
		
		BigDecimal transAmount = new BigDecimal(proxyDto.getTransactionamount());
		log.info("目标提现金额(元):{}", transAmount);
		
		if (availAmount.compareTo(transAmount) == -1) {
			result.setRespCode("0");
			result.setMessage("可提现金额不够");
			return result;
		}
		
		if (transAmount.compareTo(new BigDecimal(proxyMinAmount)) == -1) {
			result.setRespCode("0");
			result.setMessage("代付金额不能小于: " + proxyMinAmount + "元");
			return result;
		}
		
		if (transAmount.compareTo(new BigDecimal(proxyMaxAmount)) == 1) {
			result.setRespCode("0");
			result.setMessage("代付金额不能大于: " + proxyMaxAmount + "元");
			return result;
		}
		
		MerchVirtualTrans mvtrans = proxyService.createMerchVirtualTrans(proxyDto,merch,mvcard);
		proxyService.createPaymentTrans(merch, mvtrans);
		proxyService.modifyMerchVirtualCard(mvtrans);
		
		Map<String, String> respResult = ReflectUtils.convertToMaps(result);
		result.setRespCode("1");
		result.setMessage("代付["+proxyDto.getBusinessnumber()+"]提交成功-->状态:[待处理]");
		result.setSign(SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(respResult))));
		return result;
	}
	
	@RequestMapping(value = "/singleProxyQueryReq")
	public ProxyResult proxyQueryRequest(@RequestBody ProxyQueryDTO proxyQueryDto){
		ProxyResult result = new ProxyResult();
		MerchInfo merch = merchMapper.getByMerchno(proxyQueryDto.getMerchno());
		
		Map<String, String> param = ReflectUtils.convertToMaps(proxyQueryDto);
        if(!SignUtil.validSign(param, merch.getMerchKey()))
        {
        	result.setRespCode("0");
        	result.setMessage("签名不正确");
        	return result;
        }
        
        MerchVirtualTrans merchVirtualTrans = proxyService.getMerchVirtualTrans(proxyQueryDto.getBusinessnumber());
        if(null==merchVirtualTrans){
        	result.setRespCode("0");
        	result.setMessage("订单不存在");
        	return result;
        }
        
        Map<String, String> params = new HashMap<String, String>();
		params.put("service_id",queryServiceId);//tf56enterprise.enterprise.payForCustomer
		params.put("appid",appid);//1362001
		params.put("tf_timestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		params.put("businessnumber",proxyQueryDto.getBusinessnumber());//业务流水号
		
		String sign = SignUtil.proxySign(params,sign_sk,"utf-8");
		params.put("tf_sign", sign);
		
		log.info("代付查询加签值:{}",sign);
		log.info("代付查询请求参数:{}",params);
		
		String proxyResult = HttpsClientUtil.sendHttpPost(singleProxyUrl,params);
		
		JSONObject proxyJsonResult = JSONObject.parseObject(proxyResult);
		log.info("单笔代付响应结果:{}",proxyJsonResult);
		
		if("error".equals(proxyJsonResult.getString("result"))){
			result.setRespCode("0");
			result.setMessage(proxyJsonResult.getString("msg"));
			return result;
		}
		
		log.info(proxyJsonResult.getString("data"));
		JSONObject proxyDataResult = JSONObject.parseObject(proxyJsonResult.getString("data"));
		log.info("代付data结果:{}",proxyDataResult);
		log.info("代付响应结果:{},状态:{}",proxyJsonResult.getString("result"),proxyDataResult.getString("status"));
		
		if("success".equals(proxyJsonResult.getString("result"))&&"成功".equals(proxyDataResult.getString("status"))){
			merchVirtualTrans.setPayMsg("出款成功");
			merchVirtualTrans.setPayStatus(2);
			merchVirtualTrans.setTransRefno(proxyDataResult.getString("businessrecordnumber"));
			proxyService.modifyMerchVirtualTrans(merchVirtualTrans);
			
			result.setRespCode("1");
        	result.setMessage("代付成功");
        	
			Map<String, String> respResult = ReflectUtils.convertToMaps(result);
			result.setSign(SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(respResult))));
        	return result;
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
//	@PostMapping(value = "/proxy/notify")
//	public void notify(@RequestBody String respMes) throws Exception{
//		log.info("对外接口--代付回调响应结果:"+respMes);
//		
//		Map<String, String> postMap = new HashMap<String, String>();
//		JSONObject jsonResp = JSONObject.parseObject(respMes);
//		
//		String tf_sign = jsonResp.getString("tf_sign");
//		Map<String, String> params = ReflectUtils.convertToMaps(jsonResp);
//		log.info("代付map响应结果:{}",params);
//		
//		boolean verifyFlag = SignUtil.proxyVerify(params,tf_sign,sign_sk,"utf-8");
//		log.info("代付回调验签结果:{}",verifyFlag);
//		
//		if(!verifyFlag){
//			postMap.put("respCode", "0");
//			postMap.put("message", "回调失败");
//		}else{
//			String subject = jsonResp.getString("subject");
//			String description = jsonResp.getString("description");
//			String businessnumber = jsonResp.getString("businessnumber");
//			String businessrecordnumber = jsonResp.getString("businessrecordnumber");
//			String status = jsonResp.getString("status");
//			String transactiondate = jsonResp.getString("transactiondate");
//			String billamount = jsonResp.getString("billamount");
//			String transactionamount = jsonResp.getString("transactionamount");
//			String transactiontype = jsonResp.getString("transactiontype");
//			String inputdate = jsonResp.getString("inputdate");
//			
//			log.info("对外接口--代付商品名称:{},商品描述:{},业务流水号:{},支付订单号:{},交易状态:{},交易时间:{},订单金额:{},交易金额:{},"
//					+ "交易类型:{},交易创建时间:{},签名:{}",
//					subject,description,businessnumber,businessrecordnumber,status,transactiondate,billamount,transactionamount,
//					transactiontype,inputdate,tf_sign);
//			
//			MerchVirtualTrans merchVirtualTrans = proxyService.getMerchVirtualTrans(businessnumber);
//			MerchInfo merch = merchMapper.getByMerchno(merchVirtualTrans.getMerchno());
//			
//			postMap.put("respCode", "1");
//			postMap.put("message", "回调成功");
//			postMap.put("subject", subject);
//			postMap.put("merchno", merchVirtualTrans.getMerchno());
//			postMap.put("businessnumber", businessnumber);
//			postMap.put("transactionamount", transactionamount);
//			postMap.put("transactiondate", transactiondate);
//			postMap.put("status", status);
//			
//			String sign = SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(postMap)));
//			postMap.put("sign", sign);
//			HttpsClientUtil.sendRequest(merchVirtualTrans.getNotifyUrl(), JSONUtils.obj2json(postMap), "application/json");
//		}
//	}
	
	public static void main(String[] args) {
//		ProxyDTO proxyDto = new ProxyDTO();
//		proxyDto.setMerchno("200541100000004");
//		proxyDto.setBusinessnumber("BNwnf-trans-953882466286829501");
//		proxyDto.setSubject("单笔代付");
//		proxyDto.setTransactionamount("4");
//		proxyDto.setBankcardnumber("6214856555342173");
//		proxyDto.setBankcardname("曾云龙");
//		proxyDto.setBankname("招商银行");
//		proxyDto.setBankcardtype("个人");
//		proxyDto.setBankaccounttype("储蓄卡");
//
//		Map<String, String> param = ReflectUtils.convertToMaps(proxyDto);
//		String sign = SignUtil.genSign("918A9EDBECCF2B74AAD63A29928E86A2", SignUtil.createLinkString(SignUtil.paraFilter(param)));
//		System.out.println(sign);
		String paydetail_1="wnf-trans-953882466286829524^批量代付20^6214856555342173^曾云龙^2.50^个人^储蓄卡^招商银行^^^^^测试代付^ ";
		String paydetail_2="wnf-trans-953882466286829525^批量代付21^6214856555342173^曾云龙^2.50^个人^储蓄卡^招商银行^^^^^测试代付^ ";
		//String paydetail_3="wnf-trans-953882466286829513^批量代付9^6214837559410067^罗根红^2^个人^储蓄卡^招商银行^^^^^测试代付^ ";
		String paydetail=paydetail_1+"|"+paydetail_2;//+"|"+paydetail_3;
		
		BatchProxyDTO batchProxyDto = new BatchProxyDTO();
		batchProxyDto.setMerchno("200541100000004");
		batchProxyDto.setBatchno("bat20180124174550");
		batchProxyDto.setBatchamount("5");
		batchProxyDto.setBatchnum("2");
		batchProxyDto.setPaydate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		batchProxyDto.setPaydetail(paydetail);
		//batchProxyDto.setBackurl("");
		//batchProxyDto.setExtendparam("");
		System.out.println(batchProxyDto.toString());
		
		Map<String, String> param = ReflectUtils.convertToMaps(batchProxyDto);
		System.out.println(param);
		String sign = SignUtil.genSign("918A9EDBECCF2B74AAD63A29928E86A2", SignUtil.createLinkString(SignUtil.paraFilter(param)));
		System.out.println(sign);
	}
	
	@RequestMapping(value = "/batchProxyReq")
	public ProxyResult batchProxyReq(@RequestBody BatchProxyDTO batProxyDto){
		ProxyResult result = new ProxyResult();
		MerchVirtualCard mvcard = proxyService.getMerchVirtualCard(batProxyDto.getMerchno());
		MerchInfo merch = merchMapper.getByMerchno(batProxyDto.getMerchno());
		
		Map<String, String> param = ReflectUtils.convertToMaps(batProxyDto);
		log.info(param.toString());
        if(!SignUtil.validSign(param, merch.getMerchKey()))
        {
        	result.setRespCode("0");
        	result.setMessage("签名不正确");
        	return result;
        }
        
		BigDecimal availAmount = mvcard.getAvailAmount();
		log.info("可提现金额(元):{}", availAmount);
		
		BigDecimal transAmount = new BigDecimal(batProxyDto.getBatchamount());
		log.info("目标提现金额(元):{}", transAmount);
		
		if (availAmount.compareTo(transAmount) == -1) {
			result.setRespCode("0");
			result.setMessage("可提现金额不够");
			return result;
		}
		
		if (transAmount.compareTo(new BigDecimal(proxyMinAmount)) == -1) {
			result.setRespCode("0");
			result.setMessage("代付金额不能小于: " + proxyMinAmount + "元");
			return result;
		}
		
		if (transAmount.compareTo(new BigDecimal(proxyMaxAmount)) == 1) {
			result.setRespCode("0");
			result.setMessage("代付金额不能大于: " + proxyMaxAmount + "元");
			return result;
		}
		
		List<ProxyDTO> proxylist = new ArrayList<ProxyDTO>();
		String payDetail = batProxyDto.getPaydetail();
		String[] payDetails = payDetail.split("\\|");
		
		String businessnumber;
		String subject;
		String bankcardnumber;
		String bankcardname;
		String transactionamount;
		String bankcardtype;
		String bankaccounttype;
		String bankname;
		String description;
		String[] details;
		
		for(String detail:payDetails){
			ProxyDTO proxyDto = new ProxyDTO();
			details=detail.split("\\^");
			System.out.println(Arrays.toString(details));
			businessnumber=details[0];
			subject=details[1];
			bankcardnumber=details[2];
			bankcardname=details[3];
			transactionamount=details[4];
			bankcardtype=details[5];
			bankaccounttype=details[6];
			bankname=details[7];
			description=details[12];
			
			log.info("商户流水号:{},商品名称:{},收款方银行卡号:{},收款账号姓名:{},付款金额:{},银行卡类型:{},银行卡借贷类型:{},银行名称:{},订单描述:{}",
					businessnumber,subject,bankcardnumber,bankcardname,transactionamount,bankcardtype,bankaccounttype,bankname,description);
			
			proxyDto.setBusinessnumber(businessnumber);
			proxyDto.setSubject(subject);
			proxyDto.setBankcardnumber(bankcardnumber);
			proxyDto.setBankcardname(bankcardname);
			proxyDto.setTransactionamount(transactionamount);
			proxyDto.setBankcardtype(bankcardtype);
			proxyDto.setBankaccounttype(bankaccounttype);
			proxyDto.setBankname(bankname);
			proxyDto.setDescription(description);
			
			proxylist.add(proxyDto);
		}
		
		List<MerchVirtualTrans> resultList = proxyService.createBatchMerchVirtualTrans(batProxyDto, proxylist, merch, mvcard);
		proxyService.createBatchPaymentTrans(merch, resultList);
		proxyService.modifyMerchVirtualCard(resultList.get(resultList.size()-1));
		
		Map<String, String> respResult = ReflectUtils.convertToMaps(result);
		result.setRespCode("1");
		result.setMessage("代付批次["+batProxyDto.getBatchno()+"]提交成功-->状态:[待处理]");
		result.setSign(SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(respResult))));
		return result;
	}
	
	@RequestMapping(value = "/batchProxyQueryReq")
	public ProxyResult batchProxyQueryRequest(@RequestBody ProxyQueryDTO proxyQueryDto){
		ProxyResult result = new ProxyResult();
		MerchInfo merch = merchMapper.getByMerchno(proxyQueryDto.getMerchno());
		
		Map<String, String> param = ReflectUtils.convertToMaps(proxyQueryDto);
        if(!SignUtil.validSign(param, merch.getMerchKey()))
        {
        	result.setRespCode("0");
        	result.setMessage("签名不正确");
        	return result;
        }
        
        MerchVirtualTrans merchVirtualTrans = proxyService.getMerchVirtualTrans(proxyQueryDto.getBusinessnumber());
        if(null==merchVirtualTrans){
        	result.setRespCode("0");
        	result.setMessage("订单不存在");
        	return result;
        }
        
        Map<String, String> params = new HashMap<String, String>();
		params.put("service_id",batchQueryServiceId);//tf56enterprise.enterprise.payForCustomer
		params.put("appid",appid);//1362001
		params.put("tf_timestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		params.put("batchno",proxyQueryDto.getBatchno());//代付批次号
		
		String sign = SignUtil.proxySign(params,sign_sk,"utf-8");
		params.put("tf_sign", sign);
		
		log.info("批量代付查询加签值:{}",sign);
		log.info("批量代付查询请求参数:{}",params);
		
		String proxyResult = HttpsClientUtil.sendHttpPost(singleProxyUrl,params);
		
		JSONObject proxyJsonResult = JSONObject.parseObject(proxyResult);
		log.info("批量代付响应结果:{}",proxyJsonResult);
		
		if("error".equals(proxyJsonResult.getString("result"))){
			result.setRespCode("0");
			result.setMessage(proxyJsonResult.getString("msg"));
			return result;
		}
		
		log.info(proxyJsonResult.getString("data"));
		JSONObject proxyDataResult = JSONObject.parseObject(proxyJsonResult.getString("data"));
		log.info("代付data结果:{}",proxyDataResult);
		log.info("代付响应结果:{},状态:{}",proxyJsonResult.getString("result"),proxyDataResult.getString("status"));
		
		if("success".equals(proxyJsonResult.getString("result"))&&"成功".equals(proxyDataResult.getString("status"))){
			merchVirtualTrans.setPayMsg("出款成功");
			merchVirtualTrans.setPayStatus(2);
			merchVirtualTrans.setTransRefno(proxyDataResult.getString("businessrecordnumber"));
			proxyService.modifyMerchVirtualTrans(merchVirtualTrans);
			
			result.setRespCode("1");
        	result.setMessage("代付成功");
        	
			Map<String, String> respResult = ReflectUtils.convertToMaps(result);
			result.setSign(SignUtil.genSign(merch.getMerchKey(), SignUtil.createLinkString(SignUtil.paraFilter(respResult))));
        	return result;
		}
		return result;
	}
}
