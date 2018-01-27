package com.alycloud.pay.api;

import java.util.List;

import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.search.FastOrder4Search;
import com.alycloud.modules.vo.FastPayDataVO;

/**
 * 快捷交易订单Service
 * @author Moyq5
 * @date 2017年10月19日
 */
public interface IFastOrderService {

	FastOrder buildOrder(String merchno, FastPayDataVO data) throws Exception;

	List<FastOrder> listByPage(FastOrder4Search order4s);

	void add(FastOrder data);

	/**
	 * 支付成功处理
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	void paySuccess(String data);

	void mod(FastOrder order4Mod);

	/**
	 * 检查订单支付状态
	 * @author Moyq5
	 * @date 2017年11月12日
	 * @param orderno
	 * @throws Exception
	 */
	void checkPayStatus(String orderno) throws Exception;

}
