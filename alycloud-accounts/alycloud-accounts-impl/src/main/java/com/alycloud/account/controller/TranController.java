package com.alycloud.account.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IMerchService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.PageRequestBean;
import com.alycloud.core.modules.PageResultBean;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.ReceiveDetailInfo;
import com.alycloud.modules.entity.TotalAmountInfo;
import com.alycloud.modules.entity.WithdrawalDetailInfo;
import com.alycloud.modules.vo.QueryTranDetailVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 银行卡管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value="tran")
@Slf4j
public class TranController {

	@Value("${platformKey}")
    private String key;
	@Value("${page.flag}")
    private boolean pageFlag;
	@Autowired
	private IMerchService merchService;
	
	@ApiOperation(notes = "调用 /tranTotal方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询累计收款，可提现金额")
	@PostMapping(value="/total")
	@SystemControllerLog(description = "App项目查询累计收款，可提现金额")
	public ResultBean<TotalAmountInfo> tranTotal(@Validated @RequestBody RequestBean<String> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		BigDecimal totalAmount = merchService.getSuccTranAmt(reqData.getMerchno());
		BigDecimal withdrawalAmount = merchService.getAvailAmt(reqData.getMerchno());
		TotalAmountInfo tai = new TotalAmountInfo();
		tai.setReceiveAmount((null==totalAmount)?new BigDecimal("0.00"):totalAmount);
		tai.setWithdrawalAmount((null==withdrawalAmount)?new BigDecimal("0.00"):withdrawalAmount);
		log.warn("商户收款累计金额,可提现累计金额{},{}", tai.getReceiveAmount(),tai.getWithdrawalAmount());
		return RestBeanGenerator.genSuccessResult(tai);
	}
	
	@ApiOperation(notes = "调用 /receiveDetail方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "收款明细")
	@PostMapping(value="/receiveDetail")
	@SystemControllerLog(description = "App项目收款明细")
	public PageResultBean<List<ReceiveDetailInfo>> receiveDetail(@Validated @RequestBody PageRequestBean<QueryTranDetailVO> reqData) throws Exception{
		Page<ReceiveDetailInfo> page=PageHelper.startPage(reqData.getPageNum(), reqData.getPageSize());
		log.info("请求参数:"+reqData.getData().toString());
		List<ReceiveDetailInfo> tranDetailList = merchService.getTranDetail(reqData.getMerchno(),
				reqData.getData().getStartDate(),reqData.getData().getEndDate());
		if(StrUtil.isEmpty(tranDetailList) || tranDetailList.size()<1){
			return RestBeanGenerator.genSuccessPageResult("99", tranDetailList, null);
		}
		log.warn("商户收款明细{}", tranDetailList);
		return RestBeanGenerator.genSuccessPageResult(page);
	}
	
	@ApiOperation(notes = "调用 /withdrawalDetail方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "提现明细")
	@PostMapping(value="/withdrawalDetail")
	@SystemControllerLog(description = "App项目提现明细")
	public PageResultBean<List<WithdrawalDetailInfo>> withdrawalDetail(@Validated @RequestBody PageRequestBean<QueryTranDetailVO> reqData) throws Exception{
		Page<WithdrawalDetailInfo> page=PageHelper.startPage(reqData.getPageNum(), reqData.getPageSize());
		log.info("请求参数:"+reqData.getData().toString());
		List<WithdrawalDetailInfo> withdrawalDetailList = merchService.getWithdrawalDetail(reqData.getMerchno(),
				reqData.getData().getStartDate(),reqData.getData().getEndDate());
		if(StrUtil.isEmpty(withdrawalDetailList) || withdrawalDetailList.size()<1){
			return RestBeanGenerator.genSuccessPageResult("99", withdrawalDetailList, null);
		}
		log.warn("商户提现明细{}", withdrawalDetailList);
		return RestBeanGenerator.genSuccessPageResult(page);
	}
	
	@ApiOperation(notes = "调用 /withdrawalOrderDetail方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "提现订单详情")
	@PostMapping(value="/withdrawalOrderDetail")
	@SystemControllerLog(description = "App项目提现订单详情")
	public ResultBean<WithdrawalDetailInfo> withdrawalOrderDetail(@Validated @RequestBody RequestBean<QueryTranDetailVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getData().getOrderNo())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		log.info("请求参数:"+reqData.getData().toString());
		WithdrawalDetailInfo wdi = merchService.queryWithdrawalOrderDetail(reqData.getData().getOrderNo());
		MerchInfo merchInfo = merchService.getByMerchno(reqData.getMerchno());
		if(StrUtil.isEmpty(wdi)){
			return RestBeanGenerator.genResult("99", wdi, null);
		}
		wdi.setSettleCard(merchInfo.getAccountno());
		log.warn("商户提现订单详情{}", wdi);
		return RestBeanGenerator.genSuccessResult(wdi);
	}
	
	@ApiOperation(notes = "调用 /receiveOrderDetail方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "收款订单详情")
	@PostMapping(value="/receiveOrderDetail")
	@SystemControllerLog(description = "App项目收款订单详情")
	public ResultBean<ReceiveDetailInfo> receiveOrderDetail(@Validated @RequestBody RequestBean<QueryTranDetailVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getData().getOrderNo())||StrUtil.isEmpty(reqData.getData().getPayType())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		log.info("请求参数:"+reqData.getData().toString());
		ReceiveDetailInfo rdi = merchService.queryReceiveOrderDetail(reqData.getData().getOrderNo(),reqData.getData().getPayType());
		MerchInfo merchInfo = merchService.getByMerchno(reqData.getMerchno());
		if(StrUtil.isEmpty(rdi)){
			return RestBeanGenerator.genResult("99", rdi, null);
		}
		rdi.setSettleCard(merchInfo.getAccountno());
		log.warn("商户收款订单详情{}", rdi);
		return RestBeanGenerator.genSuccessPageResult(rdi);
	}
	
	@ApiOperation(notes = "调用 /queryReceiveDetailByStatus方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询各状态下收款明细")
	@PostMapping(value="/queryReceiveDetailByStatus")
	@SystemControllerLog(description = "App项目查询各状态下收款明细")
	public ResultBean<List<ReceiveDetailInfo>> queryReceiveDetailByStatus(@Validated @RequestBody PageRequestBean<QueryTranDetailVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getPageNum())
				||StrUtil.isEmpty(reqData.getPageSize())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		Page<ReceiveDetailInfo> page=PageHelper.startPage(reqData.getPageNum(), reqData.getPageSize());
		log.info("请求参数:"+reqData.getData().toString());
		List<ReceiveDetailInfo> tranDetailList = merchService.queryReceiveDetailByStatus(reqData.getMerchno(),
				reqData.getData().getStartDate(),reqData.getData().getEndDate(),reqData.getData().getTranStatus());
		MerchInfo merchInfo = merchService.getByMerchno(reqData.getMerchno());
		if(StrUtil.isEmpty(tranDetailList) || tranDetailList.size()<1||(null==tranDetailList.get(0))){
			return RestBeanGenerator.genResult("99", tranDetailList, null);
		}
		for(ReceiveDetailInfo rdi:tranDetailList){
			rdi.setSettleCard(merchInfo.getAccountno());
			rdi.setBankName(merchInfo.getBankName());
			log.info("订单号:"+rdi.getOrderno()+"订单金额:"+rdi.getAmount()+"商户总手续费:"+rdi.getMerchFee());
			rdi.setSettleAmount(rdi.getAmount().subtract(null==rdi.getMerchFee()?new BigDecimal(0.00):rdi.getMerchFee()).setScale(2));
			switch (rdi.getStatus()) {
			case 0:
				rdi.setDescr("交易中");
				break;
			case 1:
				rdi.setDescr("交易成功");
				break;
			case 2:
				rdi.setDescr("交易失败");
				break;
			default:
				break;
			}
			
			switch (Integer.parseInt(rdi.getPayType())) {
			case 1:
				rdi.setPayType("支付宝扫码");
				break;
			case 2:
				rdi.setPayType("微信扫码");
				break;
			case 6:
				rdi.setPayType("银联快捷");
				break;
			default:
				break;
			}
		}
		log.warn("商户收款明细{}", tranDetailList);
		return RestBeanGenerator.genSuccessPageResult(page);
	}
	
	@ApiOperation(notes = "调用 /queryWithdrawalDetailByStatus方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询各状态下提现明细")
	@PostMapping(value="/queryWithdrawalDetailByStatus")
	@SystemControllerLog(description = "App项目查询各状态下提现明细")
	public ResultBean<List<WithdrawalDetailInfo>> queryWithdrawalDetailByStatus(@Validated @RequestBody PageRequestBean<QueryTranDetailVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getPageNum())
				||StrUtil.isEmpty(reqData.getPageSize())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		Page<WithdrawalDetailInfo> page=PageHelper.startPage(reqData.getPageNum(), reqData.getPageSize());
		log.info("请求参数:"+reqData.getData().toString());
		List<WithdrawalDetailInfo> withdrawalDetailList = merchService.queryWithdrawalDetailByStatus(reqData.getMerchno(),
				reqData.getData().getStartDate(),reqData.getData().getEndDate(),reqData.getData().getTranStatus());
		MerchInfo merchInfo = merchService.getByMerchno(reqData.getMerchno());
		if(StrUtil.isEmpty(withdrawalDetailList) || withdrawalDetailList.size()<1||(null==withdrawalDetailList.get(0))){
			return RestBeanGenerator.genResult("99", withdrawalDetailList, null);
		}
		for(WithdrawalDetailInfo wdi:withdrawalDetailList){
			wdi.setSettleCard(merchInfo.getAccountno());
			wdi.setSettleAmount(wdi.getTransAmount().subtract(null==wdi.getTransFee()?new BigDecimal(0.00):wdi.getTransFee()).setScale(2));
			switch (wdi.getPayStatus()) {
			case 1:
				wdi.setRemark("交易中");
				break;
			case 2:
				wdi.setRemark("交易成功");
				break;
			case 3:
				wdi.setRemark("交易失败");
				break;
			default:
				break;
			}
			switch (Integer.parseInt(wdi.getPayType())) {
			case 1:
				wdi.setPayType("支付宝提现");
				break;
			case 2:
				wdi.setPayType("微信提现");
				break;
			case 6:
				wdi.setPayType("银联快捷提现");
				break;
			default:
				break;
			}
		}
		log.warn("商户提现明细{}", withdrawalDetailList);
		return RestBeanGenerator.genSuccessPageResult(page);
	}
}
