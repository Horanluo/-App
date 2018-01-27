package com.alycloud.financial.mapper;

import java.util.List;

import com.alycloud.modules.entity.AgentVirtualTrans;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.modules.entity.PaymentTrans;
import com.alycloud.modules.search.PaymentTrans4Search;



/**
 * 代付（出款）流水
 * @author Moyq5
 * @date 2017年7月28日
 */
public interface PaymentTransMapper {

	public void add(PaymentTrans trans);
	
	public List<PaymentTrans> listByPage(PaymentTrans4Search paymentTrans4s);

	public String genRefno();
	
	public Integer modifyRecord(AgentVirtualTrans agentVirtualTrans);
	
	public Integer modifyMerchRecord(MerchVirtualTrans merchVirtualTrans);
}
