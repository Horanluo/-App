package com.alycloud.pay.gateway.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.modules.entity.PaymentTrans;
import com.alycloud.modules.enums.PaymentTransBizType;
import com.alycloud.modules.enums.PaymentTransPayStatus;
import com.alycloud.pay.gateway.api.IProxyService;
import com.alycloud.pay.gateway.dto.BatchProxyDTO;
import com.alycloud.pay.gateway.dto.ProxyDTO;
import com.alycloud.pay.gateway.mapper.AgentMapper;
import com.alycloud.pay.gateway.mapper.BranchMapper;
import com.alycloud.pay.gateway.mapper.MerchVirtualCardMapper;
import com.alycloud.pay.gateway.mapper.MerchVirtualTransMapper;
import com.alycloud.pay.gateway.mapper.PaymentTransMapper;
import com.alycloud.pay.gateway.models.BranchBean;
import com.alycloud.pay.gateway.models.agent.AgentBean;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProxyServiceImpl implements IProxyService{

	@Value("${withdrawFee}")
    private String withdrawFee;
	@Autowired
    private BranchMapper branchMapper;
    @Autowired
    private AgentMapper agentMapper;
    @Autowired
    private MerchVirtualTransMapper merchVirtualTransMapper;
    @Autowired
	private MerchVirtualCardMapper merchVirtualCardMapper;
    @Autowired
    private PaymentTransMapper paymentTransMapper;
    
	@Override
	@ServiceLogAnnotation(moduleName="创建商户提现流水")
	public MerchVirtualTrans createMerchVirtualTrans(ProxyDTO proxyDto, MerchInfo merch, MerchVirtualCard mvcard) {
		log.info("根据机构号[" + merch.getBranchno() + "]查询机构信息");
        BranchBean branch = branchMapper.getByBranchno(merch.getBranchno());
        log.info(branch.toString());
        log.info("根据代理商号[" + merch.getFirstAgentno() + "]查询代理商信息");
        AgentBean agent = agentMapper.getByAgentno(merch.getFirstAgentno());
        log.info(agent.toString());
        BigDecimal payAmount = new BigDecimal(proxyDto.getTransactionamount()).subtract(new BigDecimal(withdrawFee));
		log.info("最终代付金额(元):{}", payAmount);
		
		MerchVirtualTrans mvtrans = new MerchVirtualTrans();
		mvtrans.setTransDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		mvtrans.setTransTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		mvtrans.setBranchno(branch.getBranchno());
		mvtrans.setBranchName(branch.getBranchName());
		mvtrans.setAgentno(agent.getAgentno());
		mvtrans.setAgentName(agent.getAgentName());
		mvtrans.setMerchno(merch.getMerchno());
		mvtrans.setMerchName(merch.getMerchName());
		mvtrans.setCardno(mvcard.getCardno());
		mvtrans.setAmount(payAmount);
		mvtrans.setTraceno(proxyDto.getBusinessnumber());
		mvtrans.setStatus(1);
		mvtrans.setAmountBefore(mvcard.getAvailAmount());
		mvtrans.setAmountAfter(mvcard.getAvailAmount().subtract(new BigDecimal(proxyDto.getTransactionamount())));
		mvtrans.setTransAmount(new BigDecimal(proxyDto.getTransactionamount()));
		mvtrans.setTransFee(new BigDecimal(withdrawFee));
		mvtrans.setAddTime(DateFormat.DATE_TIME.format());
		mvtrans.setRemark(proxyDto.getSubject());
		mvtrans.setDfType(2);
		mvtrans.setPayStatus(1);
		mvtrans.setPayMsg("正在出款");
		mvtrans.setPayType(2);
		mvtrans.setAccountno(proxyDto.getBankcardnumber());
		mvtrans.setAccountName(proxyDto.getBankcardname());
		mvtrans.setBankName(proxyDto.getBankname());
		mvtrans.setBankno(proxyDto.getBanknumber());
		mvtrans.setWithdrawFee(new BigDecimal(withdrawFee));
		
		merchVirtualTransMapper.createMerchVirtualTrans(mvtrans);
		
		return mvtrans;
	}

	@Override
	@ServiceLogAnnotation(moduleName="修改商户虚拟账户信息")
	public void modifyMerchVirtualCard(MerchVirtualTrans mvtrans) {
		MerchVirtualCard mvcrad = new MerchVirtualCard();
		mvcrad.setAvailAmount(mvtrans.getAmountAfter());
		//mvcrad.setTransitAmount(mvtrans.getTransAmount());
		mvcrad.setMerchno(mvtrans.getMerchno());
		mvcrad.setChannelType(-1);
		
		merchVirtualCardMapper.modifyMerchVirtualCard(mvcrad);
	}

	@Override
	@ServiceLogAnnotation(moduleName="获取商户提现信息")
	public MerchVirtualTrans getMerchVirtualTrans(String traceno) {
		return merchVirtualTransMapper.getMerchVirtualTrans(traceno);
	}

	@Override
	@ServiceLogAnnotation(moduleName="修改商户提现状态")
	public int modifyMerchVirtualTrans(MerchVirtualTrans merchVirtualTrans) {
		return merchVirtualTransMapper.modifyMerchVirtualTrans(merchVirtualTrans);
	}

	@Override
	@ServiceLogAnnotation(moduleName="获取商户虚拟账户信息")
	public MerchVirtualCard getMerchVirtualCard(String merchno) {
		return merchVirtualCardMapper.getMerchVirtualCard(merchno);
	}

	@Override
	@ServiceLogAnnotation(moduleName="创建商户批量提现流水")
	public List<MerchVirtualTrans> createBatchMerchVirtualTrans(BatchProxyDTO batProxyDto,List<ProxyDTO> proxyList, MerchInfo merch,
			MerchVirtualCard mvcard) {
		log.info("根据机构号[" + merch.getBranchno() + "]查询机构信息");
        BranchBean branch = branchMapper.getByBranchno(merch.getBranchno());
        log.info(branch.toString());
        log.info("根据代理商号[" + merch.getFirstAgentno() + "]查询代理商信息");
        AgentBean agent = agentMapper.getByAgentno(merch.getFirstAgentno());
        log.info(agent.toString());
        BigDecimal payAmount = new BigDecimal(batProxyDto.getBatchamount()).subtract(new BigDecimal(withdrawFee).
        		multiply(new BigDecimal(proxyList.size())));
		log.info("最终代付金额(元):{}", payAmount);
		
		List<MerchVirtualTrans> resultList = new ArrayList<MerchVirtualTrans>();
		BigDecimal amountBefore=mvcard.getAvailAmount();
		for(ProxyDTO proxyDto:proxyList){
			MerchVirtualTrans mvtrans = new MerchVirtualTrans();
			mvtrans.setTransDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			mvtrans.setTransTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
			mvtrans.setBranchno(branch.getBranchno());
			mvtrans.setBranchName(branch.getBranchName());
			mvtrans.setAgentno(agent.getAgentno());
			mvtrans.setAgentName(agent.getAgentName());
			mvtrans.setMerchno(merch.getMerchno());
			mvtrans.setMerchName(merch.getMerchName());
			mvtrans.setCardno(mvcard.getCardno());
			mvtrans.setAmount(new BigDecimal(proxyDto.getTransactionamount()).subtract(new BigDecimal(withdrawFee)));
			mvtrans.setTraceno(proxyDto.getBusinessnumber());
			mvtrans.setStatus(1);
			mvtrans.setAmountBefore(amountBefore);
			mvtrans.setAmountAfter(amountBefore.subtract(new BigDecimal(proxyDto.getTransactionamount())));
			mvtrans.setTransAmount(new BigDecimal(proxyDto.getTransactionamount()));
			mvtrans.setTransFee(new BigDecimal(withdrawFee));
			mvtrans.setAddTime(DateFormat.DATE_TIME.format());
			mvtrans.setRemark(proxyDto.getSubject());
			mvtrans.setDfType(2);
			mvtrans.setPayStatus(1);
			mvtrans.setPayMsg("正在出款");
			mvtrans.setPayType(2);
			mvtrans.setBatchno(batProxyDto.getBatchno());
			mvtrans.setAccountno(proxyDto.getBankcardnumber());
			mvtrans.setAccountName(proxyDto.getBankcardname());
			mvtrans.setBankName(proxyDto.getBankname());
			mvtrans.setBankno(proxyDto.getBanknumber());
			mvtrans.setWithdrawFee(new BigDecimal(withdrawFee));
			
			amountBefore=amountBefore.subtract(new BigDecimal(proxyDto.getTransactionamount()));
			resultList.add(mvtrans);
		}
		
		merchVirtualTransMapper.createBatchMerchVirtualTrans(resultList);
		return resultList;
	}

	@Override
	@ServiceLogAnnotation(moduleName="创建商户代付流水")
	public PaymentTrans createPaymentTrans(MerchInfo merch, MerchVirtualTrans merchVirtualTrans) {
		PaymentTrans payTrans = new PaymentTrans();
		payTrans.setAccountName(merchVirtualTrans.getAccountName());
		payTrans.setAccountNo(merchVirtualTrans.getAccountno());
		payTrans.setAddTime(DateFormat.DATE_TIME.format());
		payTrans.setBankName(merchVirtualTrans.getBankName());
		payTrans.setBankNo(merchVirtualTrans.getBankno());
		payTrans.setBizType(PaymentTransBizType.MERCH_DRAW.ordinal());
		payTrans.setIdCardNo(merch.getIdentityCard());
		payTrans.setIsPayAgain(0);
		payTrans.setLiquidateType(0);
		payTrans.setMobile(merch.getMobile());
		payTrans.setPayType(1);
		payTrans.setPayAmount(merchVirtualTrans.getAmount());
		payTrans.setPayDescr(PaymentTransPayStatus.PAYING.getText());
		//payTrans.setPayOrderno(payOrderno);
		payTrans.setPayStatus(PaymentTransPayStatus.PAYING.ordinal());
		payTrans.setPayTime(DateFormat.DATE_TIME.format());
		payTrans.setRefno(merchVirtualTrans.getTraceno());
		payTrans.setSettleType(1);
		payTrans.setTransAmount(merchVirtualTrans.getTransAmount());
		payTrans.setTransFee(merchVirtualTrans.getTransFee());
		payTrans.setWithdrawFee(merchVirtualTrans.getTransFee());
		payTrans.setBatchno(merchVirtualTrans.getBatchno());
		
		paymentTransMapper.createPaymentTrans(payTrans);
		return payTrans;
	}

	@Override
	@ServiceLogAnnotation(moduleName="创建商户批量代付流水")
	public List<PaymentTrans> createBatchPaymentTrans(MerchInfo merch, List<MerchVirtualTrans> list) {
		List<PaymentTrans> paymentTranlist = new ArrayList<PaymentTrans>();
		
		for(MerchVirtualTrans mvt:list){
			PaymentTrans payTrans = new PaymentTrans();
			payTrans.setAccountName(mvt.getAccountName());
			payTrans.setAccountNo(mvt.getAccountno());
			payTrans.setAddTime(DateFormat.DATE_TIME.format());
			payTrans.setBankName(mvt.getBankName());
			payTrans.setBankNo(mvt.getBankno());
			payTrans.setBizType(PaymentTransBizType.MERCH_DRAW.ordinal());
			payTrans.setIdCardNo(merch.getIdentityCard());
			payTrans.setIsPayAgain(0);
			payTrans.setLiquidateType(0);
			payTrans.setMobile(merch.getMobile());
			payTrans.setPayType(1);
			payTrans.setPayAmount(mvt.getAmount());
			payTrans.setPayDescr(PaymentTransPayStatus.PAYING.getText());
			payTrans.setPayStatus(PaymentTransPayStatus.PAYING.ordinal());
			payTrans.setPayTime(DateFormat.DATE_TIME.format());
			payTrans.setRefno(mvt.getTraceno());
			payTrans.setSettleType(1);
			payTrans.setTransAmount(mvt.getTransAmount());
			payTrans.setTransFee(mvt.getTransFee());
			payTrans.setWithdrawFee(mvt.getTransFee());
			payTrans.setBatchno(mvt.getBatchno());
			//payTrans.setBatchPayDetails(mvt.getBatchPayDetails());
			
			paymentTranlist.add(payTrans);
		}
		paymentTransMapper.createBatchPaymentTrans(paymentTranlist);
		return paymentTranlist;
	}
}
