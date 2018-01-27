package com.alycloud.pay.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.channel.yufu.merch.YufuMerchApiFactory;
import com.alycloud.channel.yufu.merch.YufuMerchApiType;
import com.alycloud.channel.yufu.merch.bean.MerchApplyData;
import com.alycloud.channel.yufu.merch.bean.MerchApplyPayService;
import com.alycloud.channel.yufu.merch.bean.MerchApplyResult;
import com.alycloud.channel.yufu.merch.bean.MerchQueryData;
import com.alycloud.channel.yufu.merch.bean.MerchQueryResult;
import com.alycloud.channel.yufu.merch.enums.PayService;
import com.alycloud.channel.yufu.merch.support.client.MerchApply;
import com.alycloud.channel.yufu.merch.support.client.MerchQuery;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.vo.ReportMerchFeeVO;
import com.alycloud.pay.feign.MerchInfoFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 御付渠道
 * @author Horanluo
 * @date 2017年11月13日
 */
@RestController
@RequestMapping("/yufuChannel")
@Api(value = "御付渠道接口")
@Slf4j
public class YufuChannelController {
	@Autowired
	private MerchInfoFeign merchInfoFeign ;
	/**
	 * 上报御付通道商户费率
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年11月13日
	 */
	@ApiOperation(notes = "调用 /recordMerchFee方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "上报御付通道商户费率")
	@PostMapping(value = "/recordYUFUFee")
	@SystemControllerLog(description = "上报御付通道商户费率")
	public MerchApplyResult recordMerchFee(@RequestBody RequestBean<ReportMerchFeeVO> feignData) throws Exception {
		MerchApplyData data = buildMerchApplyData(feignData);
		log.warn("请求参数：", data.toString());
		MerchApply client = (MerchApply) YufuMerchApiFactory.getClient(YufuMerchApiType.MERCH_APPLY);
		MerchApplyResult result = client.post(data);
		return result;
	}
	
	/**
	 * 查询御付通道审核结果
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年11月13日
	 */
	@ApiOperation(notes = "调用 /queryAuditResult方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询御付通道审核结果")
	@PostMapping(value = "/queryAuditResult")
	@SystemControllerLog(description = "查询御付通道审核结果")
	public MerchQueryResult queryAuditResult(@RequestBody MerchQueryData data) throws Exception {
		MerchQuery client = (MerchQuery)YufuMerchApiFactory.getClient(YufuMerchApiType.MERCH_QUERY);
		MerchQueryResult result = client.post(data);
		return result;
	}
	
	/**
	 * 创建御付商户入驻申请接口参数对象
	 * @author 罗根恒
	 * @date 2017年11月2日
	 */
	private MerchApplyData buildMerchApplyData(RequestBean<ReportMerchFeeVO> feignData) throws Exception {
		ChannelSubmerchInfo newRecord = feignData.getData().getCsmi();
		MerchInfo merch4Mod = feignData.getData().getMerchInfo();
		
		BigDecimal t0Rate=newRecord.getD0payRate().multiply(new BigDecimal("1000"));
		BigDecimal t1Rate=newRecord.getT1payRate().multiply(new BigDecimal("1000"));
		BigDecimal t0Fee=newRecord.getPayFeeD0();
		log.info("t0Rate:"+t0Rate.setScale(2).toPlainString()+"t1Rate:"+t1Rate.setScale(2).toPlainString()+
				"toFee:"+t0Fee.setScale(2).toPlainString());
		List<MerchApplyPayService> payServices = new ArrayList<MerchApplyPayService>();
		MerchApplyPayService payService = new MerchApplyPayService();
		payService.setBottomFee("0");
		payService.setCreditCardRate(t0Rate.setScale(2).toPlainString());
		payService.setD0FeeBi(t0Fee.setScale(0).toPlainString());
		payService.setD0MinAmount(newRecord.getAmountMin().multiply(new BigDecimal(100)).setScale(0).toPlainString());
		payService.setD0Rate(t0Rate.subtract(t1Rate).setScale(2).toPlainString());
		payService.setDebitCardRate(t0Rate.setScale(2).toPlainString());
		payService.setLimitAmount("0");
		payService.setScale(t1Rate.setScale(2).toPlainString());
		payService.setPayService(PayService.KJ);
		payService.setTradeType("5210");// 快捷可默认填写5210
		payServices.add(payService);
		
		RequestBean<String> reqData = new RequestBean<String>();
		reqData.setMerchno(newRecord.getMerchno());
		MerchInfo merch = merchInfoFeign.getByMerchno(reqData).getData();
		
		MerchApplyData data = new MerchApplyData();
		if(!(null==merch4Mod)){
			data.setAccountName(merch4Mod.getAccountName());
			data.setAccountNo(merch4Mod.getAccountno());
			data.setAccountType(1);// 0-民生对公、1-民生对私、2-非民生对公、3-非民生对私(快捷可默认填写：1)
			data.setBankBranchName(merch4Mod.getBankno());// 联行号，精确到支行网点非民生对公必填
		}else{
			data.setAccountName(merch.getAccountName());
			data.setAccountNo(merch.getAccountno());
			data.setAccountType(1);// 0-民生对公、1-民生对私、2-非民生对公、3-非民生对私(快捷可默认填写：1)
			data.setBankBranchName(merch.getBankno());// 联行号，精确到支行网点非民生对公必填
		}
		data.setBankCode("310290000013");// 开户行号，快捷可默认填写：310290000013
		data.setBankName("310290000013");// 开户行名	结算户名联行号，精确到总行和bankCode传递一样的值（快捷可默认填写：310290000013）
		data.setCity(2904);// 快捷可默认填写：2904
		data.setIdCard(merch.getIdentityCard());
		data.setMerchAddress(merch.getAddress());
		data.setMerchContacts(merch.getLinkMan());
		data.setMerchName(merch.getFullName());// 商户名称 全局唯一性判重
		data.setMerchShortName(merch.getMerchName());// 商户简称
		//data.setNotifyUrl("http://api.esyto.com/channel/notify.do?m=yufu4FastApply");// 商户开通结果通知的url
		//注意：这个参数无效，并且地址不能带参数，否则接口调用会失败
		data.setNotifyUrl("http://api.esyto.com/channel/notify.do");// 商户开通结果通知的url
		data.setOpenType(4);// 快捷可默认选择：4
		data.setPayServices(payServices);
		data.setPhone(merch.getMobile());// 联系人电话 全局唯一性判重
		data.setProvince(29);// 快捷可默认填写29
		String timestamp = "" + new Date().getTime();
		String merchno = merch.getMerchno();
		data.setSerialNo(timestamp.substring(timestamp.length()-8, timestamp.length()-1) + merchno.substring(merchno.length()-10, merchno.length()-1));// 流水号
		log.info("Yufu入驻渠道参数:"+data);
		return data;
	}
}
