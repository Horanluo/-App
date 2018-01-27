package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.QrcodeChannelFee;
import com.alycloud.modules.search.QrcodeChannelFee4Search;


/**
 * 渠道二维码费率
 * @author Moyq5
 * @date 2017年10月18日
 */
public interface QrcodeChannelFeeMapper {
	
	public int addList(List<QrcodeChannelFee> qrcodeFees);
	
	public int add(QrcodeChannelFee qrcodeFee);
	
	public int mod(QrcodeChannelFee qrcodeFee);
	
	public List<QrcodeChannelFee> list(QrcodeChannelFee4Search qrcodefee4s);
}
