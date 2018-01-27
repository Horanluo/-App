package com.alycloud.pay.api;

import java.util.List;

import com.alycloud.modules.entity.QrcodeMerchFee;
import com.alycloud.modules.search.QrcodeMerchFee4Search;

/**
 * 商户二维码费率
 * @author Moyq5
 * @date 2017年10月29日
 */
public interface IQrcodeMerchFeeService {

	/**
	 * 添加
	 * @author Moyq5
	 * @date 2017年10月29日
	 * @param data
	 */
	void addList(List<QrcodeMerchFee> data);

	/**
	 * 删除指定商户的费率
	 * @author Moyq5
	 * @date 2017年10月29日
	 * @param merchno
	 */
	void delByMerchno(String merchno);

	
	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月29日
	 * @param data
	 * @return
	 */
	List<QrcodeMerchFee> list(QrcodeMerchFee4Search data);

}
