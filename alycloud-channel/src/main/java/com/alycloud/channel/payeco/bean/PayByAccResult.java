package com.alycloud.channel.payeco.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 易联支付－无磁无密交易接口－响应参数
 * @author Moyq5
 * @date 2017年4月11日
 */
@JacksonXmlRootElement(localName = "response")
public class PayByAccResult extends AbstractResult<PayByAccResultBody> {

	@Override
	public String toString() {
		return "PayByAccResult [toString()=" + super.toString() + "]";
	}
	
}
