package com.alycloud.account.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alycloud.account.api.IdentifyAuthenService;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.utils.IdentifyAuthenUtil;

/**
 * 身份认证服务接口
 * @author Administrator
 *
 */
@Service
public class IdentifyAuthenServiceImpl implements IdentifyAuthenService{

	@Value("${identify.pub.key}")
    private String pub_key;
	@Value("${identify.security.key}")
    private String security_key;
	@Value("${identify.IDCARD_VERIFY}")
    private String IDCARD_VERIFY;
	
	Logger logger = LoggerFactory.getLogger(IdentifyAuthenServiceImpl.class);
	
	@Override
	@ServiceLogAnnotation(moduleName="实名验证接口")
	public JSONObject idCardVerify(String verifyType, String idNo, String idName, String extension_info)
			throws IOException {
		JSONObject reqJson = new JSONObject();
        reqJson.put("header", IdentifyAuthenUtil.getRequestHeader("",pub_key,security_key));

        JSONObject body = new JSONObject();
        body.put("id_number", idNo);
        body.put("id_name", idName);
        body.put("verify_type", verifyType);
        reqJson.put("body", body);

        logger.info("实名验证接口-输入参数：" + JSON.toJSONString(reqJson, true));
        JSONObject resJson = IdentifyAuthenUtil.doHttpRequest(IDCARD_VERIFY, reqJson);
        logger.info("实名验证接口-输出结果：" + JSON.toJSONString(resJson, true));
		return resJson;
	}
}
