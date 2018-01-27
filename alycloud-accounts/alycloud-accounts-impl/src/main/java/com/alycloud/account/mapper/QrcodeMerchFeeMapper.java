package com.alycloud.account.mapper;

import java.util.List;
import java.util.Map;

import com.alycloud.modules.entity.QrcodeMerchFee;

/**
 * 商户二维码费率
 * @author Moyq5
 * @date 2017年5月23日
 */
public interface QrcodeMerchFeeMapper {
	
	public List<QrcodeMerchFee> list(Map<String,String> queryParam);
}
