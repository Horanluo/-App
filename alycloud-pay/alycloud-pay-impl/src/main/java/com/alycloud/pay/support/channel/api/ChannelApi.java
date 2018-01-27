package com.alycloud.pay.support.channel.api;

import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.vo.ExternalQrcodeVO;
import com.alycloud.pay.out.model.OutQueryResult;

/**
 * 渠道接口
 * @author Moyq5
 * @date 2017年8月2日
 */
public interface ChannelApi {

	/**
	 * 订单状态查询
	 * @author Moyq5
	 * @date 2017年8月4日
	 * @return
	 * @throws ChannelApiException
	 */
	ChannelOrderStatusResult query() throws ChannelApiException;
	
	/**
	 * 微信APP支付，即用户在商家APP上直接拉起微信支付
	 * @author Moyq5
	 * @date 2017年9月28日
	 * @return
	 * @throws ChannelApiException
	 */
	ChannelWeixinAppOrderResult weixinAppPay() throws ChannelApiException;

	/**
	 * 扫码支付，即商家扫用户付款码收款
	 * @author Moyq5
	 * @date 2017年10月12日
	 * @return
	 * @throws ChannelApiException
	 */
	ChannelQrcodeOrderResult scanPay() throws ChannelApiException;

	/**
	 * 条码付，即用户扫商家收款码付款
	 * @author Moyq5
	 * @date 2017年10月12日
	 * @return
	 * @throws ChannelApiException
	 */
	ChannelQrcodeOrderResult qrcodePay(QrcodeOrder order) throws ChannelApiException;

	/**
	 * 公众号支付，即在通道（微信、支付宝等）内置浏览器内拉起支付，一般支付前支付用户授权
	 * @author Moyq5
	 * @date 2017年10月12日
	 * @return
	 * @throws ChannelApiException
	 */
	ChannelQrcodeOrderResult officalPay() throws ChannelApiException;

	/**
	 * 快捷支付
	 * @author Moyq5
	 * @date 2017年10月19日
	 * @return
	 * @throws ChannelApiException
	 */
	ChannelFastOrderResult fastPay() throws ChannelApiException;
	
	/**
	 * 二维码订单状态查询
	 * @author Moyq5
	 * @date 2017年8月4日
	 * @return
	 * @throws ChannelApiException
	 */
	ChannelOrderStatusResult queryQrcodePay(QrcodeOrder order) throws ChannelApiException;
	
	/**
	 * 对外条码付，即用户扫商家收款码付款
	 * @author Moyq5
	 * @date 2017年10月12日
	 * @return
	 * @throws ChannelApiException
	 */
	ChannelQrcodeOrderResult outQrcodePay(QrcodeOrder order,ExternalQrcodeVO qrcodeVO) throws ChannelApiException;
	
	/**
	 * 查询二维码对外订单状态
	 * @author Horanluo
	 * @date 2018年01月12日
	 * @return
	 * @throws ChannelApiException
	 */
	OutQueryResult queryOutQrcodePay(ExternalQrcodeVO qrcodeVO) throws ChannelApiException;
}
