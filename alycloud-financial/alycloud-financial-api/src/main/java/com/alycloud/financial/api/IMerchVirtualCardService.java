package com.alycloud.financial.api;

import java.util.List;

import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.modules.search.MerchVirtualCard4Search;

/**
 * 商户虚拟账户Service
 * @author Moyq5
 * @date 2017年11月13日
 */
public interface IMerchVirtualCardService {

	/**
	 * 提现
	 * @author Moyq5
	 * @date 2017年11月13日
	 * @param merch 提现商户
	 * @param amount 提现金额(元)
	 * @throws Exception 
	 */
	MerchVirtualTrans draw(MerchInfo merch, String amount) throws Exception;

	/**
	 * 
	 * @author Moyq5
	 * @date 2017年11月13日
	 * @param card4s
	 * @return
	 */
	List<MerchVirtualCard> listByPage(MerchVirtualCard4Search card4s);

	/**
	 * 增加或者减少可提现金额。
	 * 本方法只用到两个参数：id,availAmount;<br>
	 * 当availAmount为正数时，可提现金额会在当前提可提现金额基础上增加该值，否则在当前提可提现金额基础上减去该值。
	 * @author Moyq5
	 * @date 2017年11月13日
	 * @param card
	 */
	void addAvailAmount(MerchVirtualCard card4AvailAmount);

	/**
	 * 创建虚拟账户
	 * @author Moyq5
	 * @date 2017年11月13日
	 * @param merchno
	 * @return
	 * @throws Exception 
	 */
	MerchVirtualCard add4Merch(String merchno) throws Exception;

}
