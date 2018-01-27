package com.alycloud.pay.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.utils.CardUtil;
import com.alycloud.pay.api.IUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * 工具类服务接口
 * @author Horanluo
 * @date 2017年11月30日
 */
@Slf4j
@Service
public class UtilServiceImpl implements IUtilService {

	@Value("${card.authKey}")
    private String cardAuthKey;
	@Value("${card.authService}")
    private String cardAuthService;
	
	@Override
	public JSONObject certifyCardMsg(String id, String name, String card, String mobile) throws ApiException {
		try {
			name = URLEncoder.encode(name, "UTF-8");
			StringBuffer url = new StringBuffer();
			url.append(cardAuthService);// 汇联
			url.append("&id_no=" + id);
			url.append("&id_name=" + name);
			url.append("&card_no=" + card);
			url.append("&mobile_no=" + mobile);
			url.append("&auth_key=" + cardAuthKey);
			return sendMsg(url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException(ApiCode.C9999);
		}
	}
	
	private JSONObject sendMsg(String urlStr) throws Exception {
		log.debug("实名认证：{}", urlStr);

		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				con.getInputStream(), "UTF-8"));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		
		log.debug("认证结果：\r\n{}", sb.toString());
		JSONObject obj = JSONObject.parseObject(sb.toString());
		return obj;
	}

	@Override
	@ServiceLogAnnotation(moduleName="获取卡信息")
	public String getCardDetail(String cardNo) {
		return CardUtil.getCardDetail(cardNo);
	}

}
