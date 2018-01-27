package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.search.QrcodeOrder4Search;

/**
 * 二维码支付订单
 * @author Moyq5
 * @date 2017年6月8日
 */
public interface QrcodeOrderMapper {

	public int add(QrcodeOrder order);
	
	public int mod(QrcodeOrder order);

	public List<QrcodeOrder> listByPage(QrcodeOrder4Search order4s);

	public String genRefno();
	
	public int modifyQrcodeOrder(QrcodeOrder order);
	
	public QrcodeOrder getOrderInfo(QrcodeOrder order);
}
