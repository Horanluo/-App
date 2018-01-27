package com.alycloud.account.api;

import java.io.IOException;
import com.alibaba.fastjson.JSONObject;

public interface IdentifyAuthenService {
	
	/**
	 * 实名验证接口
	 * @param verifyType 实名验证方式
	 * @param idNo 身份证号码
	 * @param idName 身份证姓名
	 * @param extension_info 扩展信息
	 * @return
	 * @throws IOException
	 */
	JSONObject idCardVerify(String verifyType,String idNo,String idName,String extension_info) throws IOException;
}
