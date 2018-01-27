package com.alycloud.pay.gateway.api;

import java.util.List;

import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.modules.entity.PaymentTrans;
import com.alycloud.pay.gateway.dto.BatchProxyDTO;
import com.alycloud.pay.gateway.dto.ProxyDTO;

public interface IProxyService {

	MerchVirtualTrans createMerchVirtualTrans(ProxyDTO proxyDto,MerchInfo merch,MerchVirtualCard mvcard);
	
	void modifyMerchVirtualCard(MerchVirtualTrans mvtrans);
	
	MerchVirtualTrans getMerchVirtualTrans(String traceno);
	
	int modifyMerchVirtualTrans(MerchVirtualTrans merchVirtualTrans);
	
	MerchVirtualCard getMerchVirtualCard(String merchno);
	
	List<MerchVirtualTrans> createBatchMerchVirtualTrans(BatchProxyDTO batProxyDto,List<ProxyDTO> proxyList,MerchInfo merch,MerchVirtualCard mvcard);
	
	PaymentTrans createPaymentTrans(MerchInfo merch,MerchVirtualTrans merchVirtualTrans);
	
	List<PaymentTrans> createBatchPaymentTrans(MerchInfo merch,List<MerchVirtualTrans> list);
}
