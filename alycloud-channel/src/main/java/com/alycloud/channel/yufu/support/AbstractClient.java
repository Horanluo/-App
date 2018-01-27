package com.alycloud.channel.yufu.support;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.yufu.bean.AbstractResult;
import com.alycloud.channel.yufu.support.utils.JSON;

/**
 * 接口API操作类抽象类
 * @author Moyq5
 * @date 2017年8月1日
 * @param <D>
 * @param <R>
 */
public abstract class AbstractClient<D, R extends AbstractResult> implements Client<D, R> {

	private static Logger log = LoggerFactory.getLogger(AbstractClient.class);
	private Context context;
	private Class<R> resultClass;
	private String pathType;
	protected AbstractClient(String pathType, Class<R> resultClass) {
		this.pathType = pathType;
		this.resultClass = resultClass;
	}

	@Override
	public R execute(D data) {
		R resResult = null;
		try {
			Config cfg = context.getConfig();
			HttpClient client = context.getHttpClient();
			String reqBody = getReqBody(data);
			String resBody = client.post(cfg.getServerPath() + pathType, reqBody);
			resResult = JSON.toObject(resBody, resultClass);
			/* 奶奶的坑爹，御付不做签名
			String sign = Tool.sign(resBody, cfg.getKey());
			if (!sign.equals(resResult.getSign())) {
				throw new Exception("签名失败");
			}
			*/
		} catch (Exception e) {
			log.error("接口调用失败", e);
			try {
				resResult = resultClass.newInstance();
				resResult.setRespCode("9999");
				resResult.setRespMsg(e.getMessage());
			} catch (Exception e1) {
				log.error("接口调用失败", e1);
			}
		}
		return resResult;
	}
	
	protected Context getContext() {
		return context;
	}

	protected void setContext(Context context) {
		this.context = context;
	}
	
	protected String getReqBody(D data) throws Exception {
		String jsonString = JSON.toString(data);
		Map<String, String> dataMap = Tool.getSortedFields(jsonString, null, false);
		String sign = Tool.sign(dataMap, false, context.getConfig().getKey());
		dataMap.put("sign", sign);
		return Tool.toQueryString(dataMap, null, false);
	}
	
}
