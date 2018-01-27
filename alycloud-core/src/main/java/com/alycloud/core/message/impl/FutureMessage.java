package com.alycloud.core.message.impl;

import com.alycloud.core.message.Message;
import com.alycloud.core.message.abs.AbstractMessage;
import com.alycloud.core.utils.MD5;

/**
 * 未来无线短信接口
 * 
 * @author Moyq5
 * @date 2017年4月27日
 */
public class FutureMessage extends AbstractMessage {

	private static final String pwd = "PFUR2B8VJQ";
    
	private String smsSign;
	
	public FutureMessage(String smsSign) {
		super("http://43.243.130.33:8860/sendSms");// Config.getString("msg-url-esyto")
		this.smsSign = smsSign;
	}

	/**
	 * 获取通讯的编码
	 */
	@Override
	protected String encoding() {
		return "UTF-8";
	}

	/**
	 * 根据消息类型获取模板ID
	 */
	@Override
	protected String getTemplateId(int msgType) {
		return Integer.toString(msgType);
	}

	@Override
	protected String getContent(String mobile, int msgType, String param) throws Exception {
		
	        String[] ps = param.split(",", -1);
	        String content = "【"+ smsSign +"】";
	        if (msgType == Message.TYPE_VALID_CODE || msgType == Message.TYPE_VALID_CODE_REGISTER || msgType == Message.TYPE_VALID_CODE_TRANS) {
		        content += "您的短信验证码：" + ps[0] + "，有效时间5分钟，请忽泄露给他人，如非本人操作，请忽略";
			} else if (msgType == Message.TYPE_NOTIFY_PWD) {
			    content += "注册成功！您的账号是：" + ps[0] + "，密码是：" + ps[1] + "，请妥善保管";
			} else if (msgType == Message.TYPE_VALID_CODE_RESET_PWD) {
			    content += "您的短信验证码：" + ps[0] + "，有效时间5分钟，请忽泄露给他人，如非本人操作，请忽略";
			} else {
				logger.error("未知的模板类型[" + msgType + "]"); 
			}
	        
	        String sign = MD5.sign(content, pwd, "UTF-8");
	        String reqMsg = "{"
	        		+ "\"cust_code\":\"500152\","
	        		+ "\"sp_code\":\"\","
	        		+ "\"content\":\""+ content +"\","
	        		+ "\"destMobiles\":\""+ mobile +"\","
	        		+ "\"uid\":\"\","
	        		+ "\"need_report\":\"yes\","
	        		+ "\"sign\":\""+ sign +"\"}";
	    return reqMsg;
	}

	/**
	 * 解析响应的结果
	 */
	@Override
	protected boolean parseResult(String result) {
		if (result.indexOf("\"respCode\":\"0\"") != -1) {
			logger.info("短信发送成功");
			return true;
		}
		logger.warn("短信发送失败：\r\n" + result);
		return false;
	}

}
