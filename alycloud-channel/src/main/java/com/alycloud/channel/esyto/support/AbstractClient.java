package com.alycloud.channel.esyto.support;

import java.net.SocketTimeoutException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.esyto.bean.Data;
import com.alycloud.channel.esyto.bean.Result;
import com.alycloud.channel.esyto.enums.RespCode;
import com.alycloud.channel.esyto.support.utils.JSON;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 接口API操作类抽象类
 * @author Moyq5
 * @date 2017年9月29日
 * @param <D>
 * @param <R>
 */
public abstract class AbstractClient<D, R> implements Client<D, R> {

	private static Logger log = LoggerFactory.getLogger(AbstractClient.class);
	
	private Config config;
	public AbstractClient() {
		config = Factory.getConfig();
	}
	
	@Override
	public Result<R> execute(D data) {
		Result<R> resResult = null;
		try {
			HttpClient client = Factory.getHttpClient();
			String reqBody = getReqBody(data);
			String resBody = client.post(getServerPath(), reqBody);
			resResult = JSON.toObject(resBody, new TypeReference<Result<R>>(){});
			if (resResult.getCode() == RespCode.SUCCESS) {
				String sign = Tool.sign(resBody, config.getKey());
				if (!sign.equalsIgnoreCase(resResult.getSign())) {
					throw new Exception("签名验证失败");
				} else {
					log.debug("签名验证通过");
				}
				R resultData = JSON.toObject((String)resResult.getData(), getResultClass());
				resResult.setData(resultData);
			}
			
			
		} catch (SocketTimeoutException e) {
			resResult = new Result<R>();
			RespCode code = RespCode.FAIL;
			code.setValue("C9999");
			code.setText("请求超时");
			resResult.setCode(code);
			resResult.setMsg("请求超时");
		} catch (Exception e) {
			log.error("接口调用失败", e);
			resResult = new Result<R>();
			RespCode code = RespCode.FAIL;
			code.setValue("C9999");
			code.setText("本地错误：" + e.getMessage());
			resResult.setCode(code);
			resResult.setMsg("本地错误：" + e.getMessage());
		}
		return resResult;
	}
	
	protected abstract Class<R> getResultClass();
	
	protected String getServerPath() {
		return config.getServerPath();
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	private String getReqBody(D data) throws Exception {
		String jsonString = JSON.toString(data);
		Data comData = new Data();
		comData.setData(jsonString);
		comData.setMerchNo(config.getMerchNo());
		comData.setNonceStr(mkRandomStr(32));
		comData.setTimestamp(new Long(new Date().getTime()/1000).intValue());
		jsonString = JSON.toString(comData);
		String sign = Tool.sign(jsonString, config.getKey());
		comData.setSign(sign);
		jsonString = JSON.toString(comData);
		return jsonString;
	}
	
	private static String mkRandomStr(int length) {
		final char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuffer sb = new StringBuffer();
	    for(int i = 0; i<length; i++){
	    	sb.append(chars[new Long(Math.round(Math.random() * (chars.length-1))).intValue()]);
	    }
		return sb.toString();
	}
	
}
