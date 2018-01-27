package com.alycloud.financial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.financial.api.IFastTransService;
import com.alycloud.financial.feign.FastOrderFeign;
import com.alycloud.financial.mapper.FastTransMapper;
import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.entity.FastTrans;
import com.alycloud.modules.enums.FastOrderStatus;
import com.alycloud.modules.enums.FastSettleStatus;
import com.alycloud.modules.search.FastOrder4Search;
import com.alycloud.modules.search.FastTrans4Search;

/**
 * 蹇嵎浜ゆ槗Service瀹炵幇绫�
 * @author Moyq5
 * @date 2017骞�11鏈�7鏃�
 */
@Service
public class FastTransService implements IFastTransService {

	@Autowired
	private FastTransMapper fastTransMapper;
	@Autowired
	private FastOrderFeign fastOrderFeign;

	@Override
	public List<FastTrans> listByPage(FastTrans4Search trans4s) {
		return fastTransMapper.listByPage(trans4s);
	}

	@Override
	public void addByOrderno(String orderno) throws Exception {
		FastOrder4Search order4s = new FastOrder4Search();
		order4s.setOrderno(orderno);
		RequestBean<FastOrder4Search> orderData4s = new RequestBean<FastOrder4Search>();
		orderData4s.setData(order4s);
		FastOrder order = fastOrderFeign.listByPage(orderData4s).getData().get(0);
		if (order.getStatus() != FastOrderStatus.SUCCESS.ordinal()) {
			throw new Exception("蹇嵎浜ゆ槗璁㈠崟鏈敮浠橈紝涓嶈兘鍒涘缓浜ゆ槗娴佹按");
		}
		
		FastTrans4Search trans4s = new FastTrans4Search();
		trans4s.setOrderno(orderno);
		List<FastTrans> transList = fastTransMapper.listByPage(trans4s);
		if (transList.size() > 0) {
			throw new Exception("蹇嵎浜ゆ槗娴佹按宸茬粡瀛樺湪锛屼笉鑳介噸澶嶆坊鍔�");
		}
		
		FastTrans trans = new FastTrans();
		trans.setAddTime(DateFormat.DATE_TIME.format());
		trans.setAgentName(order.getAgentName());
		trans.setAgentno(order.getAgentno());
		trans.setAmount(order.getAmount());
		trans.setBankCard(order.getBankCard());
		trans.setBankName(order.getBankName());
		trans.setBranchFee(order.getBranchFee());
		trans.setBranchName(order.getBranchName());
		trans.setBranchno(order.getBranchno());
		trans.setChannelFee(order.getChannelFee());
		trans.setChannelOrderno(order.getChannelOrderno());
		trans.setChannelType(order.getChannelType());
		trans.setDescr(order.getDescr());
		trans.setIdCard(order.getIdCard());
		trans.setLiquidateType(order.getLiquidateType());
		trans.setMerchFee(order.getMerchFee());
		trans.setMerchName(order.getMerchName());
		trans.setMerchno(order.getMerchno());
		trans.setMobile(order.getMobile());
		trans.setOrderno(order.getOrderno());
		trans.setSettleDescr(FastSettleStatus.SUCCESS.getText());
		trans.setSettleStatus(FastSettleStatus.SUCCESS.ordinal());
		trans.setSettleTime(DateFormat.DATE_TIME.format());
		trans.setSettleType(order.getSettleType());
		trans.setStatus(order.getStatus());
		trans.setTrueName(order.getTrueName());
		trans.setPayerRemark(order.getPayerRemark());
		trans.setFee(order.getFee());
		trans.setReceiveFee(order.getReceiveFee());
		trans.setRate(order.getRate());
		fastTransMapper.add(trans);
	}
	
}
