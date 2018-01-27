/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.http;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * <pre>
 * 	HMAC 校验
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年9月29日 
 *
 */
public class HMACSpecificRecordDataSignable extends AbstractSpecificRecordDataSignable {
	
	private String secret;
	public HMACSpecificRecordDataSignable(String secret) {
		super();
		this.secret = secret;
	}
	@Override
	protected String signString(String str) {
		try {
			return DigestUtils.md5Hex((str + secret).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持UTF-8编码");
		}
	}

}
