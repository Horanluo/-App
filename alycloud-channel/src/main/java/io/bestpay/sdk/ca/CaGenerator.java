/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.ca;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import io.bestpay.sdk.sign.SignUtils;

public class CaGenerator {
	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	private static final int RSA_KEY_SIZE = 1024;
	public static final String KEYSTORE_PKCS12 = "PKCS12";
	public static final String KEYSTORE_JCEKS = "JCEKS";

	// 验证周期 默认1年
	private static final int VALIDITY_PERIOD = 365 * 24 * 60 * 60 * 1000;

	// 默认提供者
	public static final String DEFAULT_PROVIDER = "BC";

	public static final String ROOT_ALIAS = "root";

	/**
	 * 通过base64生成公钥
	 * @param base64Data
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String base64Data) throws Exception {
		return getPublicKey(base64Data.getBytes(SignUtils.DEFAULT_CHARSET));
	}
	
	public static PublicKey getPublicKey(byte[] base64Data) throws Exception {
		byte[] data = Base64.decodeBase64(base64Data);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(data);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 生成键值对
	 * 
	 * @return
	 * @throws NoSuchProviderException
	 */
	public static KeyPairGenerator generateRSAKeyPair() throws NoSuchProviderException {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", DEFAULT_PROVIDER);
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(System.nanoTime());
			kpg.initialize(RSA_KEY_SIZE, sr);
			return kpg;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取keystore
	 * 
	 * @param type
	 * @param in
	 * @param password
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws NoSuchProviderException
	 */
	public static KeyStore getKeyStore(String type, InputStream in, char[] password) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, NoSuchProviderException {
		KeyStore store = KeyStore.getInstance(type, DEFAULT_PROVIDER);
		store.load(in, password);
		return store;
	}

	/**
	 * 获取证书
	 * 
	 * @param store
	 * @return
	 * @throws KeyStoreException
	 */
	public static Certificate getCertificate(KeyStore store) throws KeyStoreException {
		return store.getCertificate(ROOT_ALIAS);
	}
	
	/**
	 * 获取证书
	 * @param data
	 * @return
	 * @throws KeyStoreException
	 */
	public static Certificate getCertificate(byte[] data) throws KeyStoreException {
		try {
			CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
			java.security.cert.Certificate certificate = certificatefactory.generateCertificate(new ByteArrayInputStream(data));
			return certificate;
		} catch (CertificateException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取私钥
	 * 
	 * @param store
	 * @param password
	 * @return
	 * @throws UnrecoverableKeyException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 */
	public static java.security.Key getPrivateKey(KeyStore store, String password)
			throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		return store.getKey(ROOT_ALIAS, password.toCharArray());
	}

	/**
	 * 生成证书
	 * 
	 * @param config
	 * @param storeOutputStream
	 * @param certOutputStream
	 * @param base64
	 * @throws Exception
	 */
	public void generateCert(CaConfig config, OutputStream storeOutputStream, OutputStream certOutputStream,
			boolean base64) throws Exception {
		KeyStore ks = this.getKeyStore(KEYSTORE_PKCS12, null, null);
		KeyPair keyPair = generateRSAKeyPair().genKeyPair();

		X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
		certGen.setSerialNumber(genCertSerial());
		certGen.setIssuerDN(new X500Principal(config.getIssuerDn()));
		certGen.setNotBefore(new Date());
		certGen.setNotAfter(new Date(System.currentTimeMillis() + VALIDITY_PERIOD));
		certGen.setSubjectDN(new X500Principal(config.getSubjectDn()));
		certGen.setPublicKey(keyPair.getPublic());
		certGen.setSignatureAlgorithm(config.getSignatureAlgorithm());

		X509Certificate cert = certGen.generateX509Certificate(keyPair.getPrivate());
		ks.setKeyEntry(ROOT_ALIAS, keyPair.getPrivate(), config.getKeyPassword().toCharArray(),
				new Certificate[] { cert });

		ByteArrayOutputStream arrout = new ByteArrayOutputStream();
		ks.store(arrout, config.getStorePassword().toCharArray());

		if (base64) {
			base64Write(cert.getEncoded(), certOutputStream);
			base64Write(arrout.toByteArray(), storeOutputStream);
		} else {
			certOutputStream.write(cert.getEncoded());
			storeOutputStream.write(arrout.toByteArray());
			IOUtils.closeQuietly(certOutputStream);
			IOUtils.closeQuietly(storeOutputStream);
		}

	}

	private static void base64Write(byte[] binaryData, OutputStream out) {
		try {
			out.write(Base64.encodeBase64(binaryData));
		} catch (IOException e) {
			IOUtils.closeQuietly(out);
		}
	}

	public static BigInteger genCertSerial() {
		String time = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		String nanoTime = System.nanoTime() + "";
		return new BigInteger(time + nanoTime.substring(nanoTime.length() - 8));
	}

}
