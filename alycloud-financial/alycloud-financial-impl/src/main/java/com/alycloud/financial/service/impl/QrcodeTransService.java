package com.alycloud.financial.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.financial.api.IQrcodeTransService;
import com.alycloud.financial.feign.QrcodeOrderFeign;
import com.alycloud.financial.mapper.QrcodeTransMapper;
import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.entity.QrcodeTrans;
import com.alycloud.modules.enums.QrcodeOrderStatus;
import com.alycloud.modules.enums.QrcodeTransPaymentStatus;
import com.alycloud.modules.enums.SysLiquidateType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.search.QrcodeOrder4Search;
import com.alycloud.modules.search.QrcodeTrans4Search;

/**
 * 二维码交易Service实现类
 * @author Moyq5
 * @date 2017年11月7日
 */
@Service
public class QrcodeTransService implements IQrcodeTransService {

	@Autowired
	private QrcodeTransMapper qrcodeTransMapper;
	@Autowired
	private QrcodeOrderFeign qrcodeOrderFeign;

	@Override
	public List<QrcodeTrans> listByPage(QrcodeTrans4Search trans4s) {
		List<QrcodeTrans> list = qrcodeTransMapper.listByPageOnToday(trans4s);
		if (list.size() == 0) {
			list = qrcodeTransMapper.listByPageOnHistory(trans4s);
		}
		return list;
	}

	@Override
	public void addByOrderno(String orderno) throws Exception {
		QrcodeOrder4Search order4s = new QrcodeOrder4Search();
		order4s.setOrderno(orderno);
		RequestBean<QrcodeOrder4Search> orderData = new RequestBean<QrcodeOrder4Search>();
		orderData.setData(order4s);
		QrcodeOrder order = qrcodeOrderFeign.listByPage(orderData).getData().get(0);
		if (order.getStatus() != QrcodeOrderStatus.SUCCESS.ordinal()) {
			throw new Exception("二维码交易订单未支付，不能创建交易流水");
		}
		
		QrcodeTrans4Search trans4s = new QrcodeTrans4Search();
		trans4s.setOrderno(orderno);
		List<QrcodeTrans> list = listByPage(trans4s);
		Log.info("二维码交易流水:{},",list);
		if (list.size() > 0) {
			throw new Exception("二维码交易流水已经存在，不能重复添加");
		}
		
		QrcodeTransPaymentStatus paymentStatus = 
				(order.getSettleType() == SysSettleType.T0.ordinal()
				 && order.getLiquidateType() == SysLiquidateType.PLATFORM.ordinal()) ?
				QrcodeTransPaymentStatus.NEW : QrcodeTransPaymentStatus.NEEDLESS;
		
		QrcodeTrans trans = new QrcodeTrans();
		trans.setAccountName(order.getAccountName());
		trans.setAccountno(order.getAccountno());
		trans.setAgentName(order.getAgentName());
		trans.setAgentno(order.getAgentno());
		//trans.setBackupOrderno(backupOrderno);
		//trans.setBankCardType(order.getBankCardType());
		trans.setBankName(order.getBankName());
		trans.setBankno(order.getBankno());
		trans.setBankType(order.getBankType());
		//trans.setBarCode(barCode);
		trans.setBranchFee(order.getBranchFee());
		trans.setBranchName(order.getBranchName());
		trans.setBranchno(order.getBranchno());
		trans.setCertno(order.getCertno());
		trans.setChannelCode(order.getChannelCode());
		trans.setChannelFee(order.getChannelFee());
		trans.setChannelOrderno(order.getChannelOrderno());
		//trans.setCheckStatus(checkStatus);
		//trans.setCheckTime(checkTime);
		trans.setDiscountAmount(BigDecimal.ZERO);
		//trans.setId(id);
		trans.setInterType(4);// 4-接口接入
		//trans.setLiquidator(liquidator);
		trans.setLiquidateType(order.getLiquidateType());
		trans.setMerchName(order.getMerchName());
		trans.setMerchno(order.getMerchno());
		trans.setMobile(order.getMobile());
		//trans.setMobileBuyer(mobileBuyer);
		//trans.setMobileSeller(mobileSeller);
		//trans.setNotifyStatus(notifyStatus);
		trans.setNotifyUrl(order.getNotifyUrl());
		trans.setOrderno(order.getOrderno());
		trans.setPartnerId(order.getChannelMerchno());
		//trans.setPayDesc(payDesc);
		trans.setPayer(order.getPayer());
		trans.setPayerBiz(order.getPayerBiz());
		trans.setPayerRemark(order.getPayerRemark());
		trans.setPayerType(order.getPayerType());
		trans.setPaymentFee(order.getPaymentFee());
		trans.setPaymentStatus(paymentStatus.ordinal());
		//trans.setPayStatus(payStatus);
		trans.setPayType(order.getPayType());
		trans.setRebackAmount(BigDecimal.ZERO);
		//trans.setRechargeStatus(rechargeStatus);
		//trans.setRemark(remark);
		trans.setReturnUrl(order.getReturnUrl());
		trans.setScanType(order.getScanType());
		trans.setSettleType(order.getSettleType());
		trans.setStatus(order.getStatus());
		//trans.setT0AddFee(t0AddFee);
		//trans.setTermno(termno);
		trans.setTotalFee(order.getMerchFee());
		trans.setTraceno(order.getTraceno());
		trans.setTransAmount(order.getAmount());
		trans.setTransDate(order.getTransDate());
		trans.setTransTime(order.getTransTime());
		trans.setFee(order.getFee());
		trans.setReceiveFee(order.getReceiveFee());
		trans.setRate(order.getRate());
		qrcodeTransMapper.add(trans);
		
	}
}
