package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.QrcodeChannelMerchFee;
import com.alycloud.modules.search.QrcodeChannelMerchFee4Search;


/**
 * 渠道商户二维码费率
 * @author Moyq5
 * @date 2017年10月10日
 */
public interface QrcodeChannelMerchFeeMapper {
	
	public int addList(List<QrcodeChannelMerchFee> qrcodeFees);
	
	public int add(QrcodeChannelMerchFee qrcodeFee);
	
	public int mod(QrcodeChannelMerchFee qrcodeFee);
	
	public List<QrcodeChannelMerchFee> list(QrcodeChannelMerchFee4Search qrcodeFee4s);
}
