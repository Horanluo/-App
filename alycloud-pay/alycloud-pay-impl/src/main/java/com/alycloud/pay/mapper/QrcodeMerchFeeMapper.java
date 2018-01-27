package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.QrcodeMerchFee;
import com.alycloud.modules.search.QrcodeMerchFee4Search;


/**
 * 商户二维码费率
 * @author Moyq5
 * @date 2017年5月23日
 */
public interface QrcodeMerchFeeMapper {
	
	public int addList(List<QrcodeMerchFee> qrcodeFees);
	
	public int add(QrcodeMerchFee qrcodeFee);
	
	public int mod(QrcodeMerchFee qrcodeFee);
	
	public List<QrcodeMerchFee> list(QrcodeMerchFee4Search qrcodefee4s);

	public void delByMerchno(String merchno);

}
