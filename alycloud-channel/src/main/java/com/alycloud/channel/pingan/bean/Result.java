package com.alycloud.channel.pingan.bean;

/**
 * 支付接口－响应对象（原始响应内容最终转换成的对象）
 * @author Moyq5
 * @date 2017年6月15日
 * @param <R>
 */
public class Result<R> {

	private CommonResult common;
	private R detail;
	public CommonResult getCommon() {
		return common;
	}
	public void setCommon(CommonResult common) {
		this.common = common;
	}
	public R getDetail() {
		return detail;
	}
	public void setDetail(R detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "Result [common=" + common + ", detail=" + detail + "]";
	}
}
