package com.alycloud.channel.pingan.support;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.pingan.bean.CommonData;
import com.alycloud.channel.pingan.bean.CommonData4Refund;
import com.alycloud.channel.pingan.bean.CommonResult;
import com.alycloud.channel.pingan.bean.PayRefundData;
import com.alycloud.channel.pingan.bean.Result;
import com.alycloud.channel.pingan.bean.SignData;
import com.alycloud.channel.pingan.bean.SignData4Refund;
import com.alycloud.channel.pingan.bean.SignResult;
import com.alycloud.channel.pingan.support.utils.AES;
import com.alycloud.channel.pingan.support.utils.JSON;
import com.alycloud.channel.pingan.support.utils.MD5;
import com.alycloud.channel.pingan.support.utils.RSA;
import com.alycloud.channel.pingan.support.utils.SHA1;

/**
 * 接口API操作类抽象类
 * 
 * @author Moyq5
 * @date 2017年6月15日
 * @param <D>
 * @param <R>
 */
public abstract class AbstractClient<D, R> implements Client<D, R> {

	private static Logger log = LoggerFactory.getLogger(AbstractClient.class);
	private Merch merch;
	private Config config;
	public AbstractClient() {
		config = Factory.getConfig();
	}
	@Override
	public Result<R> post(D data) {
		Result<R> result = new Result<R>();
		try {
			HttpClient client = Factory.getHttpClient();
			String jsonString = JSON.toString(data);
			log.debug("请求参数data明文：{}", jsonString);
			String dataEncrypt = AES.encrypt(jsonString, merch.getOpenKey());
			log.debug("请求参数data密文：{}", dataEncrypt);

			String reqBody = null;
			if (data instanceof PayRefundData) {
				CommonData4Refund reqData = buildCommonData4Refund(dataEncrypt);
				reqBody = Tool.toQueryString(reqData);
			} else {
				CommonData reqData = buildCommonData(dataEncrypt);
				reqBody = Tool.toQueryString(reqData);
			}

			String resBody = client.post(getServerPath(), reqBody);
			CommonResult resResult = JSON.toObject(resBody, CommonResult.class);

			dataEncrypt = resResult.getData();
			R detail = null;
			if (null != dataEncrypt) {
				if (!checkSign(resResult)) {
					throw new Exception("签名失败");
				}
				log.debug("响应参数data密文：{}", dataEncrypt);
				jsonString = AES.decrypt(dataEncrypt, merch.getOpenKey());
				log.debug("响应参数data明文：{}", jsonString);
				detail = JSON.toObject(jsonString, getDetailClass());
			}

			result.setCommon(resResult);
			result.setDetail(detail);
		} catch (Exception e) {
			log.error("接口调用失败", e);
			CommonResult comResult = new CommonResult();
			comResult.setCode(9999);
			comResult.setMsg(e.getMessage());
			result.setCommon(comResult);
		}
		return result;
	}

	/**
	 * 签名验证
	 */
	private boolean checkSign(CommonResult resResult) {

		SignResult signData = new SignResult();
		signData.setCode(resResult.getCode());
		signData.setData(resResult.getData());
		signData.setMsg(resResult.getMsg());
		signData.setOpenKey(merch.getOpenKey());
		signData.setTimestamp(resResult.getTimestamp());

		List<String> ignoreFields = new ArrayList<String>();
		ignoreFields.add("sign");

		String signString = Tool.toQueryString(signData, ignoreFields);
		log.debug("签名验证内容：{}", signString);
		String sign = MD5.MD5Encode(SHA1.encrypt(signString).toLowerCase()).toLowerCase();
		log.debug("签名值：{}", sign);

		boolean isValid = sign.equals(resResult.getSign());
		log.debug("签名验证结果：{}", isValid);
		if (!isValid) {
			log.error("签名验证失败，目标值：{}", resResult.getSign());
		}
		return isValid;
	}

	/**
	 * 创建最终请求参数对象
	 */
	private CommonData buildCommonData(String data) {

		SignData signData = new SignData();
		signData.setData(data);
		signData.setOpenId(merch.getOpenId());
		signData.setOpenKey(merch.getOpenKey());
		//signData.setSign(null);
		signData.setTimestamp((int) (new Date().getTime() / 1000));

		List<String> ignoreFields = new ArrayList<String>();
		ignoreFields.add("sign");

		String signString = Tool.toQueryString(signData, ignoreFields);
		log.debug("签名内容：{}", signString);
		String sign = MD5.MD5Encode(SHA1.encrypt(signString).toLowerCase()).toLowerCase();
		log.debug("签名值：{}", sign);

		CommonData com = new CommonData();
		com.setData(signData.getData());
		com.setOpenId(signData.getOpenId());
		com.setSign(sign);
		com.setTimestamp(signData.getTimestamp());
		return com;
	}

	/**
	 * 退款接口参数签名
	 * 
	 * @author Moyq5
	 * @date 2017年7月10日
	 */
	private CommonData4Refund buildCommonData4Refund(String data) throws UnsupportedEncodingException, Exception {

		SignData4Refund signData = new SignData4Refund();
		signData.setData(data);
		signData.setOpenId(merch.getOpenId());
		signData.setOpenKey(merch.getOpenKey());
		//signData.setSign(null);
		signData.setSignType("RSA");
		signData.setTimestamp((int) (new Date().getTime() / 1000));

		List<String> ignoreFields = new ArrayList<String>();
		ignoreFields.add("sign");

		String publicKey = merch.getPublicKey();
		if (null == publicKey || publicKey.isEmpty()) {
			publicKey = config.getPublicKey();
		}
		String signString = Tool.toQueryString(signData, ignoreFields);
		log.debug("签名内容：{}", signString);
		String sign = RSA.sign(signString.getBytes("UTF-8"), publicKey);
		log.debug("签名值：{}", sign);
		
		CommonData4Refund com = new CommonData4Refund();
		com.setData(signData.getData());
		com.setOpenId(signData.getOpenId());
		com.setSign(sign);
		com.setSignType(signData.getSignType());
		com.setTimestamp(signData.getTimestamp());
		return com;
	}

	@Override
	public void setMerch(Merch merch) {
		this.merch = merch;
	}
	
	protected abstract Class<R> getDetailClass();
	
	protected String getServerPath() {
		return config.getServerPath();
	}

	public Config getConfig() {
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
	}
}
