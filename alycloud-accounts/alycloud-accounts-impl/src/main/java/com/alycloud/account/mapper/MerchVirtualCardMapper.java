package com.alycloud.account.mapper;

import java.util.List;

import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.search.MerchVirtualCard4Search;

/**
 * 商户虚拟账户的持久层
 * @author Moyq5
 * @date 2017年3月14日
 */
public interface MerchVirtualCardMapper {

	public int add(MerchVirtualCard card);

	public String getMaxCardo();

	public List<MerchVirtualCard> listByPage(MerchVirtualCard4Search card4s);

	/**
	 * 增加或者减少可提现金额。
	 * 本方法只用到两个参数：id,availAmount;<br>
	 * 当availAmount为正数时，可提现金额会在当前提可提现金额基础上增加该值，否则在当前提可提现金额基础上减去该值。
	 * @author Moyq5
	 * @date 2017年11月13日
	 * @param card
	 */
	public void addAvailAmount(MerchVirtualCard card4AvailAmount);
	
	/**
	 * 修改虚拟账户记录
	 * @author Horanluo
	 */
	Integer modifyRecord(MerchVirtualCard mvc); 
	
	List<MerchVirtualCard> getByMerchno(String merchno);
}
