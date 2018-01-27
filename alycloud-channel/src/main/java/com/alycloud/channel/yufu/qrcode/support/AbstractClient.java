package com.alycloud.channel.yufu.qrcode.support;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.yufu.qrcode.bean.AbstractResult;
import com.alycloud.channel.yufu.qrcode.enums.RespCode;
import com.alycloud.channel.yufu.qrcode.support.utils.JSON;

/**
 * 接口API操作类抽象类
 * @author Moyq5
 * @date 2017年8月28日
 * @param <D>
 * @param <R>
 */
public abstract class AbstractClient<D, R extends AbstractResult> implements Client<D, R> {

	private static Logger log = LoggerFactory.getLogger(AbstractClient.class);
	
	@Override
	public R execute(D data) {
		R resResult = null;
		try {
			Config cfg = Factory.getConfig();
			HttpClient client = Factory.getHttpClient();
			String reqBody = getReqBody(data);
			String resBody = client.post(getServerPath(), reqBody);
			resResult = JSON.toObject(resBody, getResultClass());
			if (resResult.getRespCode() == RespCode.SUCCESS) {
				String sign = Tool.sign(resBody, cfg.getKey());
				if (!sign.equals(resResult.getSign())) {
					throw new Exception("签名验证失败");
				} else {
					log.debug("签名验证通过");
				}
			}
		} catch (Exception e) {
			log.error("接口调用失败", e);
			try {
				resResult = getResultClass().newInstance();
				RespCode code = RespCode.FAIL;
				code.setValue("9999");
				code.setText(e.getMessage());
				resResult.setRespCode(code);
				resResult.setRespDesc(e.getMessage());
			} catch (Exception e1) {
				log.error("接口调用失败", e1);
			}
		}
		return resResult;
	}
	
	protected String getReqBody(D data) throws Exception {
		String jsonString = JSON.toString(data);
		Map<String, String> dataMap = Tool.getSortedFields(jsonString, false);
		String sign = Tool.sign(jsonString, Factory.getConfig().getKey());
		dataMap.put("signIn", sign);
		return Tool.toQueryString(dataMap, null, true);
	}
	
	protected abstract String getServerPath();
	
	protected abstract Class<R> getResultClass();
}
