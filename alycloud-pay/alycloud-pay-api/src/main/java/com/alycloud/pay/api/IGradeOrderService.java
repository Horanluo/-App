package com.alycloud.pay.api;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alycloud.modules.entity.GradeOrder;
import com.alycloud.modules.search.GradeOrder4Search;

/**
 * 商户升级支付订单
 * @author Moyq5
 * @date 2017年10月30日
 */
public interface IGradeOrderService {

	/**
	 * 添加
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param order
	 */
	void add(GradeOrder order);
	
	/**
	 * 更新
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param order
	 */
	void mod(GradeOrder order);

	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param order4s
	 * @return
	 */
	List<GradeOrder> listByPage(GradeOrder4Search order4s);
	

	/**
	 * 根据ID获取单记录
	 * @author Moyq5
	 * @date 2017年11月5日
	 * @param data
	 * @return
	 */
	GradeOrder getById(Integer data);
	
	/**
	 * 统计金额
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param order4s
	 * @return
	 */
	BigDecimal sumAmount(GradeOrder4Search order4s);

	/**
	 * 升级支付订单支付成功通知处理
	 * @author Moyq5
	 * @date 2017年10月31日
	 * @param req
	 */
	void notifyCallback(HttpServletRequest req);

	/**
	 * 支付成功处理
	 * @author Moyq5
	 * @date 2017年11月15日
	 * @param orderno
	 */
	void paySuccess(String orderno);

}
