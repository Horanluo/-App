package com.alycloud.pay.api;

import java.util.List;

import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.search.QrcodeOrder4Search;
import com.alycloud.modules.vo.QrcodePayDataVO;

/**
 * 二维码交易订单Service
 * @author Moyq5
 * @date 2017年10月16日
 */
public interface IQrcodeOrderService {

	QrcodeOrder buildOrder(String merchno, QrcodePayDataVO data) throws Exception;

	void add(QrcodeOrder data);

	String genRefno() throws Exception;

	void mod(QrcodeOrder data);

	List<QrcodeOrder> listByPage(QrcodeOrder4Search order4s);

	/**
	 * 支付成功处理
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	void paySuccess(String orderno);

	/**
	 * 付款状态查询
	 * @author Moyq5
	 * @throws Exception 
	 * @date 2017年11月12日
	 */
	void checkPayStatus(String orderno) throws Exception;

	/**
	 * 扫码支付成功处理
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	void qrcodePaySuccess(QrcodeOrder order);
	
	QrcodeOrder getOrderInfo(QrcodeOrder order);
}
