package com.alycloud.channel.payeco.bean;

/**
 * 易联支付响应参数
 * @author Moyq5
 * @date 2017年4月11日
 */
public abstract class AbstractResult<T extends AbstractResultBody> {

	public Head head;
	public T body;
	
	public static class Head {
		private String retCode;
		private String retMsg;
		public String getRetCode() {
			return retCode;
		}
		public void setRetCode(String retCode) {
			this.retCode = retCode;
		}
		public String getRetMsg() {
			return retMsg;
		}
		public void setRetMsg(String retMsg) {
			this.retMsg = retMsg;
		}
		@Override
		public String toString() {
			return "Head [retCode=" + retCode + ", retMsg=" + retMsg + "]";
		}
	}

	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "AbstractResult [head=" + head + ", body=" + body + "]";
	}
	
}
