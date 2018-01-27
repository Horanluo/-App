/*
 *  @Copyright 2017 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import java.security.PrivateKey;
import java.security.cert.Certificate;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.base.SpecificRecordBase;

public class RsaSignBase64DataEncrypt extends RsaDataEncrypt {

	private Base64DataEncrypt base64DataEncrypt;
	public RsaSignBase64DataEncrypt(Class<?> recordClass, Certificate cert) {
		super(recordClass, cert);
		this.base64DataEncrypt = new Base64DataEncrypt(recordClass);
	}

	public RsaSignBase64DataEncrypt(Class<?> recordClass, PrivateKey privateKey, byte[] publicKey) {
		super(recordClass, privateKey, publicKey);
		this.base64DataEncrypt = new Base64DataEncrypt(recordClass);
	}

	@Override
	public byte[] encrypt(SpecificRecordBase specificRecordBase) throws CodeException {
		return this.base64DataEncrypt.encrypt(specificRecordBase);
	}

	@Override
	public SpecificRecordBase decrypt(String encryptData) throws CodeException {
		return this.base64DataEncrypt.decrypt(encryptData);
	}
	



}
