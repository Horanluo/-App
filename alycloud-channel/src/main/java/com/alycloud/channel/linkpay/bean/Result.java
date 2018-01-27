package com.alycloud.channel.linkpay.bean;

/**
 * 支付接口－支付地址（二维码，或者网关地址）信息
 * @author Moyq5
 * @date 2017年5月9日
 */
public class Result<R> {

	private CommonResult commonResult;
	private R detailResult;
	public CommonResult getCommonResult() {
		return commonResult;
	}
	public void setCommonResult(CommonResult commonResult) {
		this.commonResult = commonResult;
	}
	public R getDetailResult() {
		return detailResult;
	}
	public void setDetailResult(R detailResult) {
		this.detailResult = detailResult;
	}
	@Override
	public String toString() {
		return "Result [commonResult=" + commonResult + ", detailResult=" + detailResult + "]";
	}
	
}
