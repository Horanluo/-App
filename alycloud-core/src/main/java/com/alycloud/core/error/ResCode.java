package com.alycloud.core.error;

public enum ResCode {

	API_ERROE_CODE_0001("0001","帐号不存在"),
	API_ERROE_CODE_0002("0002","登录密码不对"),
	API_ERROE_CODE_0003("0003","登录账号已锁"),
	API_ERROE_CODE_0004("0004","登录账号已冻结"),
	API_ERROE_CODE_0005("0005","密码不匹配"),
	API_ERROE_CODE_0006("0006","发送短信验证码失败"),
	API_ERROE_CODE_0007("0007","查询个人信息失败"),
	API_ERROE_CODE_0008("0008",""),
	API_ERROE_CODE_0009("0009","查询绑卡信息失败"),
	API_ERROE_CODE_0010("0010","注册二次密码不匹配"),
	API_ERROE_CODE_0011("0011","密码格式不对"),
	API_ERROE_CODE_0012("0012","短信验证码不对"),
	API_ERROE_CODE_0013("0013","手机号已注册"),
	API_ERROE_CODE_0014("0014","注册失败"),
	API_ERROE_CODE_0015("0015","绑卡失败"),
	API_ERROE_CODE_0016("0016","银行卡认证失败"),
	API_ERROE_CODE_0017("0017","修改商户费率失败"),
	API_ERROE_CODE_0018("0018","上报商户费率失败"),
	API_ERROE_CODE_0019("0019","上传图片不存在"),
	API_ERROE_CODE_0020("0020","身份认证失败"),
	API_ERROE_CODE_0021("0021","推荐人不存在"),
	API_ERROE_CODE_0022("0022","新注册用户，提交活体识别信息失败"),
	API_ERROE_CODE_0023("0023","发送短信异常"),
	API_ERROE_CODE_0024("0024","银行卡已认证,无需再上传图片"),
	API_ERROE_CODE_0025("0025","身份已认证"),
	API_ERROE_CODE_0026("0026","新注册用户，没进行身份认证"),
	API_ERROE_CODE_0027("0027","必填参数为空，请检查必填参数。。。");
	
	private String errorCode;
	private String errorMes;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMes() {
		return errorMes;
	}
	public void setErrorMes(String errorMes) {
		this.errorMes = errorMes;
	}
	private ResCode(String errorCode, String errorMes) {
		this.errorCode = errorCode;
		this.errorMes = errorMes;
	}
}
