/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.http;

import java.util.Map;

import io.bestpay.framework.base.SpecificRecordBase;
import io.bestpay.framework.base.SpecificRecordUtils;
import io.bestpay.sdk.base.RequestParams;

public abstract class AbstractSpecificRecordDataSignable extends AbstractDataSignable implements DataSignable<SpecificRecordBase> {

	@Override 
	public String sign(RequestParams requestParams, SpecificRecordBase data) {
		Map<String, String> map = SpecificRecordUtils.toMap(data);
		map.putAll(SpecificRecordUtils.toMap(requestParams));
		return this.signMap(map);
	}

	@Override
	public boolean verify(RequestParams requestParams, SpecificRecordBase data, String sign) {
		Map<String, String> map = SpecificRecordUtils.toMap(data);
		map.putAll(SpecificRecordUtils.toMap(requestParams));
		return sign.equals(this.signMap(map));
	}

}
