package com.alycloud.channel.esyto.support.client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.esyto.bean.NotifyCallbackResult;
import com.alycloud.channel.esyto.bean.Result;
import com.alycloud.channel.esyto.enums.RespCode;
import com.alycloud.channel.esyto.support.Client;
import com.alycloud.channel.esyto.support.Config;
import com.alycloud.channel.esyto.support.Factory;
import com.alycloud.channel.esyto.support.Tool;
import com.alycloud.channel.esyto.support.utils.JSON;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 支付异步通知处理
 * @author Moyq5
 * @date 2017年9月30日
 */
public class NotifyCallback implements Client<HttpServletRequest, NotifyCallbackResult> {

	private static final Logger log = LoggerFactory.getLogger(NotifyCallback.class);
	
	public static final String CHARSET = "UTF-8";
	
	private Config config;
	
	public NotifyCallback() {
		config = Factory.getConfig();
	}
	
	@Override
	public Result<NotifyCallbackResult> execute(HttpServletRequest req) {
		Result<NotifyCallbackResult> resResult = new Result<NotifyCallbackResult>();
		try {
			log.debug("支付回调 -> 请求地址：{}", req.getRequestURI());
			log.debug("支付回调 -> 请求参数：{}", req.getQueryString());
			
			InputStream is = req.getInputStream();
			byte[] b = new byte[1024];
			int i;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			while((i=is.read(b)) != -1) {
				os.write(b, 0, i);
			}
			is.close();
			os.close();
			String reqBody = os.toString(CHARSET);
			log.debug("支付回调 -> 请求报文：{}", reqBody);
			
			Result<String> comResult = JSON.toObject(reqBody, new TypeReference<Result<String>>(){});
			NotifyCallbackResult data = JSON.toObject(comResult.getData(), NotifyCallbackResult.class);
			resResult.setCode(comResult.getCode());
			resResult.setData(data);
			resResult.setMsg(comResult.getMsg());
			resResult.setSign(comResult.getSign());
			
			if (resResult.getCode() == RespCode.SUCCESS) {
				String sign = Tool.sign(reqBody, config.getKey());
				if (!sign.equalsIgnoreCase(resResult.getSign())) {
					throw new Exception("签名验证失败");
				} else {
					log.debug("签名验证通过");
				}
			}
			
		} catch (Exception e) {
			log.error("支付回调 -> 处理失败", e);
			RespCode code = RespCode.FAIL;
			code.setValue("C9999");
			code.setText("本地错误：" + e.getMessage());
			resResult.setCode(code);
			resResult.setMsg("本地错误：" + e.getMessage());
		}
		return resResult;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

}
