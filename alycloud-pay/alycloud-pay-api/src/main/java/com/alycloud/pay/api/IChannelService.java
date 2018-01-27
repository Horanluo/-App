package com.alycloud.pay.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.vo.PayTypeResultVO;

/**
 * 二维码渠道Service
 * @author Moyq5
 * @date 2017年10月20日
 */
public interface IChannelService {

	/**
	 * 根据支付方式获取渠道列表信息
	 * @author Moyq5
	 * @date 2017年10月20日
	 * @param merchno 平台商户号
	 * @param payType 支付方式
	 * @return
	 * @throws Exception
	 */
	List<PayTypeResultVO> getPayTypeResult(String merchno, QrcodePayType payType) throws Exception;

	/**
	 * 获取快捷支付渠道列表信息
	 * @author Moyq5
	 * @date 2017年10月21日
	 * @param merchno 平台商户号
	 * @return
	 * @throws Exception
	 */
	List<PayTypeResultVO> getPayTypeResult(String merchno) throws Exception;

	/**
	 * 平安通道支付回调处理
	 * @author Moyq5 
	 * @date 2017年11月11日
	 */
	String notifyByPingan(HttpServletRequest req);

	/**
	 * 御付（扫码）通道支付回调处理
	 * @author Moyq5 
	 * @throws IOException 
	 * @date 2017年11月11日
	 */
	String notifyByYufuQrcode(HttpServletRequest req) throws IOException;

	/**
	 * 御付（快捷）通道支付回调处理
	 * @author Moyq5 
	 * @throws IOException 
	 * @date 2017年11月11日
	 */
	String notifyByYufuFast(HttpServletRequest req) throws IOException;

	List<PayTypeResultVO> getPayTypeResult2(String merchno, QrcodePayType payType) throws Exception;

    /**
     * 根据支付类型、商户级别列出商户所有的收款渠道
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年11月20日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    List<PayTypeResultVO> listAllChannelByType(String merchno, QrcodePayType channelType) throws Exception;

}
