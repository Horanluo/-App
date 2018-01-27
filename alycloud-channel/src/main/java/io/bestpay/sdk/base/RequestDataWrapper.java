/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.base;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.base.SpecificRecordBase;
import io.bestpay.framework.base.SpecificRecordUtils;
import io.bestpay.framework.exception.SignInValidException;
import io.bestpay.sdk.sign.RsaDataEncrypt;
import io.bestpay.sdk.sign.SignUtils;

public class RequestDataWrapper extends RequestData {
	
	public SpecificRecordBase getRequestData() {
		return requestData;
	}

	public void setRequestData(SpecificRecordBase requestData) {
		this.requestData = requestData;
	}

	private SpecificRecordBase requestData;
	
	/**
	 * 解密成请求对像
	 * @param requestData
	 * @param dataEncrypt
	 * @return
	 */
	public static RequestDataWrapper parse(RequestData requestData, RsaDataEncrypt dataEncrypt) throws CodeException {
		RequestData tmp = RequestData.newBuilder(requestData).setSign("").build();
		byte[] signData = SignUtils.getSortString(tmp);
		try {
			dataEncrypt.verify(signData, requestData.getSign());
		} catch (CodeException e) {
			throw e;
		} catch (Exception e) {
			throw new SignInValidException("签名验证错误");
		}
		
		SpecificRecordBase result = dataEncrypt.decrypt(requestData.getData());
		RequestDataWrapper rdw = new RequestDataWrapper();
		SpecificRecordUtils.copy(requestData, rdw);
		rdw.setRequestData(result);
		return rdw;
	}
}
