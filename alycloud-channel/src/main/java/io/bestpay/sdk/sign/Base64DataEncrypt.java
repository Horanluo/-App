/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.base.SpecificRecordBase;

/**
 * 
 * <pre>
 * 	base64加密类
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年11月7日 
 *
 */
public class Base64DataEncrypt extends AbstractDataEncryptable {

	private final Class<?> recordClass;
	
	public Base64DataEncrypt(Class<?> recordClass) {
		super();
		this.recordClass = recordClass;
	}

	@Override
	public byte[] encrypt(SpecificRecordBase data) throws CodeException {
		return Base64.encodeBase64(this.serialize(data));
	}

	@Override
	public SpecificRecordBase decrypt(String data) throws CodeException {
		try {
			byte[] arr = Base64.decodeBase64(data.getBytes(DEFAULT_CHARSET));
			return (SpecificRecordBase) this.deserialize(this.recordClass, new String(arr, DEFAULT_CHARSET));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public Class<?> getRecordClass() {
		return recordClass;
	}

	@Override
	public String sign(byte[] data) throws Exception {
		return DigestUtils.md5Hex(data);
	}


}
