package com.alycloud.channel.linkpay.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.linkpay.LinkpayUtils;
import com.alycloud.channel.linkpay.bean.CommonData;
import com.alycloud.channel.linkpay.bean.CommonResult;
import com.alycloud.channel.linkpay.bean.Result;
import com.alycloud.channel.linkpay.enums.Code;
import com.alycloud.channel.linkpay.enums.Service;

public abstract class AbstractClient<D, R> implements Client<D, R> {

	private static Logger log = LoggerFactory.getLogger(AbstractClient.class);
	
	private Context context;
	private Class<R> detailClass;
	private CommonData com;
	protected AbstractClient(Service service, Class<R> detailClass) {
		this.com = LinkpayUtils.buildCommonData(service);
		this.detailClass = detailClass;
	}
	
	@Override
	public Result<R> post(D data) {
		Result<R> result = new Result<R>();
		try {
			HttpClient client = context.getHttpClient();
			String reqBody = LinkpayUtils.toQueryString(com, data);
			String resBody = client.post(getServerPath(), reqBody);
			result = LinkpayUtils.toResultObject(resBody, detailClass);
		} catch (Exception e) {
			log.error("接口调用失败", e);
			CommonResult comResult = new CommonResult();
			comResult.setCode(Code.C9999);
			comResult.setMessage(e.getMessage());
			result.setCommonResult(comResult);
		}
		return result;
	}
	
	protected Context getContext() {
		return context;
	}

	protected void setContext(Context context) {
		this.context = context;
	}
	
	protected String getServerPath() {
		return context.getConfig().getServerPath();
	}
}
