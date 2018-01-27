package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.QrcodeMerch;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.search.QrcodeMerch4Search;
import com.alycloud.modules.search.QrcodeMerch4Search4Pay;

/**
 * 二维码渠道商户
 * @author Moyq5
 * @date 2017年6月20日
 */
public interface QrcodeMerchMapper {

	public int add(QrcodeMerch merch);
	
	public int mod(QrcodeMerch merch);
	
	public List<QrcodeMerch> listByPage(QrcodeMerch4Search merch4s);
	
	/**
	 * 查询可用于支付的渠道商户
	 * @author Moyq5
	 * @date 2017年6月20日
	 */
	public List<QrcodeMerch> list4Pay(QrcodeMerch4Search4Pay merch4s);

	public void delById(Integer id);

	/**
	 * 查询未进行渠道进件的（平台）商户号
	 * @author Moyq5
	 * @date 2017年9月8日
	 */
	public List<String> listMerchnoNotAdd4Channel(SysChannelType channelType);
}
