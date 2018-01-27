package com.alycloud.pay.api;

import java.util.List;

import com.alycloud.modules.entity.QrcodeMerch;
import com.alycloud.modules.search.QrcodeMerch4Search;
import com.alycloud.modules.search.QrcodeMerch4Search4Pay;

/**
 * 渠道商户
 * @author Moyq5
 * @date 2017年11月11日
 */
public interface IQrcodeMerchService {

	List<QrcodeMerch> listByPage(QrcodeMerch4Search data);

	List<QrcodeMerch> list4Pay(QrcodeMerch4Search4Pay data);

}
