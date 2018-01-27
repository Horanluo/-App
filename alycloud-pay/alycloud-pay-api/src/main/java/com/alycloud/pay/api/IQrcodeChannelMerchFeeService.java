package com.alycloud.pay.api;

import java.util.List;

import com.alycloud.modules.entity.QrcodeChannelMerchFee;
import com.alycloud.modules.search.QrcodeChannelMerchFee4Search;

/**
 * 渠道商户二维码费率
 * @author Moyq5
 * @date 2017年11月16日
 */
public interface IQrcodeChannelMerchFeeService {

	List<QrcodeChannelMerchFee> list(QrcodeChannelMerchFee4Search fee4s);

}
