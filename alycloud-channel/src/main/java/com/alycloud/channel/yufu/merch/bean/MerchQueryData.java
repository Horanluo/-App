package com.alycloud.channel.yufu.merch.bean;

/**
 * 商户审核结果查询请求参数对象
 * @author Moyq5
 * @date 2017年7月31日
 */
public class MerchQueryData extends DataAbstract {

	/**
	 * 手机号，必填
	 */
	private String phone;
	
	public MerchQueryData() {
		super("MER_QUERY");
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "MerchQueryData [phone=" + phone + ", toString()=" + super.toString() + "]";
	}
	
}
