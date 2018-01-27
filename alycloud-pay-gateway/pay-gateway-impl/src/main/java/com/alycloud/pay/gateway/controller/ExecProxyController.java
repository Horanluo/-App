package com.alycloud.pay.gateway.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.utils.HttpsClientUtil;
import com.alycloud.core.utils.SignUtil;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.pay.gateway.mapper.MerchVirtualTransMapper;
import com.alycloud.pay.gateway.models.proxy.ProxyResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * restful接口
 * 
 * @author Horanluo
 * @version V001Z0001
 * @date 2018年01月23日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/gateway")
@Api(value = "商户对外接口")
@Slf4j
public class ExecProxyController {

	@Autowired
    private MerchVirtualTransMapper merchVirtualTransMapper;
//	@Autowired
//	private MerchVirtualCardMapper merchVirtualCardMapper;
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
	@Value("${batch_tran_service_id}")
    private String batchTranServiceId;
	@Value("${enterpriseCode}")
    private String enterpriseCode;
	
	/**
	 * 执行单笔代付
	 * @param merchno
	 * @return
	 */
	@RequestMapping(value = "/execSingleProxy")
	public ProxyResult execSingleProxy(){
		MerchVirtualTrans merchVirtualTrans = new MerchVirtualTrans();
		merchVirtualTrans.setPayStatus(1);
		List<MerchVirtualTrans> resultlist = merchVirtualTransMapper.getMerchVirtualTransList(merchVirtualTrans);
		
		ProxyResult respResult = new ProxyResult();
		if(null==resultlist||resultlist.size()<1){
			respResult.setRespCode("0");
			respResult.setMessage("该商户无待处理的代付订单");
			return respResult;
		}
		String message="";
		for(MerchVirtualTrans mvt:resultlist){
//			MerchVirtualCard mvcard = merchVirtualCardMapper.getMerchVirtualCard(mvt.getMerchno());
//			
//			BigDecimal availAmount = mvcard.getAvailAmount();
//			log.info("可提现金额(元):{}", availAmount);
//			
//			BigDecimal transAmount = mvt.getTransAmount();
//			log.info("目标提现金额(元):{}", transAmount);
//			
//			if (availAmount.compareTo(transAmount) == -1) {
//				respResult.setRespCode("0");
//				respResult.setMessage("可提现金额不够");
//				return respResult;
//			}
//			
//			if (transAmount.compareTo(new BigDecimal(proxyMinAmount)) == -1) {
//				respResult.setRespCode("0");
//				respResult.setMessage("代付金额不能小于: " + proxyMinAmount + "元");
//				return respResult;
//			}
//			
//			if (transAmount.compareTo(new BigDecimal(proxyMaxAmount)) == 1) {
//				respResult.setRespCode("0");
//				respResult.setMessage("代付金额不能大于: " + proxyMaxAmount + "元");
//				return respResult;
//			}
//			
			Map<String, String> params = new HashMap<String, String>();
			params.put("service_id",tranServiceId);//tf56enterprise.enterprise.payForCustomer
			params.put("appid",appid);//1362001
			params.put("tf_timestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			params.put("businessnumber",mvt.getTraceno());//业务流水号
			params.put("subject",mvt.getRemark());
			params.put("transactionamount",mvt.getAmount().setScale(2).toPlainString());//校验金额
			params.put("bankcardnumber",mvt.getAccountno());//银行卡号
			params.put("bankcardname",mvt.getAccountName());//银行卡姓名
			params.put("bankname",mvt.getBankName());//银行名称
			params.put("bankcardtype","个人");//银行卡类型
			params.put("bankaccounttype","储蓄卡");//银行卡借贷类型
			params.put("fromaccountnumber",fromAccountNumber);//会员账号名 
			params.put("backurl", "http://120.24.13.203:9001/services/payGateway/gateway/api/proxy/notify");
			
			String sign = SignUtil.proxySign(params,sign_sk,"utf-8");
			params.put("tf_sign", sign);
			
			log.info("代付加签值:{}",sign);
			log.info("代付请求参数:{}",params);
			
			String proxyResult = HttpsClientUtil.sendHttpPost(singleProxyUrl,params);
			log.info("代付响应结果:{}",proxyResult);
			
			JSONObject proxyJsonResult = JSONObject.parseObject(proxyResult);
			if("success".equals(proxyJsonResult.getString("result"))){
				respResult.setRespCode("1");
				message=message+"订单:"+mvt.getTraceno()+"代付成功,";
				respResult.setMessage(message);
			}else{
				respResult.setRespCode("0");
				respResult.setMessage("订单:"+mvt.getTraceno()+"代付失败,失败原因:"+proxyJsonResult.getString("msg"));
				return respResult;
			}
		}
		return respResult;
	}
	
	/**
	 * 执行批量代付
	 * @param merchno
	 * @return
	 */
	@RequestMapping(value = "/execBatchProxy")
	public ProxyResult execBatchProxy(){
		MerchVirtualTrans merchVirtualTrans = new MerchVirtualTrans();
		merchVirtualTrans.setPayStatus(1);
		List<MerchVirtualTrans> resultlist = merchVirtualTransMapper.getBatchList(merchVirtualTrans);
		
		ProxyResult respResult = new ProxyResult();
		if(null==resultlist||resultlist.size()<1){
			respResult.setRespCode("0");
			respResult.setMessage("该商户无待处理的代付订单");
			return respResult;
		}
		String respMessage="";
		for(MerchVirtualTrans mvt:resultlist){
			
			List<MerchVirtualTrans> batchlist = merchVirtualTransMapper.getInfoByBatchno(mvt.getBatchno());
			String payDetails="";
			for(int i=0;i<batchlist.size();i++){
				MerchVirtualTrans mvts = batchlist.get(i);
				if(i==batchlist.size()-1){
					payDetails=payDetails+mvts.getTraceno()+"^"+mvts.getRemark()+"^"+mvts.getAccountno()+"^"+mvts.getAccountName()+"^"+
							mvts.getAmount()+"^个人^储蓄卡^"+mvts.getBankName()+"^^^^^^ ";
				}else{
					payDetails=payDetails+mvts.getTraceno()+"^"+mvts.getRemark()+"^"+mvts.getAccountno()+"^"+mvts.getAccountName()+"^"+
							mvts.getAmount()+"^个人^储蓄卡^"+mvts.getBankName()+"^^^^^^ |";
				}
			}
			log.info("代付批次:{},代付明细:{}",mvt.getBatchno(),payDetails);
			Map<String, String> params = new HashMap<String, String>();
			params.put("service_id",batchTranServiceId);//tf56enterprise.enterprise.payForCustomer
			params.put("appid",appid);
			params.put("tf_timestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			params.put("enterprisecode",enterpriseCode);//商户唯一编号
			params.put("accountnumber",fromAccountNumber);//支付账户
			params.put("batchno",mvt.getBatchno());//代付批次号
			params.put("batchnum",mvt.getTotalRecord());//代付总笔数
			params.put("batchamount",mvt.getTotalProxyAmount().toPlainString());//代付总金额
			params.put("paydate",new SimpleDateFormat("yyyyMMdd").format(new Date()));//代付日期
			params.put("paydetail",payDetails);//代付明细
			params.put("backurl","");//
			
			String sign = SignUtil.proxySign(params,sign_sk,"utf-8");
			params.put("tf_sign", sign);
			
			log.info("代付加签值:{}",sign);
			log.info("代付请求参数:{}",params);
			
			String batchProxyResult = HttpsClientUtil.sendHttpPost(singleProxyUrl,params);
			log.info("批量代付响应结果:{}",batchProxyResult);
			
			JSONObject batchProxyJsonResult = JSONObject.parseObject(batchProxyResult);
			if("success".equals(batchProxyJsonResult.getString("result"))){
				respResult.setRespCode("1");
				respMessage=respMessage+"代付批次:"+mvt.getBatchno()+"代付成功,";
				respResult.setMessage(respMessage);
			}else{
				respResult.setRespCode("0");
				respResult.setMessage("代付批次:"+mvt.getBatchno()+"代付失败,失败原因:"+batchProxyJsonResult.getString("msg"));
				return respResult;
			}
		}
		return respResult;
//		String message="";
//		for(MerchVirtualTrans mvt:resultlist){
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("service_id",tranServiceId);//tf56enterprise.enterprise.payForCustomer
//			params.put("appid",appid);//1362001
//			params.put("tf_timestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//			params.put("businessnumber",mvt.getTraceno());//业务流水号
//			params.put("subject",mvt.getRemark());
//			params.put("transactionamount",mvt.getAmount().setScale(2).toPlainString());//校验金额
//			params.put("bankcardnumber",mvt.getAccountno());//银行卡号
//			params.put("bankcardname",mvt.getAccountName());//银行卡姓名
//			params.put("bankname",mvt.getBankName());//银行名称
//			params.put("bankcardtype","个人");//银行卡类型
//			params.put("bankaccounttype","储蓄卡");//银行卡借贷类型
//			params.put("fromaccountnumber",fromAccountNumber);//会员账号名 
//			params.put("backurl", "http://120.24.13.203:9001/services/payGateway/gateway/api/proxy/notify");
//			
//			String sign = SignUtil.proxySign(params,sign_sk,"utf-8");
//			params.put("tf_sign", sign);
//			
//			log.info("代付加签值:{}",sign);
//			log.info("代付请求参数:{}",params);
//			
//			String proxyResult = HttpsClientUtil.sendHttpPost(singleProxyUrl,params);
//			log.info("代付响应结果:{}",proxyResult);
//			
//			JSONObject proxyJsonResult = JSONObject.parseObject(proxyResult);
//			if("success".equals(proxyJsonResult.getString("result"))){
//				respResult.setRespCode("1");
//				message=message+"订单:"+mvt.getTraceno()+"代付成功,";
//				respResult.setMessage(message);
//			}else{
//				respResult.setRespCode("0");
//				respResult.setMessage("订单:"+mvt.getTraceno()+"代付失败,失败原因:"+proxyJsonResult.getString("msg"));
//				return respResult;
//			}
//		}
	}
}
