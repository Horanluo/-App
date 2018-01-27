package com.alycloud.pay.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.channel.pingan.support.utils.MD5;
import com.alycloud.channel.pingan.support.utils.SHA1;
import com.alycloud.channel.yufu.YufuApiFactory;
import com.alycloud.channel.yufu.qrcode.YufuQrcodeApiFactory;
import com.alycloud.channel.yufu.qrcode.YufuQrcodeApiType;
import com.alycloud.channel.yufu.qrcode.bean.NotifyCallbackResult;
import com.alycloud.channel.yufu.qrcode.enums.RespCode;
import com.alycloud.channel.yufu.qrcode.support.client.NotifyCallback;
import com.alycloud.channel.yufu.support.Config;
import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.modules.entity.Channel;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.QrcodeChannelMerchFee;
import com.alycloud.modules.entity.QrcodeMerch;
import com.alycloud.modules.entity.QrcodeMerchFee;
import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.enums.QrcodeChannelMerchFeeStatus;
import com.alycloud.modules.enums.QrcodeMerchPartnerType;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.QrcodeScanType;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.search.Channel4Search;
import com.alycloud.modules.search.ChannelSubmerchInfo4Search;
import com.alycloud.modules.search.FastOrder4Search;
import com.alycloud.modules.search.QrcodeChannelMerchFee4Search;
import com.alycloud.modules.search.QrcodeMerch4Search;
import com.alycloud.modules.search.QrcodeMerch4Search4Pay;
import com.alycloud.modules.search.QrcodeMerchFee4Search;
import com.alycloud.modules.search.QrcodeOrder4Search;
import com.alycloud.modules.vo.PayTypeResultVO;
import com.alycloud.pay.api.IChannelService;
import com.alycloud.pay.api.IFastOrderService;
import com.alycloud.pay.api.IQrcodeChannelMerchFeeService;
import com.alycloud.pay.api.IQrcodeMerchFeeService;
import com.alycloud.pay.api.IQrcodeMerchService;
import com.alycloud.pay.api.IQrcodeOrderService;
import com.alycloud.pay.feign.ChannelSubmerchInfoFeign;
import com.alycloud.pay.feign.MerchInfoFeign;
import com.alycloud.pay.mapper.ChannelMapper;
import com.alycloud.pay.mapper.ChannelSubmerchInfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 二维码交易订单
 * @author Moyq5
 * @date 2017年10月17日
 */
@Slf4j
@Service
public class ChannelService implements IChannelService {

	@Autowired
	private ChannelMapper channelMapper;
	
	@Autowired
    private ChannelSubmerchInfoMapper channelSubmerchInfoMapper;
	
	@Autowired
	private ChannelSubmerchInfoFeign channelSubmerchInfoFeign;
	@Autowired
	private IQrcodeMerchService qrcodeMerchService;
	@Autowired
	private IQrcodeOrderService qrcodeOrderService;
	@Autowired
	private IFastOrderService fastOrderService;
	@Autowired
	private IQrcodeChannelMerchFeeService qrcodeChannelMerchFeeService;
	@Autowired
	private IQrcodeMerchFeeService qrcodeMerchFeeService;
	
	@Autowired
	private MerchInfoFeign merchInfoFeign;
	
	@Override
	public List<PayTypeResultVO> getPayTypeResult(String merchno) throws Exception {
		return getPayTypeResult(merchno, null);
	}
	
	/**
	 * 重载方法
	 * @param merchno
	 * @param channelType
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PayTypeResultVO> listAllChannelByType(String merchno, QrcodePayType payType)
	    throws Exception
	{
	    //判断是否已经开通快捷通道
        List<ChannelSubmerchInfo> submerchList = channelSubmerchInfoMapper.searchByParams(merchno,"QUICKPAY");
        Map<String, ChannelSubmerchInfo> subChannelMap = subChannelConverToMap(submerchList);
        List<PayTypeResultVO> payTypeList = new ArrayList<PayTypeResultVO>();
        List<Channel> channels = channelMapper.searchFastChannel();
        for (Channel channel: channels) {
            ChannelSubmerchInfo submerch = subChannelMap.get(channel.getChannelCode());
            if(submerch == null)
            {
            	Map<String,String> channelParams = new HashMap<String,String>();
            	channelParams.put("merchno", merchno);
            	channelParams.put("channelType", new Integer(SysChannelType.getByCode(channel.getChannelCode()).ordinal()).toString());
                submerch = channelSubmerchInfoMapper.searchFastByGradeInfo(channelParams);
            }
            if(submerch == null)
            {
                throw new ApiException(ApiCode.C0002);
            }
            PayTypeResultVO payTypeResult = new PayTypeResultVO();
            payTypeResult.setAmountDay(channel.getAmountDay().toPlainString());
            payTypeResult.setAmountMax(channel.getAmountMax().toPlainString());
            payTypeResult.setAmountMin(channel.getAmountMin().toPlainString());
            //payTypeResult.setChannelName(submerch.getChannelName());
            switch (SysChannelType.getByCode(channel.getChannelCode()).ordinal()) {
			case 3:
				payTypeResult.setChannelName("银联快捷大额A");
				break;
			case 4:
				payTypeResult.setChannelName("银联快捷大额B");
				break;
			case 7:
				payTypeResult.setChannelName("银联快捷大额C(带积分)");
				break;
			default:
				break;
			}
            payTypeResult.setChannelType(SysChannelType.getByCode(channel.getChannelCode()).ordinal());
            payTypeResult.setRemark(channel.getRemark());
            payTypeResult.setT0Fee(submerch.getPayFeeD0().toPlainString());
            payTypeResult.setT0Rate(submerch.getD0payRate().toPlainString());
            payTypeResult.setT0Status(channel.getT0Status());
            
            //只放D0通道
//            payTypeResult.setT1Fee(submerch.getPayFeeT1().toPlainString());
//            payTypeResult.setT1Rate(submerch.getT1payRate().toPlainString());
            payTypeResult.setT1Status(channel.getT1Status());
            payTypeList.add(payTypeResult);
        }
        
        if (payTypeList.size() == 0) {
            throw new ApiException(ApiCode.C0009);
        }
        return payTypeList;
	}
	
	private Map<String, ChannelSubmerchInfo> subChannelConverToMap(List<ChannelSubmerchInfo> submerchList)
	{
	    Map<String, ChannelSubmerchInfo> map = new HashMap<String, ChannelSubmerchInfo>();
	    if(submerchList != null && submerchList.size() > 0)
	    {
	        for(ChannelSubmerchInfo info : submerchList)
	        {
	            map.put(info.getChannelCode(), info);
	        }
	    }
	    return map;
	}
	
	@Override
	public List<PayTypeResultVO> getPayTypeResult(String merchno, QrcodePayType payType) throws Exception {
		ChannelSubmerchInfo4Search merch4s = new ChannelSubmerchInfo4Search();
		merch4s.setMerchno(merchno);
		merch4s.setAduitStatus("1");
		if (null == payType) {// 快捷
			merch4s.setPayType("QUICKPAY");
			merch4s.setPayMethod("QUICKPAY");
		} else {// 扫码
			if (payType == QrcodePayType.UNIONPAY) {
				merch4s.setPayType("UNIONPAY");
				merch4s.setPayMethod("UNIONPAYQR");
			} else if (payType == QrcodePayType.ALIPAY) {
				merch4s.setPayType("ALIPAY");
				merch4s.setPayMethod("ALIPAYQR");
			} else if (payType == QrcodePayType.WEIXIN) {
				merch4s.setPayType("WEIXIN");
				merch4s.setPayMethod("WXPUBQR");
			} else if (payType == QrcodePayType.QQ) {
				merch4s.setPayType("QQWALLET");
				merch4s.setPayMethod("QQWALLETQR");
			} else if (payType == QrcodePayType.JD) {
				merch4s.setPayType("JD");
				merch4s.setPayMethod("JDQR");
			} else {
				throw new ApiException(ApiCode.C0008);
			}
		}
		
		RequestBean<ChannelSubmerchInfo4Search> feignData = new RequestBean<ChannelSubmerchInfo4Search>();
		feignData.setData(merch4s);
		List<ChannelSubmerchInfo> submerchList = channelSubmerchInfoFeign.listByPage(feignData).getData();
		if (submerchList.size() == 0) {
			throw new ApiException(ApiCode.C0009);
		}
		List<PayTypeResultVO> payTypeList = new ArrayList<PayTypeResultVO>();
		Channel4Search channel4s = new Channel4Search();
		List<Channel> channelList;
		Channel channel;
		String channelCode;
		for (ChannelSubmerchInfo submerch: submerchList) {
			channelCode = submerch.getChannelCode();
			channel4s.setChannelCode(channelCode);
			channelList = channelMapper.listByPage(channel4s);
			if (channelList.size() == 0) {
				log.warn("找不渠道信息: {}", channelCode);
				continue;
			}
			channel = channelList.get(0);
			PayTypeResultVO payTypeResult = new PayTypeResultVO();
			payTypeResult.setAmountDay(channel.getAmountDay().toPlainString());
			payTypeResult.setAmountMax(channel.getAmountMax().toPlainString());
			payTypeResult.setAmountMin(channel.getAmountMin().toPlainString());
			payTypeResult.setChannelName(submerch.getChannelName());
			payTypeResult.setChannelType(SysChannelType.getByCode(channelCode).ordinal());
			payTypeResult.setRemark(channel.getRemark());
			payTypeResult.setT0Fee(submerch.getPayFeeD0().toPlainString());
			payTypeResult.setT0Rate(submerch.getD0payRate().toPlainString());
			payTypeResult.setT0Status(channel.getT0Status());
			payTypeResult.setT1Fee(submerch.getPayFeeT1().toPlainString());
			payTypeResult.setT1Rate(submerch.getT1payRate().toPlainString());
			payTypeResult.setT1Status(channel.getT1Status());
			payTypeList.add(payTypeResult);
		}
		
		if (payTypeList.size() == 0) {
			throw new ApiException(ApiCode.C0009);
		}
		return payTypeList;
	}
	
	@Override
	public List<PayTypeResultVO> getPayTypeResult2(String merchno, QrcodePayType payType) throws Exception {
		RequestBean<String> merchData = new RequestBean<String>();
		merchData.setData(merchno);
		MerchInfo merch = merchInfoFeign.getByMerchno(merchData).getData();
		QrcodeMerch4Search4Pay qm4s = new QrcodeMerch4Search4Pay();
		qm4s.setAgentno(merch.getSuperAgent());
		qm4s.setAmount(new BigDecimal("9"));//  TODO 可选，但不加，sql会出错，暂时这么处理
		qm4s.setBranchno(merch.getBranchno());
		qm4s.setMerchno(merch.getMerchno());
		qm4s.setPayType(1<<payType.ordinal());
		if (payType == QrcodePayType.FAST) {
			qm4s.setScanType(1<<QrcodeScanType.FAST.ordinal());
		} else {
			qm4s.setScanType(1<<QrcodeScanType.PASSIVE.ordinal());
		}
		qm4s.setTime(DateFormat.TIME.format());
		
		List<QrcodeMerch> qrcodeMerchList = qrcodeMerchService.list4Pay(qm4s);
		if (qrcodeMerchList.size() == 0) {
			throw new ApiException(ApiCode.C0009);
		}
		
		List<PayTypeResultVO> payTypeList = new ArrayList<PayTypeResultVO>();
		Channel4Search channel4s = new Channel4Search();
		List<Channel> channelList;
		Channel channel;
		String channelCode;
		for (QrcodeMerch qrcodeMerch: qrcodeMerchList) {
			
			channelCode = qrcodeMerch.getChannelCode();
			channel4s.setChannelCode(channelCode);
			channelList = channelMapper.listByPage(channel4s);
			if (channelList.size() == 0) {
				log.warn("找不渠道信息: {}", channelCode);
				continue;
			}
			channel = channelList.get(0);
			PayTypeResultVO payTypeResult = new PayTypeResultVO();
			payTypeResult.setAmountDay(channel.getAmountDay().toPlainString());
			payTypeResult.setAmountMax(channel.getAmountMax().toPlainString());
			payTypeResult.setAmountMin(channel.getAmountMin().toPlainString());
			payTypeResult.setChannelName(channel.getChannelAlias());
			payTypeResult.setChannelType(SysChannelType.getByCode(channelCode).ordinal());
			payTypeResult.setRemark(channel.getRemark());
			payTypeResult.setT0Status(channel.getT0Status());
			payTypeResult.setT1Status(channel.getT1Status());
			payTypeResult.setStatus(channel.getStatus());
			
			if (qrcodeMerch.getPartnerType() == QrcodeMerchPartnerType.BIGGER.ordinal()) {
				QrcodeMerchFee4Search fee4s = new QrcodeMerchFee4Search();
				fee4s.setMerchno(merchno);
				fee4s.setPayType(payType.ordinal());
				//fee4s.setPayType(1<<payType.ordinal());
				if (payType == QrcodePayType.FAST) {
					fee4s.setScanType(1<<QrcodeScanType.FAST.ordinal());
				} else {
					fee4s.setScanType(1<<QrcodeScanType.PASSIVE.ordinal());
				}
				List<QrcodeMerchFee> fees = qrcodeMerchFeeService.list(fee4s);
				for (QrcodeMerchFee fee: fees) {
					if ((fee.getSettleType()&(1<<SysSettleType.T0.ordinal())) == (1<<SysSettleType.T0.ordinal())) {
						payTypeResult.setT0Fee(fee.getFee().toPlainString());
						payTypeResult.setT0Rate(fee.getRate().toPlainString());
					}
					if ((fee.getSettleType()&(1<<SysSettleType.T1.ordinal())) == (1<<SysSettleType.T1.ordinal())) {
						payTypeResult.setT1Fee(fee.getFee().toPlainString());
						payTypeResult.setT1Rate(fee.getRate().toPlainString());
					}
				}
			} else if (qrcodeMerch.getPartnerType() == QrcodeMerchPartnerType.REGISTERED.ordinal()) {
				QrcodeChannelMerchFee4Search fee4s = new QrcodeChannelMerchFee4Search();
				fee4s.setChannelMerchId(qrcodeMerch.getId());
				fee4s.setPayType(1<<payType.ordinal());
				if (payType == QrcodePayType.FAST) {
					fee4s.setScanType(1<<QrcodeScanType.FAST.ordinal());
				} else {
					fee4s.setScanType(1<<QrcodeScanType.PASSIVE.ordinal());
				}
				//fee4s.setSettleType(1<<SysSettleType.T0.ordinal());
				fee4s.setStatus(QrcodeChannelMerchFeeStatus.AUDITED.ordinal());
				List<QrcodeChannelMerchFee> fees = qrcodeChannelMerchFeeService.list(fee4s);
				for (QrcodeChannelMerchFee fee: fees) {
					if ((fee.getSettleType()&(1<<SysSettleType.T0.ordinal())) == (1<<SysSettleType.T0.ordinal())) {
						payTypeResult.setT0Fee(fee.getFee().toPlainString());
						payTypeResult.setT0Rate(fee.getRate().toPlainString());
					}
					if ((fee.getSettleType()&(1<<SysSettleType.T1.ordinal())) == (1<<SysSettleType.T1.ordinal())) {
						payTypeResult.setT1Fee(fee.getFee().toPlainString());
						payTypeResult.setT1Rate(fee.getRate().toPlainString());
					}
				}
			} else {
				log.warn("不支付商户模式：{}", qrcodeMerch.getPartnerType());
				continue;
			}
			
			payTypeList.add(payTypeResult);
		}
		
		if (payTypeList.size() == 0) {
			throw new ApiException(ApiCode.C0009);
		}
		return payTypeList;
	}

	@Override
	public String notifyByPingan(HttpServletRequest req) {
		String reqCustomOrderNo = req.getParameter("out_no");
		String reqStatus = req.getParameter("status");
		String reqSign = req.getParameter("sign");
		
		String orderno = reqCustomOrderNo;
		QrcodeOrder4Search order4s = new QrcodeOrder4Search();
		order4s.setOrderno(orderno);
		List<QrcodeOrder> orderList = qrcodeOrderService.listByPage(order4s);
		if (orderList.size() != 1) {
			log.warn("平安支付回调 -> 订单不存在");
			return "订单不存在";
		}
		QrcodeOrder order = orderList.get(0);
		QrcodeMerch4Search channelMerch4s = new QrcodeMerch4Search();
		channelMerch4s.setChannelCode(order.getChannelCode());
		channelMerch4s.setPartnerId(order.getChannelMerchno());// open_id
		List<QrcodeMerch> merchList = qrcodeMerchService.listByPage(channelMerch4s);
		if (merchList.size() != 1) {
			log.warn("平安支付回调 -> 找不到渠道商户");
			return "找不到渠道商户";
		}
		QrcodeMerch channelMerch = merchList.get(0);
		
		Map<String, String> sortMap = new TreeMap<String, String>();
		Map<String, String[]> paramMap = req.getParameterMap();
		Iterator<String> it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			sortMap.put(key, paramMap.get(key)[0]);
		}
		sortMap.remove("m");// 自定义参数不参与签名
		sortMap.remove("sign");// sign参数不参与签名
		sortMap.put("open_key", channelMerch.getMerchKey());
		StringBuffer sb = new StringBuffer();
		for (Entry<String, String> entry: sortMap.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		sb.delete(sb.length()-1, sb.length());
		
		log.debug("平安支付回调 -> 签名内容：{}", sb.toString());
		String sign = MD5.MD5Encode(SHA1.encrypt(sb.toString()));
		log.debug("平安支付回调 -> 签名值：{}", sign);
		if (!sign.equals(reqSign)) {
			log.warn("平安支付回调 -> 签名验证失败: {}!={}", reqSign, sign);
        	return "签名验证失败";
		}
		
		if (reqStatus.equals("1")) {
			qrcodeOrderService.paySuccess(orderno);
		} else {
			log.warn("平安支付回调 -> 订单未支付[{}]：{}", reqStatus);
		}
		return "notify_success";
	}

	@Override
	public String notifyByYufuQrcode(HttpServletRequest req) throws IOException {
		NotifyCallback client = (NotifyCallback)YufuQrcodeApiFactory.getClient(YufuQrcodeApiType.NOTIFY_CALLBACK);
		NotifyCallbackResult data = client.execute(req);
		
		log.debug("御付扫码回调 -> 请求参数：{}", data);
		
		InputStream is = req.getInputStream();
		byte[] b = new byte[1024];
		int i;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		while((i=is.read(b)) != -1) {
			os.write(b, 0, i);
		}
		is.close();
		os.close();
		log.debug("御付扫码回调 -> 请求报文：{}", os.toString("UTF-8"));
		
		if (data.getRespCode() != RespCode.SUCCESS) {
			log.warn("御付扫码回调 -> 通知失败[{}]：{}", data.getRespCode().getValue(), data.getRespDesc());
			return "{\"result\":\"FAIL\",\"desc\":\"通知失败\"}";
		}
		
		QrcodeOrder4Search order4s = new QrcodeOrder4Search();
		order4s.setOrderno(data.getOrderNo());
		List<QrcodeOrder> list = qrcodeOrderService.listByPage(order4s);
		if (list.size() == 0) {
			log.warn("御付扫码回调 -> 通知失败，单号不存在：{}", data.getOrderNo());
			return "{\"result\":\"FAIL\",\"desc\":\"通知失败\"}";
		}
		
		QrcodeOrder order4Mod = new QrcodeOrder();
		order4Mod.setId(list.get(0).getId());
		order4Mod.setChannelOrderno(data.getTransId());
		qrcodeOrderService.mod(order4Mod);
		
		qrcodeOrderService.paySuccess(data.getOrderNo());
		
		return "{\"result\":\"SUCCESS\",\"desc\":\"通知成功\"}";
	}

	@Override
	public String notifyByYufuFast(HttpServletRequest req) throws IOException {
		String reqSign = req.getParameter("sign");
		String reqMerOrderNo = req.getParameter("mer_order_no");
		String reqOrderNo = req.getParameter("order_no");
		String reqStatus = req.getParameter("status");
		String reqStatusMsg = req.getParameter("status_msg");
		
		InputStream is = req.getInputStream();
		byte[] b = new byte[1024];
		int i;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		while((i=is.read(b)) != -1) {
			os.write(b, 0, i);
		}
		is.close();
		os.close();
		log.debug("御付快捷回调 -> 请求报文：{}", os.toString("UTF-8"));

		Config cfg = YufuApiFactory.getConfig();
		
		Map<String, String> sortMap = new TreeMap<String, String>();
		Map<String, String[]> paramMap = req.getParameterMap();
		Iterator<String> it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			sortMap.put(key, paramMap.get(key)[0]);
		}
		sortMap.remove("sign");// sign参数不参与签名
		StringBuffer sb = new StringBuffer();
		for (Entry<String, String> entry: sortMap.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		
		log.debug("御付快捷回调 -> 参数：{}", sb.toString());
		
		sb.append("key=" + cfg.getKey());
		
		log.debug("签名内容：{}", sb.toString());
		String sign = MD5.MD5Encode(sb.toString()).toUpperCase();
		log.debug("签名值：{}", sign);
		if (!sign.equals(reqSign)) {
			log.warn("御付快捷回调 -> 签名验证失败：{}",sign);
        	//return "签名验证失败:" + sign;
		}
		
        FastOrder4Search order4s = new FastOrder4Search();
    	order4s.setOrderno(reqMerOrderNo);
    	List<FastOrder> list = fastOrderService.listByPage(order4s);
		if (list.size() != 1) {
			log.warn("御付快捷回调 -> 交易单号不存在[{}]", reqMerOrderNo);
			return "交易单号不存在：" + reqMerOrderNo;
		}
		
		/** 更新渠道单号*/
		FastOrder order4Mod = new FastOrder();
		order4Mod.setId(list.get(0).getId());
		order4Mod.setChannelOrderno(reqOrderNo);
		fastOrderService.mod(order4Mod);
		
		/** 进账、通知等业务处理*/
		if (reqStatus.equals("1")) {
			fastOrderService.paySuccess(reqMerOrderNo);
		} else {
			log.warn("御付快捷回调 -> 订单未支付[{}]：{}", reqStatus, reqStatusMsg);
		}
		return "{\"resp_code\":\"0000\",\"resp_msg\":\"执行成功\"}";
	}

}
