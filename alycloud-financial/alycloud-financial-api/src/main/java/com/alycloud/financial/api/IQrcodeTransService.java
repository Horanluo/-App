package com.alycloud.financial.api;

import java.util.List;

import com.alycloud.modules.entity.QrcodeTrans;
import com.alycloud.modules.search.QrcodeTrans4Search;

/**
 * 二维码交易流水Service
 * @author Moyq5
 * @date 2017年11月7日
 */
public interface IQrcodeTransService {

	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年11月7日
	 * @param trans4s
	 * @return
	 */
	List<QrcodeTrans> listByPage(QrcodeTrans4Search trans4s);

	/**
	 * 根据订单号生成交易流水
	 * @author Moyq5
	 * @throws Exception 
	 * @date 2017年11月8日
	 */
	void addByOrderno(String data) throws Exception;

}
