package com.alycloud.channel.payeco.support.client;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.common.RequestClient;
import com.alycloud.channel.payeco.Config;
import com.alycloud.channel.payeco.PayecoContext;
import com.alycloud.channel.payeco.bean.AbstractData;
import com.alycloud.channel.payeco.bean.AbstractResult;
import com.alycloud.channel.payeco.bean.AbstractResult.Head;
import com.alycloud.channel.payeco.utils.Base64;
import com.alycloud.channel.payeco.utils.XmlUtils;

/**
 * 易联支付接口抽象类
 * @author Moyq5
 * @date 2017年4月14日
 * @param <D> 请求参数类型
 * @param <R> 响应参数类型
 */
public abstract class AbstractClient<D extends AbstractData, R extends AbstractResult<?>> {

	private static Logger log = LoggerFactory.getLogger(AbstractClient.class);
	
	protected PayecoContext context;
	
	public R request(D data) {
		Config cfg = context.getConfig();
		RequestClient client = context.getRequestClient();
		data.setSign(sign(getSignString(data)));
		String resultStr = client.request(cfg.getServer(), getQueryString(data));
		R result = XmlUtils.toObject(resultStr, getResultClass());
		if (null == result.getBody()) {
			log.warn("接口返回状态:{}", result.getHead());
		} else if (!valid(result.getBody().getSign(), getSignString(result))) {
			Head head = result.getHead();
			log.warn("签名验证未通过:{}", head);
			head.setRetCode("9999");
			head.setRetMsg("签名失败");
		}
		return result;
	}
	
	protected abstract Class<R> getResultClass();
	protected abstract String getSignString(D data);
	protected abstract String getQueryString(D data);
	protected abstract String getSignString(R result);
	
	private String sign(String data) {
		String sign = null;
		try {
			Config cfg = context.getConfig();
			String key = cfg.getPrivateKey();
			byte[] plainText = data.getBytes(cfg.getDataEncode());
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(key));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			Signature signer = Signature.getInstance("MD5WithRSA");
			signer.initSign(keyf.generatePrivate(priPKCS8));
			signer.update(plainText);
			byte[] signature = signer.sign();
			sign = Base64.encodeBytes(signature);
			log.debug("生成签名：\nkey={}\ndata={}\nsign={}", new Object[]{key, data, sign});
		} catch (Exception e) {
			log.error("签名生成异常", e);
		}
		return sign;
	}
	
	private boolean valid(String sign, String data) {
		boolean isValided = false;
		try {
			sign = sign.replaceAll(" ", "+");
			Config cfg = context.getConfig();
			String key = cfg.getPublicKey();
			log.debug("签名验证：\nkey={}\ndata={}\nsign={}", new Object[]{key, data, sign});
			Signature signer = Signature.getInstance("MD5WithRSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(key));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			signer.initVerify(publicKey);
			byte[] plainText = data.getBytes(cfg.getDataEncode());
			signer.update(plainText);
			isValided = signer.verify(Base64.decode(sign));
		} catch (Exception e) {
			log.error("签名验证异常", e);
		}
		return isValided;
	}

	public PayecoContext getContext() {
		return context;
	}

	public void setContext(PayecoContext context) {
		this.context = context;
	}
	
}
