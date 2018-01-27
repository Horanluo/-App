package com.alycloud.pay.out.service;

import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.vo.ExternalQrcodeVO;

/**
 * 对外接口---扫码支付
 * @author Horanluo
 */
public interface IQrcodeService {

	QrcodeOrder buildOrder(ExternalQrcodeVO qrcodeVO) throws Exception;
	
	QrcodeOrder getOrderInfo(QrcodeOrder order);
}
