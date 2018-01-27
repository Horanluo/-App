package com.alycloud.pay.api;

import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.exception.ApiException;

/**
 * 工具类  服务接口
 * @author Horanluo
 * @date 2017年11月30日
 */
public interface IUtilService {

	public JSONObject certifyCardMsg(String id, String name, String card, String mobile) throws ApiException;
	
	/**
	 * 获取卡信息
	 * @return
	 */
	public String getCardDetail(String cardNo);
}
