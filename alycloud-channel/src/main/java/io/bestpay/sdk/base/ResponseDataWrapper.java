/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.base;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.base.SpecificRecordBase;
import io.bestpay.framework.base.SpecificRecordUtils;
import io.bestpay.framework.exception.SignInValidException;
import io.bestpay.sdk.sign.DataEncryptable;
import io.bestpay.sdk.sign.RsaDataEncrypt;
import io.bestpay.sdk.sign.SignUtils;

public class ResponseDataWrapper extends ResponseData {

	private SpecificRecordBase responseData;

	public SpecificRecordBase getResponseData() {
		return responseData;
	}

	public void setResponseData(SpecificRecordBase responseData) {
		this.responseData = responseData;
	}
	
	/**
	 * 将返回结果解析为对像
	 * @param response
	 * @param dataEncrypt
	 * @return
	 * @throws CodeException
	 */
	public static ResponseDataWrapper parse(String response, RsaDataEncrypt dataEncrypt) throws CodeException {
		ObjectMapper om = new ObjectMapper();
		
		Map<String, Object> map;
		try {
			map = om.readValue(response, Map.class);
		} catch (Exception e) {
			throw new RuntimeException("解析json格式出错", e);
		}
		
		ResponseDataWrapper rd = new ResponseDataWrapper();
		SpecificRecordUtils.parse(rd, map);
		ResponseData data = ResponseData.newBuilder(rd).setSign("").build();
		byte[] signData = SignUtils.getSortString(data);
		boolean signResult = false;
		try {
			signResult = dataEncrypt.verify(signData, rd.getSign());
		} catch (CodeException e) {
			throw e;
		} catch (Exception e) {
			throw new SignInValidException("签名验证错误");
		}
		if (!signResult) {
			throw new SignInValidException();
		}
		if (null != rd.getData() && !"".equals(rd.getData())) {
			SpecificRecordBase result = dataEncrypt.decrypt(rd.getData());
			rd.setResponseData(result);
		}
		
		return rd;
		
	}
	
	/**
	 * 加密
	 * @param builder
	 * @param data
	 * @param dataEncrypt
	 * @return
	 * @throws CodeException 
	 */
	public static ResponseData createResponse(ResponseData.Builder builder, SpecificRecordBase data, DataEncryptable<SpecificRecordBase> dataEncrypt) {
		try {
			if (null != data && !"".equals(data)) {
				byte[] encData = dataEncrypt.encrypt(data);
				builder.setData(new String(encData, SignUtils.DEFAULT_CHARSET));
			}
			ResponseData respData = builder.setSign("").build();
			byte[] signData = SignUtils.getSortString(respData);
			String str = dataEncrypt.sign(signData);
			
			return ResponseData.newBuilder(respData).setSign(str).build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
