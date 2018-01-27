package com.alycloud.core.utils;

import java.util.HashMap;
import java.util.Map;
import com.alycloud.core.message.Message;
import com.alycloud.core.message.impl.FutureMessage;
import com.alycloud.core.modules.SingleResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsUtil {

	/**
	 * 短信操作间隔
	 */
	private static final int SMS_VALID_PERIOD = 60000*5;
	
	/**
	 * 发送短信验证码
	 * 
	 * @author Horanluo
	 * @date 2017年11月7日
	 */
	public static SingleResult<String> send(String mobile) throws Exception {
				
		Message msg = new FutureMessage("易商付");
        Integer smsCode = (int) (Math.random() * 9000) + 1000;
		
		log.info("发送手机短信>> 手机号[" + mobile + "],验证码[" + smsCode + "]");
		return msg.send(mobile, Message.TYPE_VALID_CODE_REGISTER, smsCode + "");
	}
	
	public static Map<String,Boolean> valid(String mobile, String smsCode,String verifyCode){
		Map<String,Boolean> result = new HashMap<String,Boolean>();
		verifyCode = new String(Base64Util.decode(verifyCode));
		String[] verifyArray = verifyCode.split("\\|");
		if (!smsCode.equals(verifyArray[0])) {
			result.put("smsCode",false);
			result.put("smsValidTime", true);
			return result;
		}
		long sendSmsTime = Long.parseLong(verifyArray[1]);
		long curTime=System.currentTimeMillis();
		log.info("短信发送时间:"+sendSmsTime+"短信校验时间:"+curTime);
		if ((sendSmsTime + SMS_VALID_PERIOD) < curTime) {// 5分钟内有效
			result.put("smsValidTime", false);
			result.put("smsCode", true);
			return result;
		}
		result.put("smsValidTime", true);
		result.put("smsCode", true);
		return result;
	}
}
